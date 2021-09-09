```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution10775 {
	static int g; 
	static int p ; 
	static int cnt = 0 ; 
	static int [] parent ; 
	static int find(int id ) {
		if(parent[id] == id) return id; 
		return parent[id] = find(parent[id]); 
		
	}
	
	public static void main(String []args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        parent = new int [g + 1]; 
        for(int i = 1 ; i <= g; i ++) {
        	parent [i] = i ; 
        }
        for(int i = 1; i <= p ; i ++) {

            int gate = Integer.parseInt(br.readLine());
            
        	int pa = find(gate);
        	if(pa == 0 ) break; 
        	cnt ++; 
        	update(pa, pa -1);
        }
        System.out.println(cnt); 
        
	}
	static void update(int a, int b) {
		parent [find(a)] = find(b); 
	}
	
	
}
```
