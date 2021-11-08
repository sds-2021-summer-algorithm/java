import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main {
    static int moveX[] = {0,1,1,1,0,-1,-1,-1,0};
    static int moveY[] = {-1,-1,0,1,1,1,0,-1,0};
    static char arr[][];
    static Point start = new Point(1,8);
    static Queue<Point> Wall = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[9][9];
        for(int i=1; i<=8; i++) {
            String str = br.readLine();
            for(int j=1; j<=8; j++) {
                arr[i][j] = str.charAt(j-1);
                if(arr[i][j] == '#')
                    Wall.add(new Point(j,i));
            }
        }
        
        
        bfs();
    }
    
    private static void bfs() {
        // TODO Auto-generated method stub
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(start);
        
        while(true) {
            // 욱제의 캐릭터를 갈 수 있는 곳 다 가주는 딘계
            while(!queue.isEmpty()) {
                Point po = queue.poll();
                if(po.x == 8 && po.y == 1) {
//                    System.out.println("here");
                    System.out.println("1");
                    return;
                }
                if(arr[po.y][po.x] == '#')
                    continue;
                
                // 상하좌우, 대각선, 제자리 
                for(int d=0; d<9; d++) {
                    int newY = po.y + moveY[d];
                    int newX = po.x + moveX[d];
                    
                    if(newY<1 || newY>8 || newX<1 || newX>8)
                        continue;
                    
                    //여기서 미리 queue에 넣어줘도 되긴 하는데 나중에 벽 움직이고 제거해줘야해서 뒤에서 하는 게 시간적으로 효율
                    if(arr[newY][newX] == '.')
                        arr[newY][newX] = '*';
                }
            }
            
            // 벽이 아예 없으면 무조건 도달할 수 있다.
            if(Wall.size() == 0){
//                System.out.println("another");
                System.out.println("1");
                return;
            }
            
            int len = Wall.size();
            for(int i=0; i<len; i++) {
                Point p = Wall.poll();
                if(p.y + 1 != 9) {
                    arr[p.y][p.x] = '.';
                    arr[p.y+1][p.x] = '#';
                    Wall.add(new Point(p.x, p.y+1));
                }
            }
            
            // 욱제의 캐릭터가 있는 위치를 큐에 넣는다
            for(int i=1; i<=8; i++) {
                for(int j=1; j<=8; j++) {
                    if(arr[i][j] == '*') {
                        queue.add(new Point(j,i));
                    }
                }
            }
            // 욱제 캐릭터가 없으면 불가능
            if(queue.size() == 0) {
                System.out.println(0);
                return;
            }
        }
    }
}
 
