import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count, years;
    static int[][] map, tmpMap;
    static Deque<Coord> iceberg;
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

        // Build map
        map = new int[N][M];
        iceberg = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    // Store iceberg coord
                    iceberg.add(new Coord(i, j));
                }
            }
        }

        years = 0;
        while (true) {
            years++;
            updateIceberg();
            int group = checkGroup();
            if (group >= 2)
                break;
            else if (group == 0) {
                years = -1;
                break;
            }
        }

        System.out.println(years == -1 ? 0 : years);
    }

    static void updateIceberg() {
        tmpMap = new int[N][M];
        int icebergLeng = iceberg.size();

        // Update each iceberg height into tmpMap
        for (int i = 0; i < icebergLeng; i++) {
            Coord current = iceberg.pollFirst();
            int currX = current.x;
            int currY = current.y;

            // count number of water
            count = 0;
            for (int dir = 0; dir < 4; dir++) {
                int mx = dx[dir] + currX;
                int my = dy[dir] + currY;
                if (mx < 0 || my < 0 || mx >= N || my >= M)
                    continue;
                if (map[mx][my] <= 0)
                    count++;
            }

            iceberg.addLast(new Coord(currX, currY));
            tmpMap[currX][currY] = map[currX][currY] - count;
        }

        // Update base Map using new iceberg height
        for (int i = 0; i < icebergLeng; i++) {
            Coord current = iceberg.pollFirst();
            int currX = current.x;
            int currY = current.y;

            // Above 0 -> iceberg
            if (tmpMap[currX][currY] > 0)
                iceberg.addLast(new Coord(currX, currY));

            // Update iceberg height on base map
            map[currX][currY] = tmpMap[currX][currY];
        }
    }

    static int checkGroup() {
        int count = 0;

        // No iceberg exist
        if (iceberg.isEmpty())
            return 0;

        // Check iceberg group
        int icebergLeng = iceberg.size();
        visit = new boolean[N][M];
        for (int i = 0; i < icebergLeng; i++) {
            Coord current = iceberg.pollFirst();

            if (!visit[current.x][current.y]) {
                checkSurrounding(current.x, current.y);
                count++; // count group
            }
            iceberg.addLast(new Coord(current.x, current.y));
        }

        return count;
    }

    static void checkSurrounding(int x, int y) {
        visit[x][y] = true;
        for (int dir = 0; dir < 4; dir++) {
            int mx = dx[dir] + x;
            int my = dy[dir] + y;
            if (mx < 0 || my < 0 || mx >= N || my >= M)
                continue;
            if (map[mx][my] > 0 && !visit[mx][my]) {
                checkSurrounding(mx, my);
            }
        }
    }
}