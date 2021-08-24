```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int testcase ;
	static int n ; 
	static int [] dp = new int [11 + 1] ; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		testcase  = Integer.parseInt(br.readLine()); 
		StringBuilder sb = new StringBuilder(); 
		dp[0] = 0 ; 
		dp[1] = 1; 
		dp[2] = 2; 
		dp[3] = 4; 
		while(testcase > 0 ) {
			testcase -- ; 
			n = Integer.parseInt(br.readLine()); 
			if(dp[n] != 0 ) {
				sb.append(dp[n] + "\n"); 
				continue; 
			}
			for(int i = 4; i <= 11; i ++) {
				dp[i] = dp[i-3] + dp[i-2] + dp[i-1]; 
				if(i == n ) {
					sb.append(dp[n] + "\n"); 
					break; 
				}
			}
			
		}
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out)) ; 
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
}
```
