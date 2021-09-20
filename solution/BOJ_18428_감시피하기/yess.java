```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Teacher{
	int row; 
	int column; 
	public Teacher (int r, int c ) {
		this.row = r; 
		this.column = c; 
	}
}

public class Main {
	static int n ; 
	static char [][] map;  
	static boolean flag  = false; 
	static ArrayList<Teacher> al = new ArrayList<>();   
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char [n][n];
	 
		
		for(int i = 0 ; i < n ; i ++) {
			String s  = br.readLine();
			for(int j =  0 ; j < n ; j ++) {
				map[i][j] = s.charAt(j * 2);
				
				if(map[i][j] == 'T') {
					al.add(new Teacher(i,j)); 
				}
			}
		}
		
		
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < n ; j ++) {
				if(map[i][j] == 'X') {
					installBarrier(i,j,0,3);
				}
			}
		}
		if(flag == true ) {
			System.out.println("YES");
		}
		else System.out.println("NO");
	
	}
	static boolean check4direction(int r, int c) {
	
		for(int i = r + 1; i < n ; i ++) {
			if(map[i][c] == 'S') return false; 
			else if (map[i][c] == 'W') break;
			else if(map[i][c] == 'T') break;
		}
		for(int i = c + 1; i < n ; i ++) {

			if(map[r][i] == 'S') return false; 
			else if (map[r][i] == 'W') break;
			else if(map[r][i] == 'T') break;
		}
		
		for(int i = c - 1; i >= 0 ; i --) {
			if(map[r][i] == 'S') return false; 
			else if (map[r][i] == 'W') break;
			else if(map[r][i] == 'T') break;
		}
		for(int i = r - 1; i >= 0 ; i --) {
			if(map[i][c] == 'S') return false; 
			else if (map[i][c] == 'W') break;
			else if(map[i][c] == 'T') break;
		}
		return true; 
	}
	static void isitPossible() {
		flag = true; 
		for(int i = 0 ; i < al.size(); i ++) {
			Teacher t = al.get(i); 
			int tr = t.row; 
			int tc = t.column;
			
			if (check4direction(tr, tc) ==false) {
				flag = false;
				break;
			}
		}
		
		
	}
	static void installBarrier(int x, int y , int cnt, int target) {
		if(flag == true) {
			return ; 
		}
		if(cnt == target) {
			isitPossible();
			return ; 
		}
		for(int i = x; i < n ; i ++) {
			for(int j = i == x ? y : 0 ; j < n ; j ++  ) {
				if(map[i][j] == 'X') { 
					map[i][j] = 'W';
					installBarrier(i,j,cnt + 1, target);
					map[i][j] = 'X';
				
				}
			} 
		}
	}
}

```
