import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M; //세로와 가로 크기
    static int r,c,d; // 시작좌표 + 방향
    static int map[][]; // 지도
    static int dr[] = { -1, 0, 1, 0 }; //북 - 동 - 남 - 서
    static int dc[] = { 0, 1, 0, -1 };
    static int cnt = 0; //청소한 칸을 체크

    public static void main(String[] args) throws IOException {
        //1. 데이터 입력받기 + 지도 완성시키기
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(in.readLine());
        r = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());
        d = Integer.valueOf(st.nextToken()); // 0북쪽 / 1동쪽 / 2남쪽 / 3서쪽

        for(int i =0; i< N; i++){
            st = new StringTokenizer(in.readLine());
            for(int j =0; j<M; j++){
                map[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        clean(r,c,d);

        System.out.println(cnt);

    }

    private static void clean(int r, int c, int d) {
        if(map[r][c] == 0){ //2-1. 청소가 안되있다면
            map[r][c] = 2; //청소상태로 만들어준다.
            cnt++;
        }

        //2-2. 왼쪽방향부터 체크한다.
        boolean flag = false;
        for(int i=0; i<4; i++){
            int nd = (d+3) % 4; //왼쪽으로 머리를 돌리기
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if(nr > 0 && nc > 0 && nr < N && nc < M){
                if(map[nr][nc] == 0){ //아직 청소하지 않았다면
                    clean(nr,nc,nd); //청소해주고 끝내기
                    flag = true;
                    break;
                }
            }
            d = (d + 3) % 4;
        }

        // 2-3모든 방향이 청소가 된 경우
        if(!flag){ //위에서 단 한번도 플래그가 안바뀌면, 그 위치에서 모든 방향이 청소되있다는 뜻
            int nd = (d + 2) % 4; //180도 바꾸면서 후진준비
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if(nr > 0 && nc > 0 && nr < N && nc < M) {
                if (map[nr][nc] != 1){ //뒤로 돌았는데 벽이 아니면
                    clean(nr,nc,d);
                }
            }
        }


    }
}
