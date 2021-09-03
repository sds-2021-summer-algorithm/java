import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static long result = 0; //길의 갯수

    static int[][] map_row;
    static int[][] map_column;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. Data 입력받기
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map_row = new int[N][N];
        map_column = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map_row[i][j] = value;
                map_column[j][i] = value;
            }

        }

        // 2. 모든 길을 다 함수에 넣어보기 -> 2N개의 길
        for(int i=0; i<N; i++){
            find_load(map_row[i]);
            find_load(map_column[i]);
        }

        //3. 정답 출력
        System.out.println(result);

    }

    private static void find_load(int[] map) { //길이 맞는지 검사하는 함수
        boolean[] visit = new boolean[N]; //블럭을 놓았는지 체크하기

        //1. 안되는 경우 제거하기
        for(int i = 0; i< N-1; i++){

            if(map[i] != map[i+1]){
                int dif = map[i] - map[i+1];
                if (Math.abs(dif) != 1){ //1-1. 높이차이가 1이 아닌경우
                    return;
                }

                else if (dif == 1){ //왼쪽이 높은 경우
                    for(int j=1; j<=L;j++){
                        if(i+j >= N || visit[i+j]){ //1-2. 범위를 벗어난 경우 && 이미 블럭이 있는 경우
                            return;
                        }
                        if(map[i] -1 == map[i+j]){
                            visit[i+j] = true;
                        }
                        else{   //1-3. L만큼 같은 높이의 낮은블럭이 있는 게 아닌경우
                            return;
                        }
                    }

                }

                else if (dif == -1){ //오른쪽이 높은 경우
                    for(int j=0; j<L;j++){
                        if(i < j || visit[i-j]){ //1-2. 범위를 벗어난 경우 && 이미 블럭이 있는 경우
                            return;
                        }
                        if(map[i] == map[i-j]){
                            visit[i-j] = true;
                        }
                        else{         //1-3. L만큼 같은 높이의 낮은블럭이 있는 게 아닌경우
                            return;
                        }
                    }
                }
            }

            //문제 구현이 너무 빡세서 머리터질뻔...
        }
        //2. 길 추가해주기
        result++;
    }
}
