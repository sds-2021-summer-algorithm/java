import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,H;
    static int[][] map;
    static int max;
    static boolean ok;
//    1. 내 위치에 1이 있다. 오른쪽 사다리로 가라.
//    2. 내 왼쪽에 1이 있다. 왼쪽 사다리로 가라
    //완전탐색으로 풀음
    //로직 참고 : https://jaejin89.tistory.com/97

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. Data 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+2][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        //2. DFS 돌리기 -->가로선을 3번까지 추가할 수 있음음
       for (int i = 0; i <= 3; i++) {
            max = i;
            solve(1,1,0);
            if(ok) break;
       }

       if(ok){
           System.out.println(max);
       }
       else{
           System.out.println(-1);
       }

    }

    private static void solve(int x, int y, int cnt) {
        if(ok){ //OK되면 무조건 안돌림
            return;
        }

        if (max == cnt){
            if(check()){ //종료 조건
                ok = true; //ok는 3번안에 가능한지 안한지 체크
            }
            return;
        }
        
        for (int i= (y < N ? x : x+1); i<= H; i++){
            for(int j=1; j<N; j++){
                if(map[i][j] == 1|| map[i][j-1] ==1 || map[i][j+1] == 1){ // 이미 줄이 있음
                    continue;
                }
                map[i][j] = 1; //백트래킹
                solve(i,j,cnt+1); //안되니까 줄 한개 더 추가
                map[i][j] = 0;
            }
        }


    }

    private static boolean check() {
        for (int j=1; j<=N; j++){
            int i=1;
            int tmp = j;
            while(i < H+1){
                if(map[i][tmp] == 1){
                    tmp++;
                }
                else if (map[i][tmp-1] == 1){
                    tmp--;
                }
                i++;
            }
            if(j != tmp){
                return false;
            }
        }
        return true;
    }

}
