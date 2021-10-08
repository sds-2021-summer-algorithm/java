```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	// t->s 를 만든다. 
	// a로 끝나면 방법1을 쓴것이고 b로 끝나면 방법 2를 쓴것이다. 
	static String s; 
	static String t; 
	static int lengths; 
	static int lengtht;
	static boolean flag = true; 
	static Queue<String > queue = new ArrayDeque<>(); 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		s = br.readLine(); 
		t = br.readLine();
		lengths = s.length();
		lengtht = t.length();
		
		ArrayList<Character> al = new ArrayList <>(); 
		for(int i = 0 ; i < lengtht; i ++) {
			al.add(t.charAt(i)); 
		}
		int idx = al.size() -1; 
		while (al.size() > s.length()) {
			if (al.get(idx) == 'A') {
				al.remove(idx); 
			}
			else if (al.get(idx) == 'B') {
				al.remove(idx);
				al = reverse(al); 
			}
			idx --; 
		}
		for(int i = 0 ; i < al.size(); i ++) {
			if(al.get(i) != s.charAt(i)) {
				flag = false; 
				break; 
			}
		}
		
		if (flag == true) System.out.println(1); 
		else System.out.println(0);
		
	}
	static ArrayList reverse(ArrayList al) {
		ArrayList<Character> newal = new ArrayList<>(); 
		for(int i = al.size() - 1 ; i >= 0;i --) {
			newal.add((Character) al.get(i));
		}
		return newal;
	}

}

```
