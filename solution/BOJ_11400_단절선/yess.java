import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable <Edge> {
	int first; 
	int second; 
	public Edge(int f, int s) {
		this.first = f; 
		this.second = s; 
		
	}
	@Override
	public int compareTo(Edge e) {
		if(this.first == e.first) {
			return this.second - e.second; 
		}
		return this.first- e.first; 
	}
}
public class Main{
	// 단절선 문제 
	// dfs기반 order, low 를 구하기 
	
	static int v; 
	static int e; 
	static int [] order; 
	static ArrayList [] al ; 
	static int index = 1; 
	static boolean [] isit ;
	static StringBuilder sb = new StringBuilder (); 
	static PriorityQueue<Edge> pq = new PriorityQueue<>(); 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		v = Integer.parseInt(st.nextToken()); 
		e = Integer.parseInt(st.nextToken()); 
		isit  = new boolean [ v + 1]; 
		order = new int [v + 1]; 
		al = new ArrayList [v + 1]; 
		for(int i = 1; i <= v; i ++) {
			al [i] = new ArrayList<>(); 
		}
		for(int i = 1; i <= e; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			
			al[a].add(b); 
			al[b].add(a); 
			
		}
		dfs(1,0); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		sb.append(pq.size() + "\n"); 
		while(!pq.isEmpty()) {
			sb.append(pq.peek().first  + " " + pq.peek().second); 
			pq.poll();
			sb.append("\n"); 
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	static int dfs(int now, int parent) {
		order[now] = index ++; 
		int ret = order[now]; 
		int size = al[now].size();
		for(int i = 0 ; i < size; i ++) {
			int next = (int) al[now].get(i); 
			if(next == parent) continue; 
			if(order[next] == 0 ) {
				 int low  = dfs(next, now); 
				 if( low > order[now] ) {
					 int first = Math.min(next, now); 
					 int second = Math.max(next, now); 
					 pq.add(new Edge(first, second)); 
				 }
				 ret = Math.min(low, ret); 
			}
			else {
				ret = Math.min(order[next], ret); 
			}
		}
		return ret; 
	}
}
