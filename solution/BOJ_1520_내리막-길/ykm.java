import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;                   //<=500
    static int M;                   //<=500
    static int[][] map;
    static boolean[][] isVisited;   // 방문여부 표시
    static int[][] dp;              // 각 위치에서 도착지점까지 갈수있는 경우의 수
    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];
        dp = new int[N][M];
        
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());   // 입력받기                       
            }
        }
        DFS(0,0);
        System.out.println(dp[0][0]);   // 0,0에서 도착지점까지 갈수있는 경우의 수 출력
    }

    private static int DFS(int x, int y){
        if(x==N-1 && y==M-1) {
            return 1;
        }
        if(isVisited[x][y]){            // 가본길은 가지않음.
            return dp[x][y];            // 해당 위치에서 도착지까지 갈수있는 경로수
        }

        isVisited[x][y] = true;
        for(int i = 0 ; i< 4; i++){     // 상하좌우
            int nextX = x+mx[i];
            int nextY = y+my[i];

            if(nextX<0 || nextY<0 ||nextX >=N || nextY>=M) continue;

            if(map[nextX][nextY] < map[x][y]){
                // 길을 방문후 돌아올때 갔었던 위치에서 도착지까지 경로수를 가지고 돌아옴.
                dp[x][y] += DFS(nextX,nextY); 
            }
        }
        return dp[x][y];
    }
}
