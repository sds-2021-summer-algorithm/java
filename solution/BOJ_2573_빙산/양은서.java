```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; 
	static int m ; 
	static int [][] map; 
	static int [][]	melt; 
	static int ice = 0 ; 
	static boolean [][] check ; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static boolean flag = false; 
	static int answer = 0 ; 
	
	public static void main(String [] args ) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in)); 
		StringTokenizer st = new StringTokenizer (br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		map  = new int [n][m]; 
		for(int i = 0 ; i < n; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if (map[i][j] != 0) ice ++; 
			}
		}
		while (true) {
			int group = 0 ;
			// group 체크 불린 배열 
			check = new boolean [n][m]; 
			// 주변 바다개수 
			melt  = new int [n][m]; 
			
			for(int i = 0 ; i < n ; i ++) {
				for(int j = 0 ; j < m ; j ++) {
					if (map[i][j] != 0 && check[i][j] == false ) {
						check_iceberg(i,j); 
						group ++; 
					}
				}
			}
			if (group < 2 && ice <= 0) {
				break;
			}
			if (group >= 2) {
				flag = true; 
				break; 
			}
			melt_iceberg(); 
			answer ++; 
		}
		if (flag == false ) System.out.println(0); 
		else System.out.println(answer); 
	}
	static void check_iceberg(int x, int y ) {
		
		check[x][y] = true; 
		
		for(int i = 0 ; i < dx.length; i ++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i] ; 
			if (nx < 0 || ny < 0 || ny >= m || nx >= n) continue; 
			if (check[nx][ny] == true ) continue;
			if (map[nx][ny] == 0 ) {
				melt[x][y] ++; 
				continue; 
			}
			check_iceberg(nx, ny); 
			
		}
	}
	static void melt_iceberg() {
		for(int i= 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				if (map[i][j] != 0 && map[i][j] - melt[i][j] <= 0 ) ice --; 
				map[i][j] -= melt[i][j]; 
				if (map[i][j] <= 0 ) {
					map[i][j] = 0 ; 
				}
			
			}
		}
	}

	
}

```
