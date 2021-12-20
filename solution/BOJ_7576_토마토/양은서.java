```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int m ; 
	static int n ; 
	static int [][] factory; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static Queue <int []> queue = new ArrayDeque <>(); 
	static boolean [][] check ; 
	static int day = -1; 
	static boolean flag = true; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		m = Integer.parseInt(st.nextToken()); 
		n = Integer.parseInt(st.nextToken()); 
		factory = new int[n][m]; 
		check = new boolean [n][m]; 
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < m ; j ++) {
				factory[i][j] = Integer.parseInt(st.nextToken()); 
				if(factory[i][j] == 1) {
					queue.add(new int [] {i,j}); 
				}
			}
		}
		bfs(); 
		isitdone(); 
		day = flag == false ? -1 : day -1 ; 
		System.out.println(day); 
		
	}
	static void isitdone() {
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				if (factory[i][j] == 0) {
					flag = false; 
					break;
				}
          day  = Math.max(day,factory[i][j]); 
			}
		}
	}
	static void bfs() {

		while (!queue.isEmpty()) {
			int [] now = queue.poll(); 
		
			check[now[0]][now[1]] = true; 
			for(int i = 0 ; i < dx.length; i ++) {
				int nx = now[0] + dx[i]; 
				int ny = now[1] + dy[i]; 
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue; 
				if (check[nx][ny] == true ) continue; 
				if (factory[nx][ny] == 0) {
					queue.add(new int [] {nx, ny});
					factory[nx][ny] = factory[now[0]][now[1]] + 1; 
					
				}
				
			}
			
		}
	}
}	

```
