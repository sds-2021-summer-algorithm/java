import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] chart;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        chart = new int[N + 6][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            chart[i][0] = Integer.parseInt(st.nextToken());
            chart[i][1] = Integer.parseInt(st.nextToken());
        }

        // 2. dp를 활용해 최대 이익 구하기
        dp = new int[N + 6];
        int max = 0;
        for (int i = 1; i <= N + 1; i++) {
            dp[i] = Math.max(dp[i], max);
            dp[chart[i][0] + i] = Math.max(dp[chart[i][0] + i], dp[i] + chart[i][1]);
            max = Math.max(max, dp[i]);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}