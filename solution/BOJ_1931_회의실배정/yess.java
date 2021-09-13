```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		StringTokenizer st; 
		int [][] time = new int [n][2]; 
		for(int i =0 ; i < time.length; i ++) {
			st = new StringTokenizer(br.readLine()); 
			time[i][0] = Integer.parseInt(st.nextToken()); 
			time[i][1] = Integer.parseInt(st.nextToken()); 
		}
		Arrays.sort(time, new Comparator<int []>() {
			@Override
			public int compare (int []a , int []b) {
                if(a[1] == b[1]){
                    return a[0] - b[0];
                }
				return a[1] - b[1];
			}
		});
		int cnt = 1; 
		int endtime = time[0][1]; 
		for(int i = 1; i < time.length; i ++) {
			if(time[i][0] < endtime) continue; 
			else {
				cnt ++; 
				endtime = time[i][1]; 
			}
		}
		System.out.println(cnt); 
	}
}

```
