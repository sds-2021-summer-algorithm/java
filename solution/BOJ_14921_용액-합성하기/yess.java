```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	static int [] A; 
	static int point1; 
	static int point2; 
	static int INF = 100000000 * 2; 
	static int answer1 = 0 ; 
	static int answer2  = 0 ; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		A = new int [n]; 
		for(int i = 0 ; i < n ; i ++) {
			A[i] = Integer.parseInt(st.nextToken()); 
		}
		point1 = 0; 
		point2 = A.length -1; 
		
		while(true) {
			if(point1 >=  point2 ) {
				break;
			}
			int sum = A[point1] + A[point2]; 
			if(Math.abs(sum) < INF) {
				INF = Math.abs(sum); 
                answer1 = point1; 
                answer2 = point2; 
				
			}
			if(Math.abs(A[point1] + A[point2]) >= Math.abs(A[point1] + A[point2 -1])) {
				point2 --; 
			}
			else if(Math.abs(A[point1] + A[point2]) >= Math.abs(A[point1 + 1] + A[point2])) {
				point1 ++; 
			}
			else {
				point2 --; 
			}
			
		} 
        System.out.println(A[answer1] + A[answer2]); 
	}
}
```
