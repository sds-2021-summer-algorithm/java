```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	static int m ; 
	static int [][] map;
	static int [][] checkmap; 
	static int wallcount = 0; 
	static int viruscount = 0 ; 
	static LinkedList <int []> virus_candidate = new LinkedList < int [] > (); 
	static ArrayList < int [] > wall_position = new ArrayList < int [] >(); 
	static boolean [] virus_candidate_check;
	static boolean [][] check ; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int available; 
	static Queue <int [] > queue = new ArrayDeque <int []>(); 
	static int time; 
	static int answer = Integer.MAX_VALUE ;
	static boolean flag = false; 
	static int blankcount = 0 ; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		map = new int [n][n]; 
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				//바이러스 설치 할 수 있는곳 저장시켜두기 
				if (map[i][j] == 2) 
					{
						virus_candidate.add(new int [] {i,j});
						viruscount ++; 
					}
				// 벽 개수 체크
				if (map[i][j] == 1) 
					{
						wall_position.add(new int [] {i,j}); 
						wallcount ++; 
					}
			}
		}
		virus_candidate_check = new boolean [virus_candidate.size()]; 
		
		// 빈칸의 갯수 = n * n - wallcount  - viruscount ; 
		blankcount = n * n  - wallcount - viruscount; 
		choose_position(0, 0);


		if (flag == false) System.out.println(-1); 
		else System.out.println(answer); 
		
	}
	
	
	
	static void put_virus () {
		checkmap = new int[n][n]; 
		queue = new ArrayDeque <int []>(); 
		check = new boolean [n][n]; 
		for(int i =  0 ; i < wall_position.size(); i ++) {
			int [] temp = wall_position.get(i); 
			checkmap[temp[0]][temp[1]] = -1 ;
		}
		for(int i = 0 ; i < virus_candidate_check.length; i ++) {
			if (virus_candidate_check[i] == true ) {
				int [] pos = virus_candidate.get(i); 
				checkmap[pos[0]][pos[1]] = 0;
				queue.add(new int[] {pos[0],pos[1],0});
				check[pos[0]][pos[1]] = true; 
			}
			else {
				int [] pos = virus_candidate.get(i); 
				checkmap[pos[0]][pos[1]] = -2; 
			}
		}
		
		available = 0 ;
		time = 0 ; 
		infection(); 
	
	}
	
	static void choose_position(int cnt, int idx) {
		if (cnt == m )
		{
			// 바이러스 넣을 부분  표시. 
			put_virus (); 
			return ; 
		}
		for(int i = idx ; i < virus_candidate.size(); i ++) {
			if (virus_candidate_check[i] == false) {
				virus_candidate_check[i] = true ;
				choose_position(cnt + 1, i + 1); 
				virus_candidate_check[i] = false ;
			}
		}
	}
	
	static void infection() {

		while (!queue.isEmpty()) {
			int [] now = queue.poll();
			
	
			for(int i = 0 ; i < dx.length; i ++) {
				int nx = now[0] + dx[i]; 
				int ny = now[1] + dy[i]; 
				if (nx < 0 || ny < 0 || ny >= n || nx >= n) continue; 
				if (check[nx][ny] == true ) continue; 
				if (checkmap[nx][ny] == -1 ) continue;
				if (checkmap [nx][ny] == -2 ) {
					check[nx][ny] = true; 
					queue.add(new int [] {nx,ny,now[2] + 1}); 
					continue; 
				}
				queue.add(new int [] {nx,ny,now[2] + 1}); 
				check[nx][ny] = true; 
				available ++; 
				time = now[2] + 1; 
		
			}
			
		}
		
		if (available == blankcount ) {
			//가능
			flag = true; 
			if (answer > time) {
				answer = time; 
			}
		}
	}
	
}

```
