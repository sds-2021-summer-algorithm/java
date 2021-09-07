import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr; //0은 빨 1은 초록, 2는 파랑
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        //1. 데이터 입력받기!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        dp = new int[N][3];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //2. DP
        int pmax = 1000 * N + 1;
        int ans = Integer.MAX_VALUE;
        for(int k = 0; k<3; k++) {
            for(int i = 0; i<3; i++) {
                if(i==k) dp[0][i] = arr[0][i]; //k=0(빨강), k=1(초록), k=2(파랑)
                else dp[0][i] = pmax;
            }
            for(int i = 1; i<N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
            }
            for(int i = 0; i<3; i++) {
                if(i==k) continue; //0번째 집을 칠한 색과 마지막 집을 칠한 색이 같지 않아야함
                ans = Math.min(ans, dp[N-1][i]);
            }
        }

        System.out.println(ans);
    }
}
