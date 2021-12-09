import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];

        // Set base (house 1)
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = Integer.parseInt(st.nextToken());

        // Find minimum total
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int current = Integer.parseInt(st.nextToken());

                switch (j) {
                    case 0:
                        dp[i][j] = current + Math.min(dp[i - 1][1], dp[i - 1][2]);
                        break;
                    case 1:
                        dp[i][j] = current + Math.min(dp[i - 1][0], dp[i - 1][2]);
                        break;
                    case 2:
                        dp[i][j] = current + Math.min(dp[i - 1][0], dp[i - 1][1]);
                        break;
                }
            }
        }
        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}