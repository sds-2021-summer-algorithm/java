```


public class Programmers0904 {
	
	static boolean [][][][] visited = new boolean [11][11][11][11]; 
	public  static void main(String [] args) {
		String dirs = "LULLLLLLU"; 
		int answer  = solution(dirs); 
		System.out.println(answer); 
	}
	static boolean indexcheck(int nx, int ny ) {
		if(nx < 0 || ny < 0 || nx > 10 || ny > 10 ) return false; 
		return true; 
	}
	public static int solution(String dirs) {
		int ret = 0;
		int startx = 5 ; 
		int starty = 5 ; 
		for(int i = 0 ; i < dirs.length(); i ++) {
			char c = dirs.charAt(i); 
			if( c== 'U') {
				int nx = startx -1; 
				int ny = starty; 
				if(indexcheck(nx,ny) == false) continue; 
				if(visited[startx][starty][nx][ny] == false && visited[nx][ny][startx][starty] == false) ret ++; 
				visited[nx][ny][startx][starty] = true;
				visited[startx][starty][nx][ny] = true ;
				startx = nx; 
				starty = ny ; 
			
			}
			else if (c == 'D') {

				int nx = startx  + 1; 
				int ny = starty;
				if(indexcheck(nx,ny) == false) continue; 
				if(visited[startx][starty][nx][ny] == false && visited[nx][ny][startx][starty] == false) ret ++; 
				visited[nx][ny][startx][starty] = true;
				visited[startx][starty][nx][ny] = true ;
				startx = nx; 
				starty = ny ; 
			}
			else if( c== 'L') {

				int nx = startx ; 
				int ny = starty -1;
				if(indexcheck(nx,ny) == false) continue; 
				if(visited[startx][starty][nx][ny] == false && visited[nx][ny][startx][starty] == false) ret ++; 
				visited[nx][ny][startx][starty] = true;
				visited[startx][starty][nx][ny] = true ;
				startx = nx; 
				starty = ny ; 
			}
			else {

				int nx = startx ; 
				int ny = starty + 1;
				if(indexcheck(nx,ny) == false) continue; 
				if(visited[startx][starty][nx][ny] == false && visited[nx][ny][startx][starty] == false) ret ++; 
				visited[nx][ny][startx][starty] = true;
				visited[startx][starty][nx][ny] = true ;
				startx = nx; 
				starty = ny ; 
			}
		}
		return ret; 
	}
	
	
}
```
