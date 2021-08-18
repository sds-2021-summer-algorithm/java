import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());

        // 2. 점화식 구현
        long[] dp = new long[N + 1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
