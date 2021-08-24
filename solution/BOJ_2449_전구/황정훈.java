import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] C;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = new int[N+1];
        dp = new int[N][N]; // dp[i][j] : i부터 j까지 하나의 색으로 만드는데 필요한 최소 횟수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }
        div(0, N-1); // 0부터 N-1까지 하나의 색으로 만드는데 최소횟수
        bw.write(dp[0][N-1]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static int div(int s, int e){
        if (s==e)
            return 0;
        if (dp[s][e]>0)
            return dp[s][e];
        int cnt = Integer.MAX_VALUE;
        int l, r;
        for (int i = s; i < e; i++) {
            l = div(s, i); // i번째의 왼쪽부분의 최솟값
            r = div(i+1, e); // i번째의 오른쪽부분의 최솟값
            cnt = Math.min(cnt, C[s]==C[i+1] ? l+r : l+r+1); // 색이 같으면 둘이 더하고, 다르면 한번을 추가한것을 더해서
        }
        return dp[s][e] = cnt; // 최솟값 갱신
    }
}
