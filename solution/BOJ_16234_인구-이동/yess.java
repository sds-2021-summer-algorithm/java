```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; 
	static int R; 
	static int L ; 
	static boolean [][] visited; 
	static boolean [][] calculated; 
	static int [][]  map; 
	static int dx [] = {1,-1,0,0};
	static int dy [] = {0,0,1,-1};
	static int cnt ; 
	static int sum ; 
	static boolean flag; 
	static int answer = 0 ; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		R = Integer.parseInt(st.nextToken()); 
		L = Integer.parseInt(st.nextToken()); 
		map = new int[n][n]; 
		for(int i = 0 ; i < n ; i ++)
		{
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ;  j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 

				
			}
		}
		
		while(true){
			visited = new boolean [n][n]; 
			flag = false;
			calculated = new boolean [n][n]; 
			
			for(int i = 0 ; i < n ; i ++) {
				for(int j = 0 ; j < n ; j ++) {
					if(visited[i][j] == false) {
						cnt = 1; 
						sum  += map[i][j]; 
						visited[i][j] = true; 
						dfs(i,j); 	
            if(cnt == 1) {
							sum = 0 ; 
							calculated[i][j] = true; 
							continue; 
						}
						calculate(i,j); 
						sum = 0 ; 
					}
				}
			}
			
			if(flag == false) break;  
			answer ++; 
		}
        System.out.println(answer); 
	}
	
	static void calculate(int x, int y ) {
		int val = sum / cnt; 
		for(int i = x ; i < n ; i ++) {
			for(int j = i == x ? y : 0  ; j < n ; j ++) {
				if(visited[i][j] == true && calculated[i][j] == false) {
					map[i][j] = val; 
					calculated[i][j] = true; 
				}
			}
		}
		
	}
	public static void dfs(int x, int y ) {
		
		for(int i = 0 ; i < dx.length; i ++) {
			int nx = x  + dx[i]; 
			int ny = y + dy[i]; 
			if(nx < 0 || ny < 0 || nx >= n || ny >= n ) continue; 
			if(visited[nx][ny] == true) continue; 
			if(Math.abs(map[x][y] - map[nx][ny]) < R ||Math.abs(map[x][y] - map[nx][ny]) > L  ) continue; 
			flag = true; 
			cnt ++; 
			sum += map[nx][ny]; 
			visited[nx][ny] = true; 
			dfs(nx,ny); 
		}
		
	}
}
```
