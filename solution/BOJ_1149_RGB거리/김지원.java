import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //1. 데이터 입력받기
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        //2. 점화식으로 풀기
        for(int i=0; i<3; i++){
            dp[0][i] = arr[0][i];
        }

        for(int i=1; i<N; i++){
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][1],dp[i-1][0]) + arr[i][2];
        }

        System.out.println(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));
    }
}
