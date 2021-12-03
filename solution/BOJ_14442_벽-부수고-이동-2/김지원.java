import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main_BJ_2206_벽부수고이동하기2 {
 
    static class info {
        int x, y, dis, wall;
 
        public info(int x, int y, int dis, int wall) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.wall = wall;
        }
    }
 
    static int N, M,K, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
 
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
 
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j <M; j++) {
                map[i][j] = str.charAt(j) - 48;
            }
        }
        move();
    }
 
    public static void move() {
        Queue<info> queue = new LinkedList<>();
 
        queue.offer(new info(0, 0, 1, 0));
 
        visited[0][0][0] = true;
 
        while (!queue.isEmpty()) {
            info temp = queue.poll();
 
            if (temp.x == N-1 && temp.y == M-1) {
                System.out.println(temp.dis);
                return ;
            }
 
            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                int breakWall = temp.wall;
                int count = temp.dis;
 
                if (range(nx, ny)) {
                    if (map[nx][ny] == 1) { //벽일때
                        if(breakWall <K && !visited[nx][ny][breakWall+1]) {
                            visited[nx][ny][breakWall+1] = true;
                            queue.offer(new info(nx, ny, count+1, breakWall+1));
                        }
                    } 
                    else {
                        if (!visited[nx][ny][breakWall]) {
                            visited[nx][ny][breakWall] = true;
                            queue.offer(new info(nx, ny, count + 1 ,breakWall));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
 
    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
 
}
