import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static int MAX, MIN;
    // 상 하 좌 우
    static final int[] mx = {0, 0, -1, 1};
    static final int[] my = {-1, 1, 0, 0};
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        MAX = -1; MIN = 202;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                MAX = Math.max(MAX, map[i][j]);
                MIN = Math.min(MIN, map[i][j]);
            }
        }
        int diffMin = Integer.MAX_VALUE;
        int left = 0, right = MAX - MIN;
        while(left <= right) {
            int mid = (left + right) / 2;
            boolean flag = false;
            for (int i = MIN; i <= MAX; i++) {
                if (i <= map[0][0] && map[0][0] <= i + mid) {
                    if (bfs(i, i + mid)) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) {
                diffMin = Math.min(diffMin, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        bw.write(diffMin + "\n");
        bw.flush();
        bw.close();
    }
    static boolean bfs(int left, int right) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(0, 0));
        visited[0][0] = true;
        while(!q.isEmpty()) {
            Point cur = q.remove();
            if (cur.i == N - 1 && cur.j == N - 1) return true;
            for (int i = 0; i < 4; i++) {
                int ty = cur.i + my[i];
                int tx = cur.j + mx[i];

                if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                    if(!visited[ty][tx] && map[ty][tx] >= left && map[ty][tx] <= right) {
                        visited[ty][tx] = true;
                        q.add(new Point(ty, tx));
                    }
                }
            }
        }
        return false;
    }
}
