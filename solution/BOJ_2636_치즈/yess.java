```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int m ; 
	static int n ; 
	static int [][] map; 
	static boolean [][]check; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int answer = 0 ; 
	static int cnt = 0 ; 
	static int left = 0 ; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		m = Integer.parseInt(st.nextToken()); 
		n = Integer.parseInt(st.nextToken()); 
		map = new int [m][n]; 
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if (map[i][j] == 1) cnt ++; 
			}
		}
		while (cnt > 0 ) {
			left = cnt; 

			check = new boolean[m][n];
			checkOutside(0,0); 
			check = new boolean [m][n]; 
			for(int i = 1; i < m ; i ++) {
				for(int j = 1; j < n ; j ++) {
					if (map[i][j] == 1 && check [i][j] == false) {
						checkOutsideCheese(i,j); 
					}
				}
			}
			for(int i = 0 ; i < m ; i ++) {
				for(int j = 0 ; j < n ; j ++) {
					map[i][j] = map[i][j] == 3 ? 2 : map[i][j]; 
				}
			}
			answer ++; 
		}
		System.out.println(answer); 
		System.out.println(left); 
	}

	static void checkOutsideCheese(int x, int y ) {
		check[x][y] = true ; 
		if (isitRealOutside(x,y) == true ) 
			{
				cnt --; 
				map[x][y] = 3;  
			}
		for(int i = 0 ; i < dx.length; i ++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i]; 
			if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue; 
			if (map[nx][ny] != 1 || check[nx][ny] == true) continue; 
			checkOutsideCheese(nx, ny); 
		}
	}
	static boolean isitRealOutside(int x, int  y ) {
		for(int i = 0 ; i < 4; i ++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i]; 
			if (map[nx][ny] == 2) return true; 
		}
		return false; 
	}

	static void checkOutside(int x, int y ) {
		check[x][y] = true ; 
		map[x][y] = 2; 
		for(int i = 0 ; i < dx.length; i ++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i]; 
			if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue; 
			if (map[nx][ny] == 1 || check[nx][ny] == true ) continue; 
			checkOutside(nx,ny); 
		}
	}
}

```
