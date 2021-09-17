```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int T; 
	static int n ; 
	static int [] num; 
	static String p; 
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		T = Integer.parseInt(br.readLine()); 
		while(T > 0 ) {
			T --; 
			p = br.readLine(); 
			n = Integer.parseInt(br.readLine()); 
			
			num = new int [n]; 
			String s = br.readLine(); 
			
			if(n == 0 ) {
				if(p.contains("D")) {
					sb.append("error"); 
					sb.append("\n"); 
				}
				else {
					sb.append("[]"); 
					sb.append("\n"); 
				}
				
				continue; 
			}
			String [] temp = s.replaceAll("[\\[\\]]","").split(","); 
			
			executeFunction(p,temp, 0, n, false); 
			
			
		}
		System.out.println(sb.toString()); 
	}
	static void executeFunction(String p, String [] temp , int frontidx, int backidx,  boolean back) {
		boolean iserror = false; 
    
    for(int i = 0 ; i < p.length(); i ++) {
			if(p.charAt(i) == 'R') {
				back = back == false ? true: false; 
			}
			else {
					if(frontidx == backidx) {
						iserror = true; 
						break; 
					}
					
					if(back) {
						backidx --;
					}
					else {
						frontidx ++;
					}
			}
		}
        if(iserror) {
        	sb.append("error\n"); 
        	return; 
        }
		// 최종 결과 출력 
        else{
		sb.append("[");
		if(back == true ) {
			for(int i = backidx  - 1; i >= frontidx; i --) {
				
				if(i == frontidx ) {
					sb.append(temp[i]);
					break; 
				}
				sb.append(temp[i] + ",");
				
			}
		}
		else {
		 for(int i = frontidx ; i < backidx; i  ++ ) {
			if(i == backidx -1 ) {
				sb.append(temp[i]);
				break; 
			}
			sb.append(temp[i] + ",");
				
			}
		}
		sb.append("]\n");
       }
	}
}

```
