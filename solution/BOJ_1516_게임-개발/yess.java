
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Building{
	int id; 
	int cost; 
	int sum; 
	public Building(int id, int cost, int sum ) {
		this.id = id; 
		this.sum  = sum ; 
		this.cost  = cost; 
	}
}
public class Main {

	static int n ; 
	static ArrayList [] al ;
	static int [] indegree; 
	static Queue<Building>queue; 
	static Building[] building; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in)); 
		n = Integer.parseInt(br.readLine()); 
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder (); 
		queue = new ArrayDeque<>(); 
		building = new Building[n + 1]; 
	
		al = new ArrayList [n + 1]; 
		indegree = new int[n + 1]; 
		for(int i = 1; i <= n ; i ++) {
			al[i] = new ArrayList<Building>(); 
		}
		for(int i = 1 ; i <= n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int c = Integer.parseInt(st.nextToken());
			building[i] = new Building(i,c,0); 
			int next = Integer.parseInt(st.nextToken());
		 
			while(next != -1 ) {
				indegree[i] ++;
				al[next].add(new Building(i,c,0)); 
				next = Integer.parseInt(st.nextToken()); 
			}
			
		}
		for(int i = 1; i <= n ; i ++) {
			if(indegree[i] == 0 ) {
				queue.add(building[i]); 
			}
		}
		while (!queue.isEmpty()) {
			Building now = queue.poll(); 
			building[now.id].sum   +=  building[now.id].cost;
			
			int size = al[now.id].size(); 
			for(int i = 0 ; i < size; i ++ ) {
				Building temp = (Building) al[now.id].get(i); 
				indegree[temp.id] --; 
				building[temp.id].sum  = Math.max(building[temp.id].sum,building[now.id].sum); 
				if(indegree[temp.id] == 0 ) {
					queue.add(building[temp.id]); 
				}
			}
			
		}
		for(int i = 1; i <= n ; i ++) {
			System.out.println(building[i].sum); 
		}
		
			
	}
		
	
}
