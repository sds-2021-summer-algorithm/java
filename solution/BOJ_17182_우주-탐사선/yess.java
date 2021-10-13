import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Solution17182 {
	static int n ; 
	static int k ; 
	static int answer = Integer.MAX_VALUE ; 
	static int [][] map; 
	static boolean [] visited; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		map = new int [n][n]; 
		for(int i = 0 ; i < n; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		// 플로이드 
		
		for(int k = 0 ; k < n ; k ++) {
			for(int i = 0 ; i < n ; i ++) {
				for(int j = 0 ; j < n ; j ++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]); 
				}
			}
		}
		
		// dfs 
		visited = new boolean [n]; 
		visited [k] = true; 
		dfs(k,0, 0, n - 1); 
		
		System.out.println(answer); 
		
		
	}
	static void dfs(int i, int temp, int depth, int target) {

		if (depth == target) {
			answer  = Math.min(answer, temp); 
			return ; 
		}
		for(int j = 0 ; j < n ; j ++) {
			if (visited[j] == false) {
				visited[j] = true; 
				dfs(j, temp + map[i][j], depth + 1, target); 
				visited[j] = false; 
			}
		}
	}
}
