```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Block{
	// 내구도 
	int val; 
	// 로봇의 존재 유무 
	boolean isRobot;
	// 0으로 이미 체크된것인지 확인 
	boolean isZero; 
	public Block(int v) {
		this.val = v;
		this.isRobot = false; 
		this.isZero = false; 
	}
}

public class Main {
	static int n; 
	static int k ; 
	static Block block []; 
	static int step = 0  ; 
	static 	BufferedReader br ;
	static BufferedWriter  bw ; 
	
	public static void main(String [] args) throws IOException {
		br = new BufferedReader (new InputStreamReader (System.in)); 
		bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		StringTokenizer st  = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken()); 
		
		block = new Block[n * 2  + 1]; 
		
		st = new StringTokenizer(br.readLine()); 
		for(int i = 1; i < block.length; i ++) {
			int v = Integer.parseInt(st.nextToken()); 
			block[i] = new Block(v); 
		}
		
		int count = 0 ; 
		
		for(;;) {
			step ++; 
			// 컨베이어 벨트 회전 
			Block last = block[2 * n ]; 
			for(int i = 2 * n -1;  i >=  1 ; i --) {
				block[i+1] = block[i]; 
				
			}
			block[1] = last; 
			
			// 가장 먼저 벨트에 올라간 로봇 부터 이동 
			for(int i = n  ; i >= 1; i --) {
				if(i == n   && block[i].isRobot == true) {
					block[i].isRobot = false; 
					continue; 
				}
				if(i == n-1 && block[i].isRobot == true && block[i + 1].isRobot == false && block[i + 1].val > 0 ) {
					
					block[i].isRobot = false; 
					block[i + 1].val --; 
					if(block[i + 1].val <= 0  && block[i + 1].isZero == false) {
						block[i + 1].isZero = true; 
						count ++; 
					}
					
				}
				else if(i < n -1  && block[i].isRobot == true && block[i + 1].isRobot == false && block[i + 1].val > 0 )
				{
					block[i].isRobot = false; 
					block[i+ 1].isRobot = true; 
					block[i + 1].val --; 
					if(block[i + 1].val <= 0  && block[i + 1].isZero == false) {
						block[i + 1].isZero = true; 
						count ++; 
					}
				}
				
				
			}
			// 올리는 위치에 있는 칸의 내구도가 0이 아니면, 현재 로봇이 없다면  올린다. 
			if(block[1].val > 0  && block[1].isRobot == false) {
				block[1].isRobot  = true;
				block[1].val --; 
				if(block[1].val <= 0 && block[1].isZero == false) {
					block[1].isZero = true; 
					count ++; 
				}
			}
			
			if(count >= k ) {
				bw.write(String.valueOf(step));
				break;
			}
		}
		bw.flush();
		bw.close(); 
		br.close();
	}
}
		
```

