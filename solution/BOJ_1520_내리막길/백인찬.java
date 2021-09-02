import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp, map;
    static boolean[][] visited, pushed;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
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
        bw.write(dfs(0, 0) + "\n");;
        bw.flush();
        bw.close();

    }
    static int dfs(int i, int j) {
        if (i == M - 1 && j == N - 1) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int ty = i + my[k];
            int tx = j + mx[k];
            if (ty >= 0 && ty < M && tx >= 0 && tx < N) {
                if (map[ty][tx] < map[i][j]) {
                    dp[i][j] += dfs(ty, tx);
                }
            }
        }

        return dp[i][j];
    }
}
