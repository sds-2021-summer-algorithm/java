import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int M, N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dfs(0, 0);

        bw.write(dp[0][0]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static int dfs(int r, int c){
        if (r==M-1 && c==N-1) // 종료지점이면
            return 1; // 1 리턴
        else if (dp[r][c]<0){ // 방문한적 없으면
            dp[r][c] = 0; // 0으로 초기화하고
            for (int i = 0; i < 4; i++) { // 4방향 돌면서
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0<=nr && nr<M && 0<=nc && nc<N && map[r][c]>map[nr][nc]) // 조건만족하면
                    dp[r][c] += dfs(nr, nc); // 값 추가
            }
        }
        return dp[r][c]; // 갈 수 있는 경로수 리턴
    }
}
