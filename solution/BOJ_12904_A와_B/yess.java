import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//문자열뒤에 a추가 
	//문자열을 뒤집고 뒤에 b추가 
	static boolean flag = false; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String s = br.readLine(); 
		String t = br.readLine(); 
		int len = t.length() - s.length(); 
		StringBuilder sb = new StringBuilder (t); 
		while (len >= 0 ) {
			if (sb.toString().equals(s)) {
				flag = true; 
				break;
			}
			if (sb.charAt(sb.length()-1) == 'B') {
				sb.setLength(sb.length()-1);
				sb.reverse();
			}
			else {
				sb.setLength(sb.length()-1);
			}
			len --;
		}
		System.out.println(flag? 1:0);
		
	}
	static void dfs(StringBuilder sb, int cnt, int len, String target) {
		if (flag) return; 
        if (cnt == len ) {
			if (sb.toString().equals(target)) {
				flag =true; 
			}
			return ; 
		}
		
		sb.append("A");
		dfs(sb,cnt+ 1, len,target); 
		sb.setLength(sb.length() - 1);
		
		sb.reverse().append("B");
		dfs(sb,cnt + 1, len,target); 
		sb.setLength(sb.length() -1);
		sb.reverse();
	}
}
