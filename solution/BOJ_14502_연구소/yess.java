```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n ; 
	static int m ; 
	static BufferedReader br ; 
	static int [][] map; 
	static BufferedWriter bw; 
	static int [] x = {1,-1,0,0};
	static int [] y = {0,0,1,-1};
	static int max = 0 ; 
	
	public static void main(String [] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in)); 
		bw = new BufferedWriter (new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m= Integer.parseInt(st.nextToken()); 
		map = new int [n][m];
		for(int i = 0 ; i < n ;  i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < m ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		dfs(0); 
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
		
	}
	static void find() {
		int [][] copy_map = new int [n][m]; 
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				copy_map[i][j] = map[i][j]; 
			}
		}
			Queue<int [] > queue = new ArrayDeque<>(); 
		
	
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				if(copy_map[i][j] == 2) {
					int temp [] = {i,j};
					queue.add(temp); 
				}
			}
		}
		while(!queue.isEmpty()) {
			int [] now =queue.poll();
			int tx = now[0]; 
			int ty = now[1]; 
			for(int i = 0 ; i < x.length; i ++) {
				int nx = tx + x[i]; 
				int ny = ty + y[i]; 
				if(nx < 0 || nx >= n || ny < 0 || ny >= m  )continue; 
				if(copy_map[nx][ny] == 0 ) {
					copy_map[nx][ny] = 2; 
					queue.add(new int[] {nx,ny});
				}
			}
		}
		count_virus(copy_map); 
		
	}
	static void count_virus(int [][] cmap) {
		int cnt = 0 ; 
		for(int i =  0  ; i < n ; i ++)
		{
			for(int j = 0 ; j < m ; j ++) {
				if(cmap[i][j] == 0 ) {
					cnt ++; 
				}
			}
		}
		if(cnt > max) max = cnt; 
	}
	
	static void dfs(int idx ) {
		if(idx == 3) {
			find(); 
			return ; 
		}
		for(int i= 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				if(map[i][j] == 0 ) {
					map[i][j] = 1; 
					dfs(idx + 1); 
					map[i][j] = 0 ; 
				}
			}
		}
	}
}
```
