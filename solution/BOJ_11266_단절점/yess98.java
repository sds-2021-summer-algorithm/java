```

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
[

public class Solution11266 {
	static int v ; 
	static int e ; 
	static ArrayList [] al ; 
	static int [] order; 
	static int [] low; 
	static int index = 1; 
	static boolean[] isit ; 
	static int count = 0 ; 

	static StringBuilder sb = new StringBuilder(); 
	static int dfs(int now, int parent, boolean isRoot ) {
		
		order[now]  = index  ++;
		int ret  = order[now];
		int size = al[now].size();
		int child =  0 ; 
		
		for(int i = 0 ; i < size; i ++) {
			int temp = (int) al[now].get(i);
			
			if(temp == parent ) continue; 
			if(order[temp] ==  0 ) {
				child ++; 
				int temp_low = dfs(temp, now, false); 

				if(!isRoot && order[now] <= temp_low) {
					isit[now] = true; 
				}
				
				ret = Math.min(temp_low, ret);
			}
			else {
				ret =  Math.min(order[temp] , ret); 
			}
			
		}
		if(isRoot && child>= 2) {
			isit[now] = true; 
		}
		return ret ; 
	}
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		v = Integer.parseInt(st.nextToken()); 
		e = Integer.parseInt(st.nextToken()); 
		al = new ArrayList[v + 1];
		order = new int[v + 1]; 
		low  = new int[ v + 1]; 
		isit = new boolean [ v  +1]; 
		for(int i = 1; i <= v ; i ++) {
			al[i] = new ArrayList<>(); 
		}
		
		for(int i = 1; i <= e; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int a  = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			al[a].add(b); 
			al[b].add(a); 
		}
		for(int i = 1; i <= v; i ++) {
			if(order[i] == 0 ) {
				dfs(i, 0 , true); 
			}
		}
		for(int i = 1; i <= v ; i ++) {
			if(isit[i] == true) {
				count ++; 
				sb.append(i + " ");
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		bw.write(count  + "\n" + sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
}
