```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pollution{
	int x; 
	int y ; 
	int val; 
	
	public Pollution(int x, int y , int val) {
		this.x = x; 
		this.y = y ; 
		this.val = val; 
	}
}
public class Main {
	static int r; 
	static int c; 
	static int t;
	static int [][] map;
	static int machine = - 1; 
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static ArrayList<Pollution> al = new ArrayList<>(); 
	static int answer = 0 ; 
	static void propagation() {
		
		for(int i = 0 ; i < al.size(); i ++) {
			Pollution p = al.get(i); 
			int plus = p.val / 5; 
			if(plus ==  0 ) continue; 
			int cnt = 0 ; 
			for(int j = 0 ; j < dx.length; j ++) {
				int nx = p.x + dx[j]; 
				int ny = p.y + dy[j]; 
				if(nx < 0 || nx >= r || ny < 0 || ny >= c )continue; 
				if(map[nx][ny] == -1 ) continue;
				cnt ++; 
				map[nx][ny] += plus;
			}
			map[p.x][p.y] -= cnt * plus; 
		}
	}
	static void init() {
		al = new ArrayList<>(); 
		for(int i = 0 ; i < r; i ++) {
			for(int j = 0 ; j < c; j ++) {
				if(map[i][j] != 0 && map[i][j] != -1) {
					al.add(new Pollution(i,j,map[i][j])); 
				}
			} 
		}
	}
	static void clean () {
		int one = machine; 
		int two = machine + 1; 
		// 반시계
		
		for(int i = one - 1; i >  0 ; i --) {
			map[i][0] = map[i-1][0]; 
		}
		for(int i  = 0 ; i < c -1 ; i ++) {
			map[0][i] = map[0][i  +1] ; 
			}
		for(int i  = 0 ;  i < one; i ++) {
			map[i][c-1] = map[i +1][c-1]; 
		}
		for(int i = c- 1; i > 1; i --) {
			map[one][i] = map[one][i-1]; 
		}
		map[one][1]  = 0; 
		//시계
		for(int  i = two  + 1 ; i < r -1 ; i ++) {
			map[i][0] = map[i + 1][0]; 
			}
		for(int i = 0 ; i < c -1; i ++) {
			map[r-1][i] = map[r-1][i + 1]; 
		}
		for(int i = r - 1; i > two ; i --) {
			map[i][c-1] = map[i-1][c-1] ;
		}
		for(int i = c -1; i > 1; i --) {
			map[two][i] = map[two][i-1]; 
		}
		map[two][1] =  0  ;
		
		
	}
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		
		for(int i = 0 ; i <r ;  i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < c; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && machine == - 1) {
					machine = i; 
				}
				else if (map[i][j] != 0) al.add(new Pollution(i,j, map[i][j])); 
			}
		}
		while(t > 0 ) {
			t --; 
			// 리스트 초기화 
			init(); 
			// 전파 
			propagation();
			// 정화 
			clean(); 
			}
		for(int  i = 0 ; i < r; i ++) {
			for(int j = 0 ; j < c; j ++) {
				if(map[i][j] == - 1) continue; 
				
				answer += map[i][j]; 
			}
		}
		System.out.println(answer); 
	}
}

```
