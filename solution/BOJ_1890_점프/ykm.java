import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[][] map;
    static long[][] dp; // 경로의 개수는 2^63

    // 오른쪽이나 아래쪽이동만 가능
    static int[] mx = {1,0};
    static int[] my = {0,1};
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];

        // 입력받기
        for(int i = 0 ; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        
        // DFS + DP로 추정
        System.out.println(move(0,0));
    }

    private static long move(int x, int y){ // 현위치, 진행 방향, 진행할 거리
        
        // 종료 조건
        if(x==N-1 && y==N-1){
            dp[x][y] = 1;
            return 1;
        }
        
        // 방문 표시
        dp[x][y] = 0;
        // 현위치에서 갈수 있는 곳
        for(int i = 0 ; i<2; i++){
            int nextX = x + map[x][y]*mx[i];
            int nextY = y + map[x][y]*my[i];

            if(nextX<0 || nextX>=N ||nextY<0 ||nextY>=N) continue;
            
            if(dp[nextX][nextY]>=0){
                return dp[nextX][nextY];
            }
            else dp[x][y] += move(nextX,nextY);

        }
        return dp[x][y];
    }
}
