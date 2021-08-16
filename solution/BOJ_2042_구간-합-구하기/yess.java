```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n ; 
	static int m ; 
	static int k ; 
	static long  [] num; 
	static long  [] tree;
	static int s; 
	
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		num = new long [n + 1]; 
		for(int i = 1 ; i <= n ; i ++) {
			num[i] = Long.parseLong(br.readLine());
		}
		for(s= 1; s < n ; s *= 2);
		tree = new long [2 * s]; 
		
		for(int i = 1; i <= n; i ++ ) {
			tree[s + i -1] = num[i]; 
		}
		for(int i = s-1; i > 0 ; i --) {
			tree[i] = tree[2 * i] + tree[i * 2 + 1]; 
		}
		
		for(int i =  0 ; i < m + k ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int order = Integer.parseInt(st.nextToken()); 
			if(order == 1) {
				// update
				int x  = Integer.parseInt(st.nextToken()); 
				long  y = Long.parseLong(st.nextToken()); 
				update (x, y ); 
				
			}
			else if(order == 2) {
				//query
				int x = Integer.parseInt(st.nextToken()); 
				int y = Integer.parseInt(st.nextToken());
				bw.write(String.valueOf(query(x,y)) + "\n"); 
			}
		}
		bw.flush();
		bw.close(); 
		br.close();
		
	}
	static long query(int start, int end) {
		long sum = 0 ; 
		start = start + s - 1; 
		end = end + s  -1; 
		while(start <= end) {
			if(start % 2 != 0 ) {
				sum += tree[start ++]; 
			}
			if(end % 2 == 0 )
			{
				sum += tree[end--]; 
			}
			end /= 2; 
			start /=2; 
		}
		return sum ; 
	}
	static void update(int idx, long num) {
		int index = s + idx -1; 
		tree[index] = num; 
		index = index /2; 
		while(index > 0 ) {
			tree[index] = tree[index * 2] + tree[index * 2 + 1]; 
			index = index /2; 
			
		}
		
		
	}
}
```
