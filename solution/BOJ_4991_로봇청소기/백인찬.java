import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static char[][] map;
    static List<Point> points;
    // 상 하 좌 우
    static final int[] mx = {0, 0, -1, 1};
    static final int[] my = {-1, 1, 0, 0};
    static int[][][] dist;
    static int w, h;
    static boolean[] selected;
    static int min;
    static int[] perm;
    static boolean impossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;

            map = new char[h][w];
            points = new ArrayList<>();
            Point start = null;
            List<Point> dirty = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
                    if(map[i][j] == 'o') start = new Point(i, j);
                    else if (map[i][j] == '*') dirty.add(new Point(i, j));
                }
            }
            points.add(start);
            points.addAll(dirty);

            int len = points.size();
            dist = new int[len][h][w]; // points 리스트의 index의 point -> i, j의 거
            for (int i = 0; i < len; i++) {
                Point p = points.get(i);
                bfs(p, i);
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if(i == j) continue;
                    Point p = points.get(j);
                    if (dist[i][p.i][p.j] == 0) {
                        impossible = true;
                        break;
                    }
                }
                if(impossible) break;
            }
            if(impossible) {
                sb.append("-1\n");
                impossible = false;
                continue;
            }
            selected = new boolean[len];
            min = Integer.MAX_VALUE;
            perm = new int[len - 1];
            getPermutation(0);
            if(impossible) sb.append("-1\n");
            else sb.append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void bfs(Point p, int index) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        queue.add(p);
        visited[p.i][p.j] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            for (int i = 0; i < 4; i++) {
                int ty = cur.i + my[i];
                int tx = cur.j + mx[i];
                if(ty >= 0 && ty < h && tx >= 0 && tx < w) {
                    if(!visited[ty][tx] && map[ty][tx] != 'x') {
                        queue.add(new Point(ty, tx));
                        visited[ty][tx] = true;
                        dist[index][ty][tx] = dist[index][cur.i][cur.j] + 1;
                    }
                }
            }
        }
    }

    static void getPermutation(int count) {
        int len = points.size();
        if (count == len - 1) {
            int cur = 0;
            int total = 0;
            for (int index : perm) {
                Point next = points.get(index);
                total += dist[cur][next.i][next.j];
                cur = index;
            }
            min = Math.min(total, min);
            return;
        }

        for (int i = 1; i < len; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            perm[count] = i;
            getPermutation(count + 1);
            if(impossible) return;
            selected[i] = false;
        }
    }
}
