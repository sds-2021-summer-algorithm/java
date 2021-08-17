import java.io.*;
import java.util.*;

public class Main {

  static int N, M;
  static int[][] arr;

    public static void main(String[] args) throws Exception {
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        int answer=0;
        for (int i = 1; i <= N; i++) {
        	String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				if (str.charAt(j-1)=='1') {
					arr[i][j] = Math.min(arr[i-1][j], Math.min(arr[i][j-1], arr[i-1][j-1])) + 1;
					answer = Math.max(answer, arr[i][j]);
				}
			}
		}
        bw.write(answer*answer+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
