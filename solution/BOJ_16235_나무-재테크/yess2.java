```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int [][] map; 
	static int [][] A; 
	static PriorityQueue <int []> pq;  
	static int n ; 
	static int m ; 
	static int k ; 
	static int [] dx = {1,-1,1,-1,0,0,1,-1};
	static int [] dy = {1,-1,0,0,1,-1,-1,1};
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		pq = new PriorityQueue <>(new Comparator <int []>() {
			@Override 
				public int compare(int [] o1, int []o2) {
					return o1[2] - o2[2]; 
				}
			
				
		});
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		map = new int [n][n];
		for(int i = 0 ; i< n ; i ++) {
			Arrays.fill(map[i], 5);
		}
		A = new int[n][n]; 
		for(int i = 0 ; i < n ; i ++) { 
			st = new StringTokenizer(br.readLine());	
			for(int j = 0 ; j < n ; j ++) {
				A[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int x = Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken()); 
			int n = Integer.parseInt(st.nextToken()); 
			pq.add(new int[] {x,y,n}); 
		}
		for(int i = 0 ; i < k ; i ++) {
			bfs(); 
		}
		
		System.out.println(pq.size()); 
		
	}
	static void bfs() {
		int size = pq.size();
		PriorityQueue <int []> newpq = new PriorityQueue <>(new Comparator <int [] >() {
			@Override 
			public int compare(int [] o1, int []o2) {
				return o1[2] - o2[2]; 
			}
			
		});
		Queue <int [] > dead = new LinkedList <>(); 
		Queue <int []> breed = new ArrayDeque <>(); 
		for(int i = 0 ; i < size; i ++){
			int [] now = pq.poll(); 
			int now_age = now[2]; 
			int now_x = now[0]; 
			int now_y = now[1]; 
			if (map[now_x -1] [now_y -1] < now_age)  
			{
					dead.add(now); 
					continue; 
			}
			map[now_x - 1][now_y - 1] -= now_age; 
			newpq.add(new int [] {now_x, now_y, now_age + 1}); 
				
			if((now_age + 1) % 5 == 0 ) {
				breed.add(new int [] { now_x, now_y, now_age + 1});
			}
		}
		pq = new PriorityQueue <>(newpq); 
		
		size = dead.size();
		for(int i = 0 ; i < size; i ++) {
			int [] now = dead.poll();
			map[now[0] -1] [now[1] -1] += now[2] / 2; 
		}
		
		size = breed.size(); 
		for(int i = 0 ; i < size; i ++)
		{
			int [] now = breed.poll(); 
			for(int j = 0 ; j < 8 ; j ++) {
				int nx = now[0] + dx[j]; 
				int ny = now[1] + dy[j]; 
				if (nx < 1 || nx > n || ny < 1 || ny > n) continue; 
				else
					{
						pq.add(new int[] {nx,ny,1}); 
					}
			}
		}
		
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] += A[i][j]; 
			}
		}
	}
}

```
