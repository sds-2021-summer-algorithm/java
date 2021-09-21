import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 0) continue;
                int right = j + map[i][j];
                int down = i + map[i][j];
                if(right <= N) {
                    dp[i][right] += dp[i][j];
                }
                if(down <= N) {
                    dp[down][j] += dp[i][j];
                }
            }
        }

        bw.write(dp[N][N] + "\n");
        bw.flush();
        bw.close();
    }
}