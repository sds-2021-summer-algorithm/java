```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static boolean flag = false; 
	static int cnt; 
	static int answer = 0 ; 
	static boolean [][] check; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static char [][] map = new char[12][6]; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		for(int i = 0 ; i < 12; i ++) {
			String s = br.readLine(); 
			for(int j = 0 ; j < 6 ; j ++)
			{
				map[i][j]  = s.charAt(j); 
			}
		}
		while(true) {
			flag = false; 
			for(int i = 0 ; i < 12; i ++) {
				for(int j = 0 ; j < 6; j ++) {
					if (map[i][j] != '.') {
						check = new boolean [12][6]; 
						cnt = 0; 
						checking(i,j);
						if (cnt >= 4) 
						{
							flag = true;
							remove(i,j); 
							continue; 
						}
					}
				}
			}
			if (flag == false) break;
			else {
				answer ++; 
				gravity(); 
			}
		}

			System.out.println(answer); 
		}
	
	static void gravity() {
		for(int i = 0 ; i < 6; i ++) {
			
			for(int j = 11 ; j >= 1; j --) {
				int temp = j ; 
				if (map[j][i] == '.') {
					for(int p = j - 1; p >= 0 ;  p--) {
						if (map[p][i] != '.') 
							{
								temp = p; 
								break; 
							}
					}
					map[j][i] = map[temp][i]; 
					map[temp][i] = '.'; 
				}
			}
		}
	}
	static void remove(int a, int b ) {
		for(int i = a ; i < 12; i ++) {
			for(int j = i==a ?b : 0  ; j < 6 ; j ++) {
				if (check[i][j] == true ) {
					map[i][j] = '.'; 
				}
			}
		}
	}
	static void checking(int a, int b ) {
		
		check[a][b] = true; 
		cnt ++; 
		char now = map[a][b]; 
		for(int d = 0 ; d < dx.length ; d ++) {
			int nx = a + dx[d];
			int ny = b + dy[d]; 
			if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue; 
			if (check[nx][ny] == true ) continue;
			if (map[nx][ny] != now) continue; 
			checking(nx, ny); 
		}
	}
}

```
