package P13460;

import java.io.*;
import java.util.*;


public class Main {
	
	static int N,M;
	static char[][] biz;
	static boolean[][][][] visited;
	
	//상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static class Ball {
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;
		
		Ball() {
			
		}
		
		Ball(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx; 
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        biz = new char[N][M];
        //[빨간구슬 i위치][빨간구슬 j위치][파란구슬 i위치][파란구슬 j위치]
        visited = new boolean[N][M][N][M];
        Ball ball = new Ball();
        
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
        	for (int j = 0; j < M; j++) {
        		biz[i][j] = s.charAt(j);
        		if (biz[i][j] == 'R') { //빨간 구슬 위치
        			ball.rx = i;
        			ball.ry = j;
        		
        			
        		} else if (biz[i][j] == 'B') { // 파란 구슬 위치
        			ball.bx = i;
        			ball.by = j;
        			
        		}
          	}
        }
        
        ball.cnt = 0;
        bfs(ball);

		br.close();
        
	}
	
	static void bfs(Ball ball) {
		Queue<Ball> q = new LinkedList<>();
		q.offer(ball);
		
		while (!q.isEmpty()) {
			Ball b  = q.poll();
			visited[ball.rx][ball.ry][ball.bx][ball.by] = true;
			
			if (ball.cnt >= 10) {
				System.out.println(-1);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int rx = ball.rx;
				int ry = ball.ry;

				while (biz[rx + dx[i]][ry + dy[i]] != '#') {
					rx += dx[i];
					ry += dy[i];
					if (biz[rx][ry] == 'O') {
						break;
					}
				}

				int bx = ball.bx;
				int by = ball.by;

				while (biz[bx + dx[i]][by + dy[i]] != '#') {
					bx += dx[i];
					by += dy[i];
					if (biz[bx][by] == 'O') {
						break;
					}
				}

				if (biz[bx][by] == 'O') {
					continue;
				}
				if (biz[rx][ry] == 'O') {
					System.out.println(ball.cnt + 1);
					return;
				}

				if (rx == bx && ry == by) {
					switch (i) {
					case 0:
						if (ball.rx < ball.bx) {
							bx += 1;
						} else {
							rx += 1;
						}
						break;
					case 1:
						if (ball.ry < ball.by) {
							ry -= 1;
						} else {
							by -= 1;
						}
						break;
					case 2:
						if (ball.rx < ball.bx) {
							rx -= 1;
						} else {
							bx -= 1;
						}
						break;
					case 3:
						if (ball.ry < ball.by) {
							by += 1;
						} else {
							ry += 1;
						}
						break;
					}
				}
				
				if(!visited[rx][ry][bx][by]) {
					q.offer(new Ball(rx,ry,bx,by,ball.cnt+1));
				}
			}

		}
		System.out.println(-1);
	}
	

}


