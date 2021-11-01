```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int n; 
	static int [] status ; 
	static int student; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in)); 
		n = Integer.parseInt(br.readLine()); 
		status = new int [n + 1];
		StringTokenizer st  = new StringTokenizer(br.readLine()); 
		
		for(int  i = 1; i <= n ; i ++) {
			status[i] = Integer.parseInt(st.nextToken());
		}
		student = Integer.parseInt(br.readLine()); 
		for(int i = 0 ; i < student; i  ++) {
			st = new StringTokenizer(br.readLine()); 
			int sex = Integer.parseInt(st.nextToken()); 
			int pos = Integer.parseInt(st.nextToken()); 
			if(sex == 1) {
				changestatus (pos);
		
			}
			else {
				changestatus2(pos); 
			

			}
			
		}

				print(); 
	
	
	}
	static void print() {
		for(int i=1; i<status.length; i++) {
			System.out.print(status[i] + " ");
			if((i) % 20 == 0)
				System.out.println();
		}
	}
	static void changestatus2(int pos) {
		int tot_len = pos * 2;
		while(pos > 0 && tot_len - pos <= status.length - 1 ) {
		
			if (status[pos] == status[tot_len - pos] && pos != tot_len -pos) {
				 status[pos] = status[pos] == 0 ? 1 : 0 ; 
				 status[tot_len -pos] = status[tot_len -pos] == 0 ? 1 : 0 ; 
			 }
			 else if (status[pos] == status[tot_len - pos] && pos == tot_len -pos) {
				 status[pos] = status[pos] == 0 ? 1 : 0 ; 
			 }
			 else if(status[pos] != status[tot_len - pos]) break; 
			 pos --; 
		 }
	}
	static void changestatus(int pos) {
		for(int i = pos ; i < status.length; i = i + pos) {
			if (status [i] ==  0) status[i] = 1; 
			else status[i] = 0; 
		}
	}
}

```
