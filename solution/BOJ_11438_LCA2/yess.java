```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Tree{
	ArrayList<Integer>al ; 
	public Tree() {
		al = new ArrayList<>(); 
	}
}
public class Solution11438 {
	static int n ; 
	static int m ; 
	static int [][] parent; 
	static int [] depth ; 
	static Tree [] tree;
	static boolean [] check ;
	static int k ; 
	
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		StringTokenizer st ; 
		StringBuilder sb  = new StringBuilder(); 
		tree = new Tree[n + 1]; 
		depth  = new int [n + 1];  
		for(int i = 0 ; i < tree.length; i ++) {
			tree[i] = new Tree(); 
		}
		for(int i = 0 ; i < n -1 ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			tree[a].al.add(b); 
			tree[b].al.add(a); 
		}
		check = new boolean [n + 1]; 
		// 최대 높이 구하기 
		int temp = 1; 
		 k = 0 ; 
		while (temp <= n) {
			temp <<= 1; 
			k++; 
		}
		parent = new int [n + 1][ k ]; 
		dfs_depth(1,1); 
		init_parent(); 
		m = Integer.parseInt(br.readLine()); 
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			sb.append(find_lca(a,b) + "\n"); 
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int find_lca(int a , int b) {
		int answer = 0 ; 
		int da = depth[a]; 
		int db = depth[b]; 
		if (da < db ) {
			int temp = a; 
			a = b; 
			b = temp; 
		} 
		// da < db 인 경우 a랑 b랑 값을 바꿔주고, depth[a] - depth [b] 
		for(int i = k-1 ; i >= 0 ;  i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parent[a][i]; 
			}
		}
		if (a == b ) return a; 
		for(int i = k - 1; i >= 0 ; i --) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i]; 
				b = parent[b][i]; 
			}
		}
		return parent[a][0]; 
		
		
	}
	
	static void print() {
		for(int i = 1; i <parent.length; i ++) {
			for(int j = 0 ; j< parent[0].length; j ++) {
				System.out.print(parent[i][j] + " ");
				}
			System.out.println();
		}
	}
	static void init_parent() {
		for(int i = 1 ; i < parent[0].length; i ++) {
			for(int k = 1 ; k <= n; k ++) {
				parent[k][i] = parent[parent[k][i-1]][i-1];
			}
		}
		
	}
	
	static void dfs_depth(int node, int d) {
		depth[node] = d; 
		check[node] = true; 
		int size = tree[node].al.size(); 
		for(int i = 0 ; i < size; i ++) {
			int next = tree[node].al.get(i); 
			if (check[next] == false) {
				parent[next][0] = node; 
				dfs_depth(next, d + 1); 
			}
		}
	}
}
```
