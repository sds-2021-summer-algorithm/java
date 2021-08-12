import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, N, count;
    static Deque<Point> list;
    static Point start, target;
    static boolean[][] visit;
    static int[][] map;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1. 테스트 실행
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 2. 보드 준비
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            // 3. set 시작점 & 도착점
            st = new StringTokenizer(br.readLine());
            start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            target = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 4. 경로 찾기
            visit = new boolean[N][N];
            sb.append(bfs() + "\n");
        }

        // 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs() {
        int[] dx = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };
        int[] dy = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visit[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 타겟 지점에 도착
            if (current.x == target.x && current.y == target.y) {
                return map[current.x][current.y];
            }

            // 나이트 이동
            for (int i = 0; i < dx.length; i++) {
                int mx = current.x + dx[i];
                int my = current.y + dy[i];
                if (mx >= 0 && mx < N && my >= 0 && my < N && !visit[mx][my]) {
                    visit[mx][my] = true;
                    map[mx][my] = map[current.x][current.y] + 1;
                    queue.add(new Point(mx, my));
                }
            }
        }
        return 0;
    }
}