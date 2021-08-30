```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball {
	int rx; 
	int ry; 
	int bx; 
	int by; 
	int depth ; 
	
	public Ball(int rx, int ry, int bx, int by, int d) {
		this.rx = rx; 
		this.ry = ry; 
		this.bx = bx;
		this.by = by; 
		this.depth = d; 
	}
	public Ball() {
		
	}
}
public class Main {

	static int n ; 
	static int m ; 
	static char [][] map; 
	static int [] x = {1,-1,0,0};
	static int [] y = {0,0,1,-1};
	static Queue<Ball> queue = new ArrayDeque<>(); 
	static boolean [][][][] check ; 
	
	static int  bfs() {
		
		while(!queue.isEmpty()) {
			Ball now  = queue.poll(); 
			//System.out.println(now.depth); 
			if(now.depth > 10) {
				// 10번 이동 했을때 
				return -1; 
			}
			for(int i = 0 ; i < x.length; i ++) {
				// 상하좌우 이동 
				
				int nx = now.bx;
				int ny = now.by; 
				boolean bluehole = false; 
				
				while(map[nx + x[i]][ny + y[i]] != '#') {
					nx = nx + x[i]; 
					ny = ny + y[i]; 
					if(map[nx][ny] == 'O') {
						bluehole = true; 
						break; 
					}
					
				}
				
				int nx2 = now.rx;
				int ny2 = now.ry; 
				boolean redhole = false; 
				while(map[nx2 + x[i]][ny2 + y[i]] != '#') {
					nx2 = nx2 + x[i]; 
					ny2 = ny2 + y[i]; 
					if(map[nx2][ny2] == 'O') {
						redhole = true; 
						break; 
					}
				}
				
				//System.out.println(nx  + "," + ny  +  "   "  +  nx2 +"," + ny2); 
				if(redhole == true && bluehole == false) {
					// 빨간색이 구멍에 들어갔을때
					// 파란색도 안들어가야지 성공 
					return now.depth; 
				}
				if(bluehole == true) {
					// 파란색이 들어갔을때 실패 
					// 다른 방법은 가능한지 계속 check; 
					continue; 
				}
				
				if(nx == nx2 && ny == ny2) {
					// 둘의 위치가 같을때 위치조정
					if(i == 0 ) {
						// 아래
						if(now.bx > now.rx) {
							nx2 -= x[i]; 
						}
						else {
							nx -=x[i]; 
						}
					}
					else if( i == 1) {
						//위
						if(now.bx > now.rx) {
							nx -= x[i]; 
						}
						else {
							nx2 -= x[i]; 
						}
					}
					else if(i == 2) {
						//오
					
						if(now.by > now.ry) {
							ny2 -= y[i]; 
						}
						else {
							ny -=y[i]; 
						}
					}
					else {
						//왼
						if(now.by > now.ry) {
							ny -= y[i]; 
						}
						else {
							ny2 -=y[i]; 
						}
					}
					
				}
				// 이동한 가능한 점 queue 에 넣기 
				if(check[nx2][ny2][nx][ny] == false) {
					check[nx2][ny2][nx][ny]  = true; 
					queue.add(new Ball(nx2,ny2,nx,ny,now.depth + 1)); 
				}
			}
			
			
			
		
		}
		return -1; 
	}
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); 
		map = new char[n + 1][m + 1]; 
		check = new boolean [ n +1] [m + 1] [n + 1][m + 1]; 
		Ball ball = new Ball(); 
		ball.depth = 1 ; 
		for(int i = 1 ; i <= n ; i ++) {
			String s = br.readLine(); 
			for(int j = 1; j <= m ; j ++) {
				map[i][j] = s.charAt(j-1); 
				if(map[i][j] == 'R') {
					ball.rx = i; 
					ball.ry = j ; 
				}
				else if(map[i][j] == 'B') {
					ball.bx = i ; 
					ball.by  = j ; 
				}
			}
		}
		queue.add(ball); 
		check[ball.rx][ball.ry][ball.bx][ball.by] = true;
		
		int answer = bfs();
		System.out.println(answer); 
		
		
	}
}
```
