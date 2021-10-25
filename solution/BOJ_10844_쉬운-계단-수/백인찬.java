import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp; // dp[i][j] 길이가 i일때 마지막 자리가 j일 때 계단수의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if(j == 0) dp[i][0] = dp[i - 1][1] % 1000000000;
                else if(j == 9) dp[i][j] = dp[i - 1][j - 1] % 1000000000;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }
        bw.write(sum % 1000000000 + "\n");
        bw.flush();
        bw.close();
    }
}
