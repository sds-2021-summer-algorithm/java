```

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 스타트와 링크 
	static int answer = Integer.MAX_VALUE;
	static int n ; 
	static int [][] s; 
	static boolean[] check; 
	static void combination(int start, int now, int target ) {
		// 탈출 조건 : 반 반 으로 나뉘어졌을때 
		if(now == target) {
			findmin(); 
		}
		
		for(int i = start; i <= n ; i ++) {
			if(check[i] == false) {
				check[i] = true; 
				combination(i + 1 ,now + 1, target); 
				check[i] = false; 
			}
		}
	}
	static void findmin() {
		int teamLink = 0 ; 
		int teamStart  = 0 ; 
		for(int i = 1; i <= n ; i ++) {
			for(int j =i + 1; j <= n ; j ++) {
				if(check[i] == true && check[j] == true) {
					teamStart += s[i][j] ; 
					teamStart += s[j][i]; 
				}
				
				else if(check[i] == false && check[j] == false) {
					teamLink += s[i][j] ; 
					teamLink += s[j][i]; 
				}
			}
		}
		if(Math.abs(teamLink-teamStart) == 0 ) {
			answer = 0;
			return; 
		}
		
		answer = Math.min(Math.abs(teamLink-teamStart),answer);
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		s = new int[n+1][n+1]; 
		check = new boolean [n + 1]; 
		// 배열 초기화 
		for(int i = 1; i <= n ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			for(int j = 1; j <= n ; j ++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		// 조합 
		combination(1,0,n/2); 
		
		System.out.println(answer); 
		
	}
}
```
