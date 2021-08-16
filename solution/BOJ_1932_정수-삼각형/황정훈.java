import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] T, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        T = new int[N][];
        dp = new int[N][];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = new int[i+1];
            dp[i] = new int[i+1];
            for (int j = 0; j < i+1; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
                if(i==0 && j==0) {
                    dp[0][0] = T[0][0];
                    continue;
                }
                if(j==0)
                    dp[i][j] = dp[i-1][0] + T[i][0];
                else if (j==i)
                    dp[i][j] = dp[i-1][j-1] + T[i][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j-1]+T[i][j], dp[i-1][j]+T[i][j]);
                answer = Math.max(answer, dp[i][j]);
            }
        }

        bw.write(answer>0 ? answer+"\n" : T[0][0]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
