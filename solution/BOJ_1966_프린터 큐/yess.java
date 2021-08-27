```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
class Print {
	int id; 
	int value; 
	public Print(int id, int v) {
		this.id = id; 
		this.value = v; 
	}
	
}
public class Main {
	static int testcase; 
	static int n ; 
	static int m ; 
	static BufferedReader br ; 
	static BufferedWriter bw; 
	static StringBuilder sb; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in)); 
		bw = new BufferedWriter (new OutputStreamWriter(System.out)); 
		sb = new StringBuilder(); 
		testcase = Integer.parseInt(br.readLine()); 
		StringTokenizer st; 
		LinkedList <Print> queue ; 
		while(testcase > 0 ) {
			testcase -- ;
			queue = new LinkedList <>(); 
			st = new StringTokenizer(br.readLine()); 
			n = Integer.parseInt(st.nextToken()); 
			m = Integer.parseInt(st.nextToken()); 
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n ; i ++) {
				int val = Integer.parseInt(st.nextToken()); 
				queue.offer(new Print(i,val)); 
			}
			int cnt = 0  ;

			while(!queue.isEmpty()) {
				boolean isfind = true; 
				Print p = queue.poll();
				for(Print prt : queue) {
					if(prt.value > p.value) {
						isfind = false; 
						break; 
					}
				}
				if(isfind) {
					cnt ++; 
					if(p.id == m) {
						System.out.println(cnt); 
						break;
					}
				
				}
				else {
					queue.add(p); 
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
