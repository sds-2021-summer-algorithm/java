import java.io.*;
import java.util.*;

public class Main {
    static class Coord {
        int x, y, dust;

        public Coord(int x, int y, int dust) {
            this.x = x;
            this.y = y;
            this.dust = dust;
        }
    }

    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    static int R, C, T, totalDust, airCleaner;
    static int[][] room;
    static Deque<Coord> dustQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // build room & store dust location
        dustQueue = new ArrayDeque<>();
        room = new int[R][C];
        totalDust = 0;
        airCleaner = -1;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1 && airCleaner == -1)
                    airCleaner = i;
                else if (room[i][j] != 0)
                    dustQueue.add(new Coord(i, j, room[i][j]));
            }
        }

        // 2. 미세먼지 이동
        while (T-- > 0) {
            moveDust(); // 미세먼지 이동
            moveAir(); // 공기청정
            updatePlace(); // 위치 업데이트
        }

        // 3. 결과 출력
        System.out.println(totalDust);
    }

    static void moveDust() {
        while (!dustQueue.isEmpty()) {
            int countDir = 0;
            Coord current = dustQueue.pollFirst();
            if (current.dust < 5)
                continue;
            int around = current.dust / 5;

            // 상하좌우 업데이트
            for (int i = 0; i < 4; i++) {
                int mx = dx[i] + current.x;
                int my = dy[i] + current.y;

                if (mx < 0 || my < 0 || mx >= R || my >= C)
                    continue;
                if (room[mx][my] == -1)
                    continue;

                room[mx][my] += around;
                countDir++;
            }

            room[current.x][current.y] -= around * countDir;
        }
    }

    static void moveAir() {
        // up
        for (int i = airCleaner - 1; i > 0; i--)
            room[i][0] = room[i - 1][0]; // 아래로 당김
        for (int i = 0; i < C - 1; i++)
            room[0][i] = room[0][i + 1]; // 좌
        for (int i = 0; i < airCleaner; i++)
            room[i][C - 1] = room[i + 1][C - 1]; // 위
        for (int i = C - 1; i > 1; i--)
            room[airCleaner][i] = room[airCleaner][i - 1]; // 우
        room[airCleaner][1] = 0;

        // down
        int down = airCleaner + 1;
        for (int i = down + 1; i < R - 1; i++)
            room[i][0] = room[i + 1][0]; // 위
        for (int i = 0; i < C - 1; i++)
            room[R - 1][i] = room[R - 1][i + 1]; // 좌
        for (int i = R - 1; i > down; i--)
            room[i][C - 1] = room[i - 1][C - 1]; // 하
        for (int i = C - 1; i > 1; i--)
            room[down][i] = room[down][i - 1]; // 우
        room[down][1] = 0;
    }

    static void updatePlace() {
        totalDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    dustQueue.add(new Coord(i, j, room[i][j]));
                    totalDust += room[i][j];
                }
            }
        }
    }

}