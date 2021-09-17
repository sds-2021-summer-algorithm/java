```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n ; 
	static int m ; 
	static StringTokenizer st ; 
	static int [] narr; 
	static int [] marr; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		 n = Integer.parseInt(br.readLine()); 
		 narr = new int[n]; 
		 st = new StringTokenizer(br.readLine()); 
		 for(int i = 0 ; i < narr.length; i ++) {
			 narr[i] = Integer.parseInt(st.nextToken()); 
		 }
	
		 m = Integer.parseInt(br.readLine()); 
		 marr = new int[m]; 
		 st = new StringTokenizer(br.readLine()); 
		 for(int i = 0 ; i < marr.length; i ++) {
			 marr[i] = Integer.parseInt(st.nextToken()); 
		 }


		 Arrays.sort(narr);
		 StringBuilder sb = new StringBuilder(); 
		 for(int i = 0 ; i < marr.length; i ++) {
			 sb.append(solution(marr[i])-solution2(marr[i]) + " ");
		 }
		 BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (System.out)); 
		 bw.write(sb.toString());
		 bw.flush();
		 bw.close();
		 br.close();
		 
	}
	static int solution2(int num) {
		int start = 0 ; 
		int end = narr.length ; 
		int half = (start + end ) /2; 
		while(start < end) {
			if(narr[half] >= num) {
				end = half;
			}
			else {
				start = half  + 1 ; 
			}
			half = (start + end) /2 ; 
		}
		return end; 
		
	}
	static int solution(int num) {
	int start = 0 ; 
	int end = narr.length ; 
	int half = (start + end) / 2 ;
	
	while(start < end) {
		if(narr[half] <=  num) {
			start = half + 1; 
		}
		else if (narr[half] > num) {
			end = half;
		}
		half = (start + end) /2; 
	}
	return end; 
}
	
}

```
