import java.io.*;
import java.util.*;

public class Main {

  static String str1, str2;
  static int[][] dp;

  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      str1 = br.readLine();
      str2 = br.readLine();
      dp = new int[str1.length()+1][str2.length()+1];

      int answer=0;
      for (int i = 1; i <= str1.length(); i++)
        for (int j = 1; j <= str2.length(); j++) // 대각선으로 이동하며
          if (str1.charAt(i-1)==str2.charAt(j-1)) { // 문자가 같으면
            dp[i][j] += dp[i-1][j-1] + 1; // 왼쪽위의 값보다 1 증가하여 dp진행
            answer = Math.max(answer,  dp[i][j]);
          }
      bw.write(answer+"\n");

      bw.flush();
      bw.close();
      br.close();
  }
}
