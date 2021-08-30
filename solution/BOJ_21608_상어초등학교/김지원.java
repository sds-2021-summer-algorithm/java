import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 구현 하다가 너무 힘들어서, 블로그 참고함 -> 해쉬맵 사용하면 간단한듯
// https://velog.io/@mulgyeol/%EB%B0%B1%EC%A4%80-21608-%EC%83%81%EC%96%B4-%EC%B4%88%EB%93%B1%ED%95%99%EA%B5%90-Java
public class Main {
    static int N;
    static long manzok = 0; //만족도
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] empty_seat, classroom;
    static Map<Integer, Shark> list = new HashMap<>();

    //0. 좌표가 나왔으니, 객체를 만들어주기 - x좌표, y좌표, 친구 리스트
    static class Shark{
        int x;
        int y;
        int[] friends;

        public Shark(int x, int y, int[] friends){
            this.x = x;
            this.y = y;
            this.friends = friends;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. Data 입력받기
        N = Integer.parseInt(st.nextToken());
        classroom = new int[N][N];

        //2. 주변에 몇자리나 비어있는지 배열을 만들어서 넣어주기
        empty_seat = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int count = 0;
                if(i != 0 && i != N-1) count++;
                if(j != 0 && j != N-1) count++;
                empty_seat[i][j] = count;
            }
        }

        //3. 친구들 입력받고 모두 배치해주기
        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.valueOf(st.nextToken());
            int s1 = Integer.valueOf(st.nextToken());
            int s2 = Integer.valueOf(st.nextToken());
            int s3 = Integer.valueOf(st.nextToken());
            int s4 = Integer.valueOf(st.nextToken());

            findSeat(num, new int[] {s1,s2,s3,s4});
        }


        //4. 만족도 조사하기
        check_manjok();
        System.out.println(manzok);

    }

    private static void findSeat(int num, int[] friends) { //중요!!!!!!!!!!!

        int[][] nearScore = new int[N][N]; //주변에 친한 친구가 많은 위치를 찾기 위한 배열

        for(int friend : friends) { //친한 친구들을 모두 돌아봄
            if(list.containsKey(friend)) { //이미 친한친구들이 자리에 안았다면
                Shark shark = list.get(friend);
                int x = shark.x;
                int y = shark.y;

                for(int i=0; i<4; i++) {
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(nx>=0 && nx <N && ny >=0 && ny < N && classroom[nx][ny] == 0) { //들어갈 수 있는지 체크함
                        nearScore[nx][ny]++;
                    }
                }
            }
        }

        int emptyCntMax = -1;
        int nearScoreMax = -1;
        int choiceX = -1;
        int choiceY = -1;

        for(int i=0; i<N; i++) { //이제 자리 배치를 시작함
            for(int j=0; j<N; j++) {
                if(classroom[i][j] != 0) continue; //안비어 있으면 지나감

                if(nearScoreMax < nearScore[i][j]) { //친한친구가 가장 많은 곳에 앉을 수 있다면
                    choiceX = i;
                    choiceY = j;
                    nearScoreMax = nearScore[i][j]; //업데이트 해주기
                    emptyCntMax = empty_seat[i][j];
                } else if(nearScoreMax == nearScore[i][j] && emptyCntMax < empty_seat[i][j]) { //1순위가 아니면 적당한 자리 찾기
                    emptyCntMax = empty_seat[i][j];
                    choiceX = i;
                    choiceY = j;
                }
            }
        }

        classroom[choiceX][choiceY] = num;
        list.put(num, new Shark(choiceX,choiceY, friends)); //자리 앉히기

        for(int i=0; i<4; i++) {
            int nx = choiceX+dx[i];
            int ny = choiceY+dy[i];
            if(nx>=0 && nx <N && ny >=0 && ny < N && classroom[nx][ny] == 0) {
                empty_seat[nx][ny]--; //주변 앉을 수 있는 자리 1칸씩 빼주기
            }
        }
    }

    private static void check_manjok() { //만족도 조사하기
        for(int i=1; i<=N*N; i++) {
            Shark shark = list.get(i);
            int cnt = 0;
            for(int friend : shark.friends) {
                if(Math.abs(list.get(friend).x -shark.x) + Math.abs(list.get(friend).y - shark.y) == 1) {
                    cnt++;
                }
            }

            if(cnt==1) manzok+=1;
            else if(cnt==2) manzok+=10;
            else if(cnt==3) manzok+=100;
            else if(cnt==4) manzok+=1000;
        }
    }
}
