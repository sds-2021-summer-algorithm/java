import java.io.*;
import java.util.*;

public class Main {
    static int N, M, greenTomato, days;
    static int[][] storage;
    static Deque<Coord> redTomato;
    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Set variables
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // Build storage
        storage = new int[N][M];
        redTomato = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                if (storage[i][j] == 1)
                    redTomato.add(new Coord(i, j));
                else if (storage[i][j] == 0)
                    greenTomato++;
            }
        }

        if (greenTomato > 0 && redTomato.isEmpty()) {
            System.out.println(-1);
            return;
        }

        days = 0;
        while (!redTomato.isEmpty()) {
            days++;

            int size = redTomato.size();
            for (int i = 0; i < size; i++) {
                Coord current = redTomato.pollFirst();
                for (int j = 0; j < 4; j++) {
                    int mx = current.x + dx[j];
                    int my = current.y + dy[j];

                    if (mx < 0 || my < 0 || mx >= N || my >= M)
                        continue;
                    if (storage[mx][my] == 0) {
                        greenTomato--;
                        storage[mx][my] = 1;
                        redTomato.addLast(new Coord(mx, my));
                    }
                }
            }
        }
        System.out.println(greenTomato == 0 ? days - 1 : -1);
    }
}