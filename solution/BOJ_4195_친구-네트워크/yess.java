```

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;


public class Solution4195 {
	static int testcase; 
	static int f; 
	static int [] parent ;
	static int [] countfriend ;
	static Hashtable<String, Integer> ht ;
	static int count ; 
	static BufferedWriter bw ; 

	static void union(int s, int t) throws IOException {
		int p1 = find(s); 
		int p2 = find(t); 
		if(p1 != p2) {
			parent[p1] = parent[p2]; 
			countfriend[p2] += countfriend[p1];
		}

		bw.write(String.valueOf(countfriend[p2]) + "\n");
		
	}
	static int find(int a) {
		if(a == parent[a]) return a; 
		else return parent[a] = find(parent[a]); 
	}
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		testcase = Integer.parseInt(st.nextToken()); 
		while(testcase > 0 )
		{
			testcase --; 
			f = Integer.parseInt(br.readLine()); 
			parent = new int[f * 2 + 1]; 
			countfriend = new int[f * 2 + 1]; 
			for(int i = 1; i <parent.length; i ++) {
				parent[i] = i ; 
				countfriend[i] = 1; 
			}
			ht = new Hashtable<>(); 
			count = 1; 
			for(int i = 0 ; i < f; i ++) {
				st = new StringTokenizer(br.readLine()); 
				String friend1 = st.nextToken(); 
				String friend2 = st.nextToken(); 
				ht.put(friend1,ht.getOrDefault(friend1, count ++));
				ht.put(friend2, ht.getOrDefault(friend2, count ++)); 
				union(ht.get(friend1), ht.get(friend2)); 
			}
		}
		bw.flush(); 
		bw.close(); 
		br.close();
	}
}
```
