import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static item[] arr;
    static int[][] dp;

    static class item{
        int weight;
        int value;

        public item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        //1. 데이터 입력받기!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new item[N+1];
        dp = new int[N+1][K+1];
        arr[0] = new item(0,0);

        for(int i = 1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[i] = new item(w, v);
        }

        //2. DP
        for(int i = 1; i< N+1; i++){
            for(int j = 1; j< K+1; j++){
                if (arr[i].weight > j){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - arr[i].weight] + arr[i].value);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
