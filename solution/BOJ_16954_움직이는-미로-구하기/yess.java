```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class  Main {
	static char [][] map; 
	static Queue <int [] >queue = new ArrayDeque(); 
	static int [] dx = {1,1,-1,-1,0,0,1,-1,0};
	static int [] dy = {1,-1,-1,1,1,-1,0,0,0};
	static boolean check[][]; 
	static boolean flag = false; 
	static boolean flag2 = false; 
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		map = new char[8][8]; 
		check = new boolean [8][8]; 
		for(int i = 0 ; i < map.length;  i ++ ) {
			String s = br.readLine(); 
			for(int j = 0 ; j < map[i].length; j ++) {
				map[i][j] = s.charAt(j); 
			}
		}
		queue.add(new int[] {7,0});
		while (true ) {
			moveCharacter(); 
			if (flag == true ) break; 
			if (flag2 == true ) break; 
			moveBlock(); 
			//print(); 
		}
		if (flag) System.out.println(1); 
		else if (flag2) System.out.println(0); 
	}
	static void print() { 
		for(int i = 0 ; i < map.length; i ++) {
			for(int j = 0 ; j < map[0].length; j ++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println(); 
		}
		System.out.println(); 
	}
	static void moveBlock() {
		for(int j = 0 ; j < 8 ; j ++) {
			for(int i = 7 ; i >= 0 ; i --) {
				if (i == 7 && map[i][j] == '#') map[i][j] = '.'; 
				else if (i != 7 && map[i][j] == '#') {
					map[i][j] = '.'; 
					map[i + 1][j] = '#'; 
				}
			}
		}
	}
	static void moveCharacter() {
		int size = queue.size(); 
		for(int i = 0 ; i < size; i ++) {
			int [] temp = queue.poll();
			if (map[temp[0]][temp[1]] == '#') continue; 
			if (temp[0] == 0 && temp[1] == 7) {
				flag = true; 
				return ; 
			}
			for(int d = 0 ; d < dx.length; d ++ ) {
				int nx = temp[0] + dx[d]; 
				int ny = temp[1] + dy[d]; 
				if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue; 
				if (map[nx][ny] == '#') continue; 
				queue.add(new int[] {nx,ny}); 
			}
		}
		if (queue.isEmpty()) {
			flag2 = true; 
			return ; 
		}
	}
}

```
