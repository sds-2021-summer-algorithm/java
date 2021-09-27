```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	static int min = Integer.MAX_VALUE;
	static boolean [] check = new boolean [10000]; 
	static int [] primearr = new  int [10000];
	static int [] num = {1000,100,10,1};
	static Queue <Integer> queue ; 
	static StringBuilder sb = new StringBuilder(); 
	static boolean flag = false; 
	public static void makePrimeArr() {
		primearr[0] = 0;
		primearr[1] = 1;
		primearr[2] = 2;
		for(int i = 3; i < primearr.length ; i ++) {
			
			if(primearr[i] == 0) {
				primearr[i] = 2; 
			}
			for(int t = i ; t <primearr.length; t = t  +  i) {
				if(primearr[t] == 0 ) {
					primearr[t] = 1; 
				}
			}
		}
	}
	static void countStep(int now, int target) {
		flag = false; 
		queue = new ArrayDeque <>(); 
		queue.add(now); 
		check[now] = true; 
		int step = -1 ;
		if(now == target) {
			sb.append(0 + "\n"); 
			return ; 
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			step ++; 
			for(int s = 0  ; s < size; s ++ ) {
				int out = queue.poll();
			
				if(out == target) {
					sb.append(step  + "\n");
					flag = true; 
					break; 
				}
				int [] eachnum = new int[4];
				for(int i = 0 ;i < eachnum.length; i ++) {
					eachnum[i] = out/ num[i]; 
					out = out % num[i]; 
				}
				// 한번에 바꿀 수 있는 모든 값을 다 구해보기 
				for(int i = 0 ; i < 4;  i ++) {
					// 1000, 100, 10 , 1
					for(int j = 0 ; j < 10 ; j ++) {
						eachnum[i] = eachnum[i] + 1 > 9 ? 0 : eachnum[i]  + 1; 
						int next = 0 ; 
						for(int k = 0 ;  k < 4; k ++) {
							next += eachnum[k] * num[k]; 
						}
						if(check[next] == false && primearr[next] == 2 && next > 1000 && next < 9999) {
							queue.add(next); 
							check[next] = true; 
						}
					}
			
				}
			}
			
			
		}
		
		if(!flag) {
			sb.append("Impossible\n");
			return ; 
		}
	}
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine());
		StringTokenizer st ; 
		int now; 
		int target; 
		makePrimeArr(); 
		while(n > 0 ) {
			n --;
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine()); 
			now = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken()); 
			check = new boolean [10000]; 
			countStep(now,target); 
			
		}
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out)); 
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
}

```
