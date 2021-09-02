```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	// 경사로
	
	/*
	 * 1. 경사로는 낮은 칸에 놓아야 하고, L개의 연속된 칸에 경사로의 바닥이 모두 닿아야한다. 
	 * 2. 낮은 칸과 높은 칸의 차이는 1 
	 * 3. 경사로를 놓을 낮은 칸의 높이는 모두 같아야하고, L개의 칸이 연속되어야한다. 
	 * 
	 */
  
	static int n ; 
	static int L ; 
	static int column [][] ; 
	static int row [][]; 
	static int answer = 0 ; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		L = Integer.parseInt(st.nextToken()); 
		column = new int[n][n]; 
		row = new int[n][n]; 
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ; j ++) {
				row[i][j] = Integer.parseInt(st.nextToken()); 
				column[j][i] = row[i][j]; 
			}
		}
		
		
		for(int i = 0 ; i < n ; i ++) {
			find_road(column[i]); 
			find_road(row[i]); 
		}
		System.out.println(answer); 
		
	}
	static void find_road(int [] temp) {
		boolean [] check = new boolean [n]; 
		
		for(int i = 0 ; i < n  -1; i ++) {
			int b1 = temp[i]; 
			int b2 = temp[i + 1]; 
			// 두 숫자가 같을 때
			if(b1 == b2) continue; 
			// 차이가 1 이상이면 안됨
			if(Math.abs(b1 - b2) != 1) return ;
			
			if(b1 < b2) {
				// 오른쪽이 더 큰 경우 
				for(int l = 0 ; l < L ; l ++) {
					if(i < l  || check[i-l] == true) return; 
					if(temp[i-l] == b1) check[i-l] = true; 
					else return ; 
				}
			}
			else {
				// 왼쪽이 더 큰 경우 
				for(int l = 1 ; l <= L ; l ++) {
					if(i + l < n && check[i + l ] == false) {
						if(temp[i + l] == b2) {
							check[i + l ] = true; 
						}
						else {
							return ; 
						}
					}
					else {
						return ; 
					}
				}
			}
		}
		answer ++ ; 
	}
	
}
```
