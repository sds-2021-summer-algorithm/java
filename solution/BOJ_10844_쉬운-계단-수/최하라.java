import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Set variables
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        // 1 자리 수
        for (int i = 1; i <= 9; i++)
            dp[1][i] = 1;

        // 2 이상 자리 수
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] % 1000000000;
            }
            dp[i][9] = dp[i - 1][8];
        }

        // Result
        long count = 0;
        for (long i : dp[N])
            count += i;
        System.out.println(count % 1000000000);
    }
}