```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
//(1,1) -> (n,m) 시작점 끝점 포함 
// 벽을 k개 까지 부술수 있다. 
	static int n ; 
	static int m ; 
	static int k ; 
	static int [][] map; 
	static int [][] copymap;
	static ArrayList <int [] > blocks; 
	static boolean [] check;
	static boolean [][] mapcheck; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int answer = Integer.MAX_VALUE; 
	static boolean flag = false; 
	static Queue <int []> queue ; 
	
	
	public static void main(String [] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		map = new int [n + 1][m + 1];
		blocks = new ArrayList <>(); 
		
		for(int i = 1; i <= n ; i ++) {
			String s = br.readLine(); 
			for(int j = 1; j <= m ; j ++) {
				map[i][j] = Integer.parseInt(s.substring(j-1,j));
				if (map[i][j] == 1) {
					blocks.add(new int [] {i,j}); 
				}
			}
		}
		check = new boolean [blocks.size()]; 
		
		checkblocks(0,0); 
		 
		if (flag == false) System.out.println(-1); 
		else System.out.println(answer) ; 
		
		
	}
	static void copyMap() {
		copymap = new int [n + 1][m + 1]; 
		for(int i = 1; i <= n ; i ++) {
			for(int j = 1; j <= m ; j ++) {
				copymap[i][j] = map[i][j]; 
			}
		}
		for(int i = 0 ; i < check.length; i ++) {
			if (check[i] == true ) {
				int [] temp = blocks.get(i); 
				copymap[temp[0]][temp[1]] = 0; 
			}
		}
	}
	static void Print() {
		for(int i = 1 ; i <= n ; i ++) {
			for(int j = 1; j <= m ; j ++) {
				System.out.print(copymap[i][j]);
			}
			System.out.println(); 
		}
		System.out.println(); 
	}
	// 벽 부수는거
	static void checkblocks(int idx, int cnt ) {
		if(cnt <= k ) {
			copyMap(); 
			mapcheck = new boolean [n + 1][m + 1]; 
			calculateDistance(); 
		}
		for(int i = idx ; i < blocks.size(); i ++ ) {
			if (check[i] == false) {
				check[i] = true; 
				checkblocks(i, cnt + 1); 
				check[i] = false; 
			}
		}
	}
	static void calculateDistance() {
		queue = new ArrayDeque<>(); 
		queue.add(new int [] {1,1,1}); 
		
		mapcheck[1][1] = true; 
		
		while (!queue.isEmpty()) {
			
			int now[] = queue.poll(); 
			
			int nowx = now[0]; 
			int nowy = now[1]; 
			
			if (nowx == n && nowy == m) {
				flag = true; 
				if (answer > now[2]) {
					answer = now[2]; 
					break; 
				}
			}
			
			for(int i = 0 ; i < dx.length; i ++) {
				int nx = nowx + dx[i]; 
				int ny = nowy + dy[i]; 
				if (nx <= 0 || ny <= 0 || nx > n || ny > m ) continue; 
				if (copymap[nx][ny] == 1) continue; 
				if (mapcheck[nx][ny] == true ) continue; 
				mapcheck[nx][ny] = true; 
				queue.add(new int[] {nx,ny,now[2] + 1});
			}
		}
		
		
	}
}

```
