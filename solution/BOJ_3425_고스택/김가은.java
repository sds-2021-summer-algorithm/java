import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 좌, 우, 위, 아래 
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};

    static int R, C; //맵의 크기
    static char[][] map;
    static int[][] dp; //최단거리 기록할 맵
    static Queue<Point> queue;
    static boolean foundAnswer;
    
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/DAY01/P3055/input.txt"));
    	Scanner sc = new Scanner(System.in);
    	
    	R = sc.nextInt();
    	C = sc.nextInt();
    	
    	map = new char[R][C];
    	dp = new int[R][C];
    	queue = new LinkedList<>();
    	
    	Point st = null;
    	for (int r = 0; r < R; r++) {
    		String line = sc.next();
    		for (int c = 0; c < C; c++) {
    			map[r][c] = line.charAt(c);
    			if (map[r][c] == 'S') {
    				st = new Point(r, c, 'S');
    			} else if (map[r][c] == '*') {
    				queue.add(new Point(r, c, '*'));
    			}
    		}
    	}
    	
		while (!queue.isEmpty()) {
			// 1. 큐에서 꺼내옴 => S
			Point p = queue.poll();
			// 2. 목적지인가? if(p == D)
			if (p.type == 'D') {
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			//3. 갈 수 있는 곳을 순회 for(좌, 우, 위, 아래)
			for (int i = 0; i < 4; i++) {
				int ty = p.y + my[i]; // +0
				int tx = p.x + mx[i]; // -1
				// 4. 갈 수 있는가? if(맵을 벗어나지 않고, [.]이거나 [D]이거나)
				if (0 <= ty && ty < R && 0 <= tx && tx < C) {
					if (p.type =='*') {
						if(map[ty][tx] == '.' || map[ty][tx] == 'S') {
							// 5. 체크인
							map[ty][tx] = '*';
							// 6. 큐에 넣음 queue.add(next)
							queue.add(new Point(ty, tx, '*'));
						}
					}else {
						// [.]이거나 [D]이거나 
						if(map[ty][tx] == '.' || map[ty][tx] == 'D') {
							if (dp[ty][tx] == 0) {
								// 5. 체크인
								dp [ty][tx] = dp[p.y][p.x] + 1;
								// 6. 큐에 넣음 queue.add(next)
								queue.add(new Point(ty, tx, map[ty][tx]));
							}
						}
					}
				}
			}
		}

			if (foundAnswer == false) {
				System.out.println("KAKTUS");
			}
		}

    }

 
class Point {
	int y;
	int x;
	char type;
	
	public Point(int y, int x, char type) {
		super();
		this.y = y;
		this.x = x;
		this.type = type;
	}
	
	@Override
	public String toString() { return "[y=" + y + ", x=" + x + ", type=" + type + "]"; }
}