import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] table;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        table = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				table[i][j] = table[i][j-1] + table[i-1][j] - table[i-1][j-1] + Integer.parseInt(st.nextToken());
		}
        
        int x1, y1, x2, y2; 
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			bw.write(table[x2][y2] - table[x1-1][y2] - table[x2][y1-1] + table[x1-1][y1-1] + "\n");
		}
        
        bw.flush();
        bw.close();
        br.close();
    }
}
