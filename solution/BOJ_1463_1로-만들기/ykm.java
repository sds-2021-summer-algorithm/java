import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int N;
    static int[] DP;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N+1];
        for(int i = 2 ; i<=N ; i++){
            DP[i] = 1000000;
        }
        makeOne(N);
        System.out.println(DP[N]);
    }

    private static void makeOne(int n) {
        for(int i = 2; i<=n ; i++){
            if(i%3 == 0){
                DP[i] = DP[i/3] + 1;
            }
            if(i%2 == 0){
                DP[i] = Math.min(DP[i], DP[i/2] + 1);
            }
            DP[i] = Math.min(DP[i], DP[i-1] + 1);
        }
    }
}
