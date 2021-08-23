package P14502;

import java.io.*;
import java.util.*;


public class Main {
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M; // 연구실 크기 N X M
	static int[][] map; //연구실
	static int[][] copy; // 연구실 복사본
	static int max = 0; // 최대 영역 개수
	
	//상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	// 바이러스 위치 저장
	static ArrayList<Point> vList;

	
	public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken()); //가로 크기
      M = Integer.parseInt(st.nextToken()); //세로 크기
      
      map = new int[N+1][M+1];
      copy = new int[N+1][M+1];
      vList = new ArrayList<Point>();
      
      for (int i = 0; i < N; i++) {
    	  st = new StringTokenizer(br.readLine());
    	  for (int j = 0; j < M; j++) {
    		  map[i][j] = Integer.parseInt(st.nextToken());
    		  if (map[i][j] == 2) {
    			  vList.add(new Point(i, j));
    		  }
    		  
    	  }
      } //연구실 입력
      
      
      dfs(0);
      
      bw.write(max + "\n");
      
      bw.flush();
      bw.close();
      br.close();
      
	}
	
	//벽을 세우는 함수 --> dfs
	public static void dfs(int cnt) {
		//벽을 3개 세운 경우
		if (cnt == 3) {
			bfs(); //바이러스 퍼트리기
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) { // 빈칸이라면
					map[i][j] = 1; // 벽 세우기
					dfs(cnt + 1);
					map[i][j] = 0; // 다음 경우의 수를 위해서 복구!
				}
			}
		}
	}
	
	// 바이러스를 퍼트리는 함수 --> bfs
	public static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j]; //map 복사
			}
		}
		
		for (int i = 0; i < vList.size(); i++) {
			q.add(new Point(vList.get(i).x, vList.get(i).y));
		} //입력시 저장했던 바이러스를 큐에 담는 과정
			
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 2) { // 바이러스가 있다면
					q.add(new Point(i,j)); //큐에 넣기
				}
			}
		}
		
		while (!q.isEmpty()) {
			Point p = q.poll(); //큐에서 꺼내기
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < 4; i++) { // 동서남북 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) { //범위 안에 들어오면
					if (copy[nx][ny] == 0) {
						copy[nx][ny] = 2;
						q.add(new Point(nx,ny));
					}
					
				}
			}
		}
		max = Math.max(max, safe());
	}
	
	//안전영역을 구하는 함수
	public static int safe() {
		int cnt = 0;
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=M; j++) {
				if (copy[i][j] == 0) { // 빈칸이라면
					cnt ++;
				}
			}
		}
		return cnt;
			
	}
	
}