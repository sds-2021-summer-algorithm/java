```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution12904 {
	static String s; 
	static String t; 
	static int lengths; 
	static int lengtht;
	static boolean flag = false; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		s = br.readLine(); 
		t = br.readLine();
		lengths = s.length();
		lengtht = t.length();
		dfs(lengths, lengtht,s, t);
		if (flag == true) System.out.println(1); 
		else System.out.println(0);
		
	}
	static void dfs(int ls, int lt ,String strs, String strt ) {
		if (ls == lt) {
			if (strs.equals(strt)) {
				flag = true; 
				return ;
			}
		}
		if (ls > lt) return ; 
		if (flag == true ) {
			return ; 
		}
		if (ls > lt || (ls == lt  && !strs.equals(strt)))
		{
			flag = false; 
			return ; 
		}
		StringBuilder sb = new StringBuilder(strs); 
		dfs(ls + 1, lt, sb.toString() + "A", strt);
		dfs(ls + 1, lt , sb.reverse().toString() + "B", strt); 
		
	}
}
```
