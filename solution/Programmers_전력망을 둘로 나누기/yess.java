```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
    	static int answer = Integer.MAX_VALUE; 
	static int [][] edges; 
	static boolean [] check ; 
	static int cnt; 
	static int len; 
    public int solution(int n, int[][] wires) {
     edges = new int [n + 1][n + 1]; 
		len = n ; 
		for(int i = 0 ; i < wires.length; i ++) {
			int from  = wires[i][0]; 
			int to  = wires[i][1]; 
			edges[from][to] = 1; 
			edges[to][from] = 1; 
		}
		for(int i = 0 ; i < wires.length; i ++) {
			edges[wires[i][0]][wires[i][1]] = 0 ; 
			edges[wires[i][1]][wires[i][0]] = 0 ;
			check = new boolean [n + 1]; 
			cnt  = 0 ; 
			dfs(1); 
			answer = Math.min(answer, Math.abs((len-cnt)-cnt));
			edges[wires[i][0]][wires[i][1]] = 1 ; 
			edges[wires[i][1]][wires[i][0]] = 1 ;
		}
		return answer; 
    }
    void dfs(int idx) {
		check[idx] = true; 
		cnt ++; 
		for(int i = 1; i < check.length; i ++) {
			if(edges[idx][i] == 1 && check[i] == false) {
				dfs(i); 
			}
		}
	}
}
```
