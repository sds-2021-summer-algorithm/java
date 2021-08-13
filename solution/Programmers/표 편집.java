
import java.util.Stack;
// StringBuilder 연습할 수 있는 문제 
class Solution {
    static int n ; 
    static int k ; 
	static StringBuilder sb = new StringBuilder();
	static int startidx ; 
	static Stack<Integer> stack = new Stack<>(); 
    static int totsize; 
    
    void down(int idx) {
		startidx = startidx  + idx; 
	}
     void up (int idx) {
		startidx -= idx;
	}
    public String solution(int n, int k, String[] cmd) {
    	totsize = n ; 
		startidx = k ; 
		for(int i = 0 ; i < cmd.length ; i ++) {
			String order = cmd[i]; 
			if(order.charAt(0) == 'D') {
				// 칸 내리기 
				int idx = Integer.parseInt(order.substring(2));
				down(idx); 
			}
			if(order.charAt(0) == 'U') {
				// 칸 위로 올리기 
				int idx = Integer.parseInt(order.substring(2));
				up(idx); 
			}
			if(order.charAt(0) == 'Z') {
				// 복구 
				int pop = stack.pop();
				if(startidx >= pop) startidx ++; 
				totsize ++; 
			}
			if(order.charAt(0) == 'C') {
				// 삭제 
				int id = startidx; 
				stack.add(id); 
				totsize --; 
				if(id == totsize) startidx--; 
			}
		}
		for(int i= 0 ; i < totsize; i ++) {
			sb.append("O"); 
		}
		while(!stack.isEmpty()) {
			int  intern = stack.pop(); 
			sb.insert(intern,"X"); 
		}
		
	
		return sb.toString();
    }
}
