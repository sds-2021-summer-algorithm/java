```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 최대 공통 부분 수열 
	static String s1;
	static String s2 ; 
	static int [][] dp ; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		s1 = br.readLine(); 
		s2 = br.readLine();
		int len1 = s1.length(); 
		int len2 = s2.length(); 
		dp = new int[len1 + 1][len2 + 1]; 
		for(int i = 0 ; i < dp.length ; i ++) {
			dp[i][0] = 0 ; 
		}
		for(int j = 0 ; j < dp[0].length; j ++) {
			dp[0][j] = 0 ; 
		}
		for(int i = 0 ; i < len1; i ++) {
			for(int j = 0 ;  j < len2; j ++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i  + 1][j + 1] = dp[i][j]  + 1; 
				}
				else{
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]); 
				}
			}
		}
		System.out.println(dp[len1][len2]); 
	}
}

```
