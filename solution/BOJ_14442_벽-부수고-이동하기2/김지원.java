import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
 
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
}
