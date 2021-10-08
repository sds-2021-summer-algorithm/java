import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][N + 1];
        int[] num = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; i + j <= N; j++) {
                int k = i + j;
                if(j == k) dp[j][k] = 1;
                else if (j + 1 == k) {
                    if (num[j] == num[k])
                        dp[j][k] = 1;
                }
                else {
                    if (num[j] == num[k] && dp[j + 1][k - 1] == 1) {
                        dp[j][k] = 1;
                    }
                }

            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
