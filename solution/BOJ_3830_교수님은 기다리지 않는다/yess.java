```

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n ; // 샘플종류 <= 100000
	static int m ; // 한일 <= 100000
	
	// union-find
	static int parent[]; 
	
	// 무게담을 배열 
	static int [] value; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));;
	static StringTokenizer st;
	static StringBuilder sb; 
	static int find(int a) {
		if(parent[a] == a) return a; 
		else {
			int pid  = find(parent[a]); 
			value[a] += value[parent[a]];
			return parent[a] = pid; 
		}
	}
	static void union(int a, int b, int c ) {
		int pa = find(a); 
		int pb = find(b); 
		if(pa != pb) {
			// a 가 작은 값 
			parent[pa] = pb;
			value[pa] = value[b] - value [a] + c; 
		}
		
	}
	public static void main(String[] args) throws Exception {
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
	 
	    sb = new StringBuilder(); 
	    for(;;) {
	    st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken()); 
	    m = Integer.parseInt(st.nextToken()); 
	    if(n == 0  && m == 0 )break;
	    
	    parent = new int [n + 1];
	    for (int i = 1; i <= n ; i ++) {
	    	parent[i] = i ; 
	    	
	    }
	    value = new int [n + 1]; 
	    for(int i = 1 ; i <= m ; i ++) {
	    	st = new StringTokenizer(br.readLine()); 
	    	if(st.nextToken().equals("!")) {
	    		int a = Integer.parseInt(st.nextToken()); 
	    		int b = Integer.parseInt(st.nextToken()); 
	    		int c = Integer.parseInt(st.nextToken()); 
	    		union(a,b,c); 
	    	}
	    	else {
	    		int a = Integer.parseInt(st.nextToken()); 
	    		int b = Integer.parseInt(st.nextToken()); 
	    		if(find(a) != find(b)) 
	    			{
	    			sb.append("UNKNOWN");
	    			sb.append("\n"); 
	    			}
	    		else {
	    			sb.append(value[a]-value[b]);
	    			sb.append("\n"); 
	    		}
	    		
	    	}
	    }
	    
	   }
	    
	    bw.write(sb.toString());
	    bw.flush();
	    bw.close();
	    br.close();
	}
}

```
