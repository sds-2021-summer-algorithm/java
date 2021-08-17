import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] T = new int[N];
        int[] P = new int[N];
        int[] dp = new int[N+1];
        String[] str;
        for(int i=0; i < N; i++){ //입력받기
            str = br.readLine().split(" ");
            T[i] = Integer.parseInt(str[0]);
            P[i] = Integer.parseInt(str[1]);
        }
        
        //dp로 풀기
        for (int i=0; i < N; i++){
            if (i+T[i] <= N){
                dp[T[i] + i] = Math.max(dp[T[i]+i],P[i]+dp[i]);
            }
            //이전까지의 최대 수입을 비교해서 현재에 저장
            dp[i+1] = Math.max(dp[i+1],dp[i]);
        }

        System.out.println(dp[N]);
    }
}
