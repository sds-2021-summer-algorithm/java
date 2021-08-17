import java.io.*;
import java.util.*;

public class Main {

	static int N, r, c;
	static int[][] dp;
	static class Matrix{
		int row, col;
		public Matrix(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}static Matrix[] mats;

    public static void main(String[] args) throws Exception {
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];
        mats = new Matrix[N+1];
        for (int to = 1; to <= N; to++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			mats[to] = new Matrix(r, c);
			
			for (int from = to-1; from >= 1; from--) {
				int tmp = Integer.MAX_VALUE;
				
				for (int itv = 0; itv < to-from; itv++)
					tmp = Math.min(tmp, dp[from][from+itv] + dp[from+1+itv][to] + mats[from].row * mats[from+itv].col * mats[to].col);
				dp[from][to] = tmp != Integer.MAX_VALUE ? tmp : 0;
			}
		}
        bw.write(dp[1][N]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
   
}
