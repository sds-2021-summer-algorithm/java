```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1149 {
	//RGB 거리 
	static int [][] dp ; 
	static int n ; 
	static int [][] cost; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		dp = new int[n+1][3];
		cost = new int [n + 1][3]; 
		StringTokenizer st; 
		for(int i = 1 ; i <= n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < 3; j ++) {
			cost[i][j] = Integer.parseInt(st.nextToken()); 
			if (i == 1) dp[i][j] = cost[i][j]; 
			}
		}
		for(int i = 2; i <= n ; i ++) {
			dp[i][0]= Math.min(dp[i-1][1],dp[i-1][2]) + cost[i][0]; 
			dp[i][1]= Math.min(dp[i-1][0],dp[i-1][2]) + cost[i][1]; 
			dp[i][2]= Math.min(dp[i-1][1],dp[i-1][0]) + cost[i][2];
		}
		int answer =Math.min(Math.min(dp[n][0], dp[n][1]),dp[n][2]);
		System.out.println(answer); 
	}
}

```
