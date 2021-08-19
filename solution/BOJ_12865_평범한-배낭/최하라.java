import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] W;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];

        dp = new int[N][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // 3. 최대값 출력
        bw.write(String.valueOf(knapsack(N - 1, K)));
        bw.flush();
        bw.close();
        br.close();
    }

    static int knapsack(int idx, int k) {
        // 범위를 벗어날 때
        if (idx < 0)
            return 0;

        // 아직 탐색하지 않은 위치
        if (dp[idx][k] == 0) {
            if (W[idx] > k)
                dp[idx][k] = knapsack(idx - 1, k);
            else {
                dp[idx][k] = Math.max(knapsack(idx - 1, k), knapsack(idx - 1, k - W[idx]) + V[idx]);
            }
        }
        return dp[idx][k];
    }
}