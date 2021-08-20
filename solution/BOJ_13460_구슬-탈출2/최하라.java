import java.io.*;
import java.util.*;

public class Main {

    private static class Coord {
        int redX, redY, blueX, blueY, cnt;

        public Coord(int redX, int redY, int blueX, int blueY, int cnt) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.cnt = cnt;
        }

        public Coord() {
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // build map
        Coord coord = new Coord();
        coord.cnt = 0;
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') { // store red ball
                    coord.redX = i;
                    coord.redY = j;
                } else if (map[i][j] == 'B') { // store blue ball
                    coord.blueX = i;
                    coord.blueY = j;
                }
            }
        }

        visit = new boolean[10][10][10][10];

        bw.write(String.valueOf(rollBFS(coord)));
        bw.flush();

    }

    static int rollBFS(Coord ball) {
        int[] dx = new int[] { 0, 0, -1, 1 }; // 좌, 우, 위, 아래
        int[] dy = new int[] { -1, 1, 0, 0 };

        Queue<Coord> queue = new ArrayDeque<>();
        queue.offer(ball);

        while (!queue.isEmpty()) {
            Coord cur = queue.poll();
            visit[cur.redX][cur.redY][cur.blueX][cur.blueY] = true;

            // 10 이상인 경우 종류
            if (cur.cnt >= 10)
                return -1;

            // 4개의 방향으로 굴린다
            // 파란 구슬 굴리기
            for (int i = 0; i < 4; i++) {
                int bx = cur.blueX;
                int by = cur.blueY;

                while (map[bx + dx[i]][by + dy[i]] != '#') {
                    bx += dx[i];
                    by += dy[i];
                    if (map[bx][by] == 'O')
                        break;
                }

                // 빨간색 구슬 굴리기
                int rx = cur.redX;
                int ry = cur.redY;
                while (map[rx + dx[i]][ry + dy[i]] != '#') {
                    rx += dx[i];
                    ry += dy[i];
                    if (map[rx][ry] == 'O')
                        break;
                }

                if (map[bx][by] == 'O') {
                    continue;
                }

                if (map[rx][ry] == 'O')
                    return cur.cnt + 1;

                // 두 구슬의 위치가 같다면, 위치를 조정한다.
                if (rx == bx && ry == by) {
                    switch (i) {
                        case 0: // 좌
                            if (cur.redY > cur.blueY)
                                ry += 1;
                            else
                                by += 1;
                            break;
                        case 1: // 우
                            if (cur.redY > cur.blueY)
                                by -= 1;
                            else
                                ry -= 1;
                            break;
                        case 2: // 위
                            if (cur.redX > cur.blueX)
                                rx += 1;
                            else
                                bx += 1;
                            break;
                        case 3: // 아래
                            if (cur.redX > cur.blueX)
                                bx -= 1;
                            else
                                rx -= 1;
                            break;
                    }
                }

                if (!visit[rx][ry][bx][by])
                    queue.offer(new Coord(rx, ry, bx, by, cur.cnt + 1));
            }
        }
        return -1;
    }
}