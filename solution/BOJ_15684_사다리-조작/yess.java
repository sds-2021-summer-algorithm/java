```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution15684 {
	//  사다리 설치 하고 check
	//  왼쪽에서 오른쪽  1 
	//  오른쪽에서 왼쪽  2
	static int n; 
	static int m ; 
	static int h; 
	static int [][] place; 
	static boolean flag; 
	static int answer ; 
	
	static boolean isitOrNot() {
		for(int id = 1; id <= n ; id ++) {
			int pos1 = 1; 
			int  pos2 = id ; 
			for(int i = 1; i <= h ; i ++) {
				if(place[pos1][pos2] == 1) {
					pos2 ++; 
				}
				else if(place[pos1][pos2] == 2){
					pos2 -- ; 
				}
				pos1 ++; 
			}
			if(pos2 != id) return false; 
		}
		return true;
	}

	static void makeplace (int x, int y ,  int cnt ) {
		if(flag == true ) return ; 
		if(answer == cnt ) {
			if(isitOrNot() == true) 
			{
				System.out.println(cnt); 
				System.exit(0); 
			}
		}
		
		for(int i = x; i < place.length ; i ++)
		{
			for(int j = i== x ? y : 1  ; j < place[0].length - 1; j ++) {
				if(place[i][j] == 0 && place[i][j + 1] == 0 ) {
					place[i][j] = 1; 
					place[i][j + 1] = 2 ; 
					makeplace(i,j, cnt + 1); 
					place[i][j] = 0 ; 
					place[i][j + 1] = 0 ; 
				}
			}
		}
	}
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		h = Integer.parseInt(st.nextToken()); 
		place = new int  [h + 1] [n + 1]; 
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine()); 
			int s1 = Integer.parseInt(st.nextToken()); 
			int s2 = Integer.parseInt(st.nextToken());
			place[s1][s2] = 1; 
			place[s1][s2 + 1] = 2;
		}
		for(int limit = 0 ; limit <= 3; limit ++) {
			answer = limit;	
			makeplace(1,1,0); 
			
		}
		System.out.println(flag == false ? -1 : answer); 
	}
}

```
