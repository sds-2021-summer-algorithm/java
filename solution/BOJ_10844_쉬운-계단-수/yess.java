```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	static long [][] dp; 
	static int 	N; 
	static long sum = 0  ; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		dp = new long [N + 1][10];   
		for(int i = 1;i <= 9; i ++) {
			dp[1][i] =1; 
		}
		for(int i = 2; i <= N ; i ++) {
			for(int j = 0 ; j <= 9 ; j ++) {
				if (j == 0) {
					dp[i][j] = dp[i-1][1] % 1000000000;
					continue; 
				}
				else if (j == 9) {
					dp[i][j] = dp[i-1][8] % 1000000000; 
					continue; 
				}
				else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000; 
				}
			}
		}
		for(int i = 0 ; i <= 9 ; i ++) {
			sum  += dp[N][i]; 
			sum %= 1000000000; 
		}
	
		System.out.println(sum  ); 
	}
}

```
