import java.io.*;
import java.util.*;

public class Main {

  static int N;
  static int[] arr, dp;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N+3];
        dp = new int[N+3];
        for (int i = 3; i < N+3; i++) {
        	 arr[i] = Integer.parseInt(br.readLine());
        	 dp[i] = Math.max(dp[i-2]+arr[i], dp[i-3]+arr[i-1]+arr[i]);
		}        
        bw.write(dp[N+2]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
   
}
