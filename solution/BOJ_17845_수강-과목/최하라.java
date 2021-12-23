import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];
        int[] time = new int[K + 1];
        int[] importance = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            importance[i] = Integer.parseInt(st.nextToken());
            time[i] = Integer.parseInt(st.nextToken());
        }
        for (int j = 1; j <= K; j++) {
            for (int t = 1; t <= N; t++) {
                if (t - time[j] >= 0) {
                    dp[j][t] = Math.max(dp[j - 1][t], dp[j - 1][t - time[j]] + importance[j]);

                } else {
                    dp[j][t] = dp[j - 1][t];
                }
            }
        }
        System.out.println(dp[K][N]);
    }
}