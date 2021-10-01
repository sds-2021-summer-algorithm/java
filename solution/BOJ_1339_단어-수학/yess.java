```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 자리 수 높은 자리에 단어 많은게 높은 값을 가져야 한다. 
// 무조건 cnt만 체크해서는 안됨. 

public class Main {
	// 단어 수학
	static int n;
	static int[] count  = new int ['Z'-'A' + 1]; 
	static int answer = 0; 
	static ArrayList <String> al  = new ArrayList<>(); 
	public static void main(String [] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine());
		
		while (n-->0) {
			String s = br.readLine();
			al.add(s);
		}
		for(int i = 0 ; i < al.size(); i ++) {
			String s = al.get(i);
			int temp = (int) Math.pow(10,s.length() - 1); // 5자리면 10의 4제곱 
			for(int j = 0 ; j < s.length(); j ++) {
				char c = s.charAt(j); 
				count[c - 'A'] += temp;
				temp /= 10 ; 
			}
		}
		Arrays.sort(count);
		int idx = 0;
		for(int i = count.length -1 ; i >= 0 ; i --) {
			if (count[i] == 0) break;
			answer += count[i] * (9 - idx);
			idx ++;
		}
		System.out.println(answer); 
	}	
	
}
```
