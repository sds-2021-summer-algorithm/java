import java.io.*;
import java.util.*;

public class Main {
    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    static int R, C, totalMove;
    static char[][] map;
    static Deque<Coord> fireQueue;
    static Deque<Coord> personQueue;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // build map
        map = new char[R][C];
        personQueue = new ArrayDeque<>();
        fireQueue = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String road = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = road.charAt(j);

                // 지훈이 위치 저장
                if (map[i][j] == 'J')
                    personQueue.offerLast(new Coord(i, j));
                // 불 위치 저장
                else if (map[i][j] == 'F')
                    fireQueue.offerLast(new Coord(i, j));
            }
        }

        // bfs를 활용해 최소거리 찾기
        visit = new boolean[R][C];
        totalMove = 0;
        bw.write(String.valueOf(bfs() < 0 ? "IMPOSSIBLE" : totalMove));

        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs() {
        while (true) {
            // 지훈이의 탈출 할 수 있는 길이 없다
            if (personQueue.size() == 0)
                return -1;

            // 움직인 횟수 1 증가
            totalMove++;

            // 현재 총 불 개수 저장
            int size = fireQueue.size();
            // 현재 있는 불 다 돌기
            for (int i = 0; i < size; i++) {
                Coord fire = fireQueue.pollFirst();
                for (int j = 0; j < 4; j++) {
                    int mx = dx[j] + fire.x;
                    int my = dy[j] + fire.y;
                    if (mx >= 0 && my >= 0 && mx < R && my < C) {
                        if (map[mx][my] == '.') {
                            visit[mx][my] = true;
                            map[mx][my] = 'F';
                            fireQueue.offerLast(new Coord(mx, my));
                        }
                    }
                }
            }

            // 지훈이 옮기기
            size = personQueue.size();
            for (int i = 0; i < size; i++) {
                Coord current = personQueue.pollFirst();
                for (int j = 0; j < 4; j++) {
                    int mx = dx[j] + current.x;
                    int my = dy[j] + current.y;
                    if (mx >= 0 && my >= 0 && mx < R && my < C) {
                        if (map[mx][my] == '.' && !visit[mx][my]) {
                            visit[mx][my] = true;
                            map[mx][my] = 'F';
                            personQueue.offerLast(new Coord(mx, my));
                        }
                    }
                    // 끝에 도달
                    else
                        return totalMove;
                }
            }
        }
    }
}