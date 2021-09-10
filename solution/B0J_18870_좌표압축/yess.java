```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Main{

	static int n ; 
	public static void main(String [] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		Long arr[] = new Long [n]; 
		StringBuilder sb = new StringBuilder(); 
		
		Hashtable <Long, Integer> ht = new Hashtable<>(); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			arr[i] = Long.parseLong(st.nextToken()); 
		}
		Long arr_copy[] = arr.clone();
		Arrays.sort(arr_copy);
		int idx = 0 ; 
		for(Long l : arr_copy) {
			if(!ht.containsKey(l)) {
				ht.put(l, idx ++); 
			}
		}
		for(int i = 0 ; i < n ; i ++) {
			sb.append(ht.get(arr[i])); 
			sb.append(" "); 
		}
		System.out.println(sb.toString()); 
		
	}
}

```
