import java.io.*;
import java.util.*;

public class Main {
    static int N, M, totalCheese, hours;
    static int[][] board;
    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };
    static boolean[][] visit;

    static class Coord {
        int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Set variables
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // Build board
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1)
                    totalCheese++;
            }
        }

        hours = 0;
        int save = totalCheese;
        while (totalCheese != 0) {
            hours++;
            save = totalCheese;
            melt(); // 녹는 치즈 위치 저장
        }

        System.out.println(hours);
        System.out.println(save);
    }

    static void melt() {
        Queue<Coord> queue = new ArrayDeque<>();
        queue.add(new Coord(0, 0));
        visit = new boolean[N][M];
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int mx = current.x + dx[i];
                int my = current.y + dy[i];

                if (mx < 0 || my < 0 || mx >= N || my >= M || visit[mx][my])
                    continue;

                if (board[mx][my] == 1) {
                    board[mx][my] = 0;
                    totalCheese--;
                } else if (board[mx][my] == 0) {
                    queue.offer(new Coord(mx, my));
                }
                visit[mx][my] = true;
            }
        }
    }
}