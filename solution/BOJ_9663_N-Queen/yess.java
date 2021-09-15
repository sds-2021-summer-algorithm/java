```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	// 행 1 개당 1개 , 열 1개당 1개 배치해야함. 
		static int n ; 
	static int [] queen; 
	static boolean [] check; 
	static int answer = 0 ; 
	static boolean isitRight(int now) {
		for(int i = 0 ; i < now ; i ++) {
			if(queen[i] == queen[now]) return false;
			if(Math.abs(now -i) == Math.abs(queen[now] - queen[i])) return false; 
		}
		return true; 
	}
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		queen = new int [n]; 
		putqueen(0);
		System.out.println(answer); 
	}
	static void putqueen(int cnt) {
		if(cnt == n) {
			answer ++ ; 
			return ; 
		}
		// 행 마다 돌기 
		for(int i = 0 ;  i < n ; i ++) {
			queen[cnt] = i; 	
			if(isitRight(cnt) == true ) {
				putqueen(cnt + 1); 
			}
		}
	}
	```
	
	
	
	
}
