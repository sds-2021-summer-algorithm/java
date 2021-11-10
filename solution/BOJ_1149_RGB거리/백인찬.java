import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] RGB = new int[N][3];
        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = RGB[0][0];
        dp[0][1] = RGB[0][1];
        dp[0][2] = RGB[0][2];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][1] + RGB[i][j], dp[i - 1][2] + RGB[i][j]);
                } else if(j == 1) {
                    dp[i][j] = Math.min(dp[i - 1][0] + RGB[i][j], dp[i - 1][2] + RGB[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][0] + RGB[i][j], dp[i - 1][1] + RGB[i][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(dp[N - 1][i], min);
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}
