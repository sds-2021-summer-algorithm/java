import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        if(N == 1) {
            bw.write("0\n");
            bw.flush();
            bw.close();
            return;
        }
        if (N == 2) {
            bw.write("1\n");
            bw.flush();
            bw.close();
            return;
        }
        dp = new int[N + 1];
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            if(i % 6 == 0) {
                dp[i] = Math.min(dp[i / 3], Math.min(dp[i / 2], dp[i - 1])) + 1;
            } else if (i % 3 == 0) { // 3으로는 나누어떨어짐 2로는 안됨
                dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        bw.write(dp[N] + "\n");
        bw.flush();
        bw.close();
    }
}
