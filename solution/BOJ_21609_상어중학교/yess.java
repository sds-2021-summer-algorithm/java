```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int n ; 
	static int m ; 
	static int map[][]; 
	// 그룹 사이즈 
	static int gsize =  0 ; 
	// 무지개 사이즈 
	static int rsize =  0; 
	static int dx [] = {1,-1,0,0}; 
	static int dy [] = {0,0,1,-1};
	// 무지개 카운트
	static int rcount = 0 ; 
	// 그룹 카운트 
	static int ret  = 0 ; 
	static boolean check[][]; 
	static int removepos1  = 0 ; 
	static int removepos2 = 0 ; 
	static int item = 0  ; 
	static int answer = 0 ; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		map = new int [n][n]; 
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		while(true) {
			boolean goornot = false; 
			gsize = 0; 
			rsize = 0 ; 
			for(int i = 1; i <= m ; i ++) {
				check = new boolean [n][n]; 
				for(int j = 0  ; j < map.length; j  ++ ) {
					for(int k = 0 ; k < map[0].length; k  ++ ) {
						if(map[j][k] == i && check[j][k] == false) {
							rcount =  0; 
							ret = 1 ; 
							check[j][k] = true; 
							find_group(j,k,i,false); 
							if(ret >= 2 && ret > gsize) {
								gsize = ret; 
								removepos1 = j ; 
								removepos2 = k ; 
								item = i ; 
								goornot = true; 
								rsize = rcount; 
							}else if(ret >= 2 && ret == gsize && rsize <= rcount ) {
								if(rsize == rcount  ) {
									if(removepos1 < j  || removepos1 == j && removepos2 <= k ) {
										removepos1 = j ; 
										removepos2 = k ; 
										item = i ; 
									}
								}
								else {
								rsize = rcount; 
								removepos1 = j ; 
								removepos2 = k ; 
								
								item = i ; 
								}
							}
							
							
							
						}
					}
				}
			}
		if(goornot == false) break; 
		answer += Math.pow(gsize, 2); 
		find_group(removepos1, removepos2,item, true ); 
		gravity(); 
		rotate(); 
		gravity(); 
	}
		System.out.println(answer); 
	}
	static void find_group(int x, int y, int num, boolean flag) {
		for(int i = 0 ; i < dx.length; i ++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i]; 
			if(nx < 0 || ny < 0 || ny>= map[0].length || nx >= map.length) continue; 
			
			if(map[nx][ny] == 0 || map[nx][ny] == num ) {
				if(check[nx][ny] == false && flag == false) {
					if(map[nx][ny] == 0 ) rcount ++; 
					check[nx][ny] = true; 
					ret ++; 
					find_group(nx,ny,num,flag); 
				}
				// flag == true이면  선택된 큰 그룹 지우기 
				else if(flag == true ) {
					map[nx][ny] = -2; 
					find_group(nx,ny,num,flag); 
				}
			}
			
		}
		
	}
	static void gravity() {
		for(int i = 0 ; i < map[0].length; i ++) {
			for(int j = n -1; j >= 0 ; j --) {
				// 빈칸 
				if(map[j][i] == -2) {
					for(int nj  = j -1; nj >= 0 ; nj --) {
						if(map[nj][i] == -1) {
							break;
						}
						else if(map[nj][i]  != -1 && map[nj][i] != -2) {
							map[j][i] = map[nj][i]; 
							map[nj][i] = -2; 
							break; 
						}
					}
				}
				
				
			}
			
		}
	}
	static void rotate() {
		int newmap [][] = new int[n][n];
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < n ; j ++) {
				newmap[n-1-j][i] = map[i][j]; 
			}
		}
		map = newmap; 
	}
}
```
