import java.io.*;
import java.util.*;

public class Main {

	static String str1, str2;
	static int[][] dp;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str1 = " " + br.readLine();
        str2 = " " + br.readLine();
        dp = new int[str1.length()][str2.length()];
        
        for (int i = 1; i < str1.length(); i++)
			for (int j = 1; j < str2.length(); j++)
				if (str1.charAt(i)==str2.charAt(j)) // i번쨰 str과 j번째 str이 같으면
					dp[i][j] = dp[i-1][j-1] + 1; // 왼쪽 대각선 위의 값에다가 +1 하고
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // 아니면 위하고 왼쪽에서 큰값 넣기
        
        StringBuilder sb = new StringBuilder();
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        while(dp[i][j] != 0) {
        	while(dp[i-1][j]==dp[i][j]) i--;
        	while(dp[i][j-1]==dp[i][j]) j--;
        	sb.append(str1.charAt(i));
        	i--;
        	j--;
        }
        bw.write(sb.length()+"\n"+sb.reverse().toString());        
        
        bw.flush();
        bw.close();
        br.close();
    }
}
