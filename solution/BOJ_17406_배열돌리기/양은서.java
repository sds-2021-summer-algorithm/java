```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution17406 {
	static int n ; 
	static int m ; 
	static int k ; 
	static int map[][] ; 
	static int copymap[][]; 
	static int size; 
	static int min  = Integer.MAX_VALUE; 
	static boolean [] check; 
	static List <int [] > operation = new ArrayList <int [] >(); 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		check = new boolean[k]; 
		map = new int [n][m]; 
		for(int i = 0 ; i < n ; i ++) {
			st  = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < m ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		for(int i = 0; i < k ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int r = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			int s = Integer.parseInt(st.nextToken()); 
			operation.add(new int[] {r - 1,c - 1,s});
		}
		// choose operation
		 size = operation.size(); 
		for(int i = 0 ; i < size; i ++ ) {
			if (!check[i]) {
				check[i] = true; 
				ArrayList <Integer> al = new ArrayList <>(); 
				al.add(i); 
				permutation(al); 
				check[i] = false; 
			}
		}
		System.out.println(min); 
		
		
	}
	static void print() {
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				System.out.print(copymap[i][j] + " ");
			}
			System.out.println(); 
		}
		System.out.println(); 
	}
	static void implementation(ArrayList al ) {
		for(int i = 0 ; i < al.size(); i ++) {
			int [] now =operation.get((int) al.get(i)); 
			rotate(now[0]-now[2] , now[1] -now[2], now[0] + now[2], now[1] + now[2]); 
		}
		for(int i = 0 ; i < n ;  i ++) {
			int sum = 0 ; 
			for(int j = 0 ; j < m ; j ++) {
				sum += copymap[i][j]; 
			}
			if (min > sum) min = sum; 
		}
	}
	public static int [][] makenewmap(){
		for(int i = 0; i < n; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				copymap[i][j] = map[i][j]; 
			}
		}
		return copymap; 
	}
	public static void permutation(ArrayList al ) {

		if (al.size() == size ) {
			for(int i = 0 ; i < size ; i ++) {
				// 회전 
				copymap = new int [n][m]; 
				copymap = makenewmap(); 
				implementation(al); 
				return ; 
			}
		}
		int size = operation.size(); 
		for(int i = 0 ; i < size; i ++) {
			if (!check[i]) {
				check[i] = true; 
				al.add(i); 
				permutation(al); 
				al.remove(al.size() -1); 
				check[i] = false; 
			}
		}
	}
	public static void rotate(int x, int y , int x2, int y2) {
		int idx = x2 - x; 
		
		while (idx > 0 ) {
			int temp = copymap[x][y]; 
			for(int i = x + 1; i <= x2; i ++) {
				copymap[i-1][y] = copymap[i][y]; 
			}
			for(int i = y + 1; i <= y2; i ++) {
				copymap[x2][i-1] = copymap[x2][i]; 
			}
			for(int i = x2 -1 ; i >= x; i --) {
				copymap[i + 1][y2] = copymap[i][y2]; 
			}
			for(int i = y2-1 ; i >= y + 1 ; i --) {
				copymap[x][i + 1] = copymap[x][i]; 
			}
			copymap[x][y + 1] = temp;
			idx -= 2; 
			x = x + 1; 
			y = y + 1; 
			x2 = x2 - 1; 
			y2 = y2 - 1; 
			
		}
	}
}


```
