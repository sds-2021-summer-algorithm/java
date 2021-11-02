```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 최대 공통 부분 수열 
	static String s1;
	static String s2 ; 
	static int len1; 
	static int len2; 
	static int [][] dp ; 
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		s1 = br.readLine(); 
		s2 = br.readLine();
		len1 = s1.length(); 
		len2 = s2.length(); 
		dp = new int[len1 + 1][len2 + 1]; 
	
		
		for(int i = 1 ; i < len1  + 1; i ++) {
			for(int j = 1 ;  j < len2 + 1; j ++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1]  + 1; 
				}
				else{
					dp[i][j] = Math.max(dp[i- 1][j], dp[i][j -1]); 
				}
			}
		}
		System.out.println(dp[len1][len2]); 
		findString(); 
		System.out.println(sb.reverse().toString()); 
		
	}
	static void findString() {
		int idx1 = len1; 
		int idx2 = len2; 
		while(idx1 >= 1 && idx2 >= 1 ) {
			if (dp[idx1][idx2] == dp[idx1- 1][idx2]) idx1 --; 
			else if (dp[idx1][idx2] == dp[idx1][idx2 -1 ]) idx2 --; 
			else{
				sb.append(s2.charAt(idx2 - 1)); 

				idx1 --; 
				idx2 --; 
			}
		}
	}
}

```
