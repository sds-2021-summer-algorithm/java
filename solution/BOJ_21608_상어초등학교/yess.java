```
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Student {
	// position
	int r;
	int c; 
	// 친한친구 
	int [] friends; 
	boolean isfind = false; 
	public Student () {
		
	}
	
}
public class Main {
	static int n ; 
	// 학생 배치 
	static int [][] map; 
	// 주변에 빈자리 
	static int [][] state; 
	static Student [] student; 
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static int answer = 0 ; 
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		n = Integer.parseInt(br.readLine()); 
		student = new Student [n * n  + 1]; 
		for(int i = 1; i <= n * n  ; i ++) {
			student[i] = new Student(); 
		}
		state = new int [n][n]; 
		map = new int [n][n]; 
		// 빈자리 초기 세팅
		for(int i = 0 ; i < n ; i ++) {
			for(int j =0 ; j < n ; j ++) {
				int stat = 4; 
				if(i == 0  || i == n -1) stat --; 
				if(j == 0 || j == n-1) stat --; 
				state[i][j] = stat;
				}
			
		}
		StringTokenizer st ; 
		for(int i = 0 ; i < n * n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int id = Integer.parseInt(st.nextToken()); 
			int f1 = Integer.parseInt(st.nextToken()); 
			int f2 = Integer.parseInt(st.nextToken()); 
			int f3 = Integer.parseInt(st.nextToken()); 
			int f4 = Integer.parseInt(st.nextToken()); 
			student[id].friends = new int [] {f1,f2,f3,f4};
			// 한명씩 자리에 앉히기 
			find_seat(id, student[id].friends); 
		}
		// 점수 계산 
		calculation(); 
		System.out.println(answer); 
	}
	public static void calculation() {
		for(int i = 1; i < student.length; i ++) {
			Student s = student[i]; 
			int cnt = 0 ; 
			for(int id : s.friends) {
				int fx = student[id].r; 
				int fy = student[id].c; 
				if(Math.abs(fx - s.r) + Math.abs(fy - s.c) == 1) cnt ++; 
			}
			if(cnt == 0 ) answer += 0 ; 
			else if(cnt == 1) answer += 1; 
			else if (cnt == 2) answer += 10 ; 
			else if (cnt == 3) answer += 100; 
			else answer += 1000; 
		}
	}
	public static void find_seat(int id, int [] friends) {
		// 친한친구 많은 곳 찾기 
		int [][] near = new int [n][n]; 
		for(int i : friends) {
			// 이미 앉아 있는 친구인지 
			if(student[i].isfind == true) {
				Student s = student[i]; 
				int sx = s.r; 
				int sy = s.c; 
				for(int j = 0 ; j < dx.length ; j ++) {
					int nx = sx + dx[j];
					int ny = sy + dy[j]; 
					if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue; 
					else near[nx][ny] ++; 
				}
			}
		}

		
		
		// 1. 주변에 친구가 많은 곳 
		// 2. 그 중에서 인접한 곳이 빈칸이 많은 곳 
		// 3. 행과 열의 숫자가 작은 곳 

		// 주변에 친한친구가 몇명인지 
		int nearmax = -1;
		// 인접한 빈칸의 갯수 
		int emptymax = -1;
		// x위치, y위치
		int pos1 = -1 ; 
		int pos2 = -1 ; 
		
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < n ; j++) {
				// 사람이 있는곳은 pass 
				if(map[i][j] != 0 )continue; 
				// 인접한 곳에 친구가 있는 곳 
				if(nearmax < near[i][j] ) {
					pos1 = i ; 
					pos2 = j ; 
					nearmax = near[i][j]; 
					emptymax = state[i][j]; 
				}
				// 인접한 곳의 친구 수가 동일하다면 빈칸 수 많은 것으로 교체 
				else if(nearmax == near[i][j] && emptymax < state[i][j]) {
					pos1 = i; 
					pos2 = j ; 
					emptymax = state[i][j]; 
				}
			}
		}
		// 빈칸 수 update 
		for(int i = 0 ; i < dx.length; i ++) {
			int tx = pos1 + dx[i]; 
			int ty = pos2  + dy [i]; 
			if(tx < 0 || ty  < 0 || tx >= n  || ty >= n ) continue; 
			else state[tx][ty] -- ; 
		}
		
		map[pos1][pos2] = id; 
		student[id].r = pos1; 
		student[id].c = pos2; 
		student[id].isfind = true; 
		
	}
}
```
