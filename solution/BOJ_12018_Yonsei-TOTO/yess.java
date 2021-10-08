```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	// 수강 인원 다 안찬거 먼저, 
	// 수강 인원이 다 찬거면 최대 마일리지기 작은거 먼저
	static int n ; 
	static int m ;
	static int [][] classes; 
	static int answer = 0 ; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		
		classes = new int [n][2];
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int std = Integer.parseInt(st.nextToken());
			int tot = Integer.parseInt(st.nextToken());
			classes[i][0] = tot - std;
			ArrayList <Integer>al = new ArrayList <>();
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ;  j <  std; j ++){
				al.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(al,Collections.reverseOrder());
			if (tot <= std) classes[i][1] = al.get(tot - 1); 
			else classes[i][1] = 1; 
		}
		Arrays.sort(classes, new Comparator<int []> () {
			@Override 
			public int compare (int [] a, int [] b) {
				if (a[0] <= 0 && b[0] <= 0 ) {
					return a[1] - b[1]; 
				}
				return b[0] - a[0];
			}
		});
		
		for(int i = 0 ; i < n ; i ++ ) {
				if (classes[i][0] > 0 && m - 1 >= 0  ) {
					m--; 
					answer ++; 
					continue; 
				}
				if (m - classes[i][1] >= 0 ) {
					m = m - classes[i][1];
					answer ++;
					continue; 
				}
		}
		System.out.println(answer); 
		
		
	}
}

```
