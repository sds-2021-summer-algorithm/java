```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Point {
	int dest; 
	int cost; 
	public Point(int d, int c) {
		this.dest = d; 
		this.cost = c; 
	}
}
public class Main {
 // 임의의 한정점에서 가장 먼 점은 트리의 지름의 한 정점이 된다.. 
 // 그럼, 그 점에서 가정 먼 점을 찾으면 그점이 바로 다른 지름의 정점이 된다. 
	static ArrayList<Point>[] al;
	static int n ; 
	static boolean [] check; 
	static int max = Integer.MIN_VALUE;
	static int diameter1; 
	
	
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		al = new ArrayList[n + 1];
		
		for(int i = 1 ; i <= n ; i ++) {
			al[i] = new ArrayList<Point>(); 
		}
		int size = n; 
		while ( size --> 1) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al[a].add(new Point(b,c));
			al[b].add(new Point(a,c));
		}
		//한 점에서 가장 먼 점 찾기 
		check= new boolean [n + 1]; 
		findDiameterPoint(1,0);
		check = new boolean [n + 1]; 
		findDiameterPoint(diameter1,0);
		System.out.println(max); 
	}
	static void findDiameterPoint(int start, int c) {
		
		if (c > max) {
			max = c; 
			diameter1 = start;
		}
		
		check[start] = true;
		
		for(int i = 0 ; i < al[start].size(); i ++)
		{
			Point next = (Point) al[start].get(i);
			int nextp = next.dest;
			if (check[nextp] == false) { 
				findDiameterPoint(nextp, c + next.cost);
				check[nextp] = true;
			}
		}
	}
}
```
