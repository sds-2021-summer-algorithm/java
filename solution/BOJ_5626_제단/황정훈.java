import java.io.*;
import java.util.*;

public class Main {

    static int N, MAX;
    static int[] H;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
            if (H[i] > Math.min(i, (N-1)-i)){
                bw.write("0\n");
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }
        MAX = N/2+N%2-1; // 가능한 최대 높이
        dp = new int[N][MAX+1]; // dp[i][j] : i번째 제단의 높이가 j일 경우의 수
        dp[0][0] = 1;
        for (int i = 1; i < N; i++) {
            if(H[i]==-1) // 높이를 모르는 경우
                for (int j = 0; j <= Math.min(i, (N-1)-i); j++) {
                    fill(i, j); // (i, j)를 채운다.
                }
            else // 높이를 아는 경우
                fill(i, H[i]); // (i, H[i])를 채운다.
        }
        bw.write(dp[N-1][0]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static void fill(int i, int target){
        for (int dh = -1; dh <= 1; dh++) { // 직전보다 크거나 작거나 같거나 세가지 경우만 가능.
            if (0<=target+dh && target+dh<=MAX){ // 직전 높이가 0 이상 MAX값 이하일때,
                dp[i][target] += dp[i-1][target+dh];
                dp[i][target] %= 1000000007;
            }
        }
    }
}
