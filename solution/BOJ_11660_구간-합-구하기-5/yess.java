```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n ; 
	static int m ; 
	static int [][] map; 
	static StringBuilder sb; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n + 1]; 
		for(int i = 0  ; i < n ; i ++)
		{
			st = new StringTokenizer(br.readLine()); 
			for(int j = 1 ; j<=  n; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 1; j <= n ; j ++ ) {
				map[i][j] = map[i][j-1] + map[i][j];
			}
		}
		sb = new StringBuilder(); 
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int x1 = Integer.parseInt(st.nextToken()); 
			int y1 = Integer.parseInt(st.nextToken()); 
			int x2 = Integer.parseInt(st.nextToken()); 
			int y2 = Integer.parseInt(st.nextToken()); 
			query(x1,y1,x2,y2); 
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static void query(int x1, int y1, int x2, int y2) {
		long sum =  0 ; 
		for(int i = x1 - 1; i <x2; i ++) {
			sum += map[i][y2] - map[i][y1-1]; 
		}
		sb.append(sum + "\n");
	}
}
```
