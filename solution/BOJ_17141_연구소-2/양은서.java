```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	static int m ;
	static int [][] map; 
	static ArrayList <int []> al ; 
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0,0,1,-1};
	static int answer = 2501; 
	static int possible_area = 0 ; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		map =new int [n][n]; 
		al = new ArrayList<>(); 
		for(int i = 0 ;  i < n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if (map[i][j] == 2) {
					al.add(new int[] {i,j}); 
				}
				if(map[i][j] == 0 || map[i][j] == 2 ) {
					possible_area ++ ;
				}
			}
		}
		choosePosition(-1, 0); 
		if (answer == 2501) System.out.println(-1); 
		else System.out.println(answer); 
		
	}
	static int  calculate_time() {
		int [][] newmap = new int [n][n];
		Queue <int []> queue = new ArrayDeque<>(); 
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < n ; j ++) {
				newmap[i][j] = map[i][j]; 
				if (newmap[i][j] == -1 ) {
					queue.add(new int[] {i, j, 0}); 
				}
			}
		}
		int depth =  0 ; 
		int virusarea = 0 ; 
		while (! queue.isEmpty()) {
			int [] now = queue.poll(); 
			if (depth != now[2])depth ++;  
			for(int i = 0 ; i < dx.length; i ++) {
				int nx = now[0] + dx[i]; 
				int ny = now[1] + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n ) continue; 
				if (newmap[nx][ny] == 1) continue;
				if (newmap[nx][ny] == -1 ) continue; 
				queue.add(new int [] {nx,ny, now[2] + 1});
				newmap[nx][ny] = -1; 
				virusarea ++;
				
			}
		}
		if (virusarea + m  != possible_area) return -1; 
		return depth ; 
		
	}
	static void choosePosition(int now, int cnt) {
		if (cnt == m) {
			int ret = calculate_time(); 
			if (ret != -1)
				answer = Math.min(answer,ret ); 
		}
		for(int i = now + 1 ; i < al.size() ; i ++) {
			map[al.get(i)[0]][al.get(i)[1]] = -1; 
			choosePosition(i, cnt + 1); 
			map[al.get(i)[0]][al.get(i)[1]] = 2; 
		}
	}
}

```
