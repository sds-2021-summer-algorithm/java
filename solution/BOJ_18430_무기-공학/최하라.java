import java.io.*;
import java.util.*;

public class Main {
    static int N, M, maxTotal = 0;
    static int[][] map;
    static boolean visit[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. 부매랑을 만들 수 없는 경우
        if (N < 2 || M < 2) {
            System.out.println(0);
            return;
        }

        // 3. build map
        map = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 4. 최대 조합 찾기
        visit = new boolean[N + 1][M + 1];
        backtracking(0, 0);

        // 5. 결과 출력
        System.out.println(maxTotal);
    }

    static void backtracking(int index, int sum) {
        if (index == N * M) {
            maxTotal = Math.max(maxTotal, sum);
            return;
        }

        int i = index / M;
        int j = index % M;

        if (!visit[i][j]) {
            for (int r = 0; r < 4; r++) {
                int tmp = checkDir(r, i, j);
                if (tmp != -1) {
                    updateBool(r, i, j);
                    backtracking(index + 1, sum + (map[i][j] * 2 + tmp));
                    updateBool(r, i, j);
                }
            }
        }
        backtracking(index + 1, sum);
    }

    static int checkDir(int dir, int x, int y) {
        switch (dir) {
            case 0: // 좌 하
                if (y - 1 >= 0 && x + 1 < N) {
                    if (!visit[x][y - 1] && !visit[x + 1][y])
                        return map[x][y - 1] + map[x + 1][y];
                }
                break;
            case 1: // 우 하
                if (x + 1 < N && y + 1 < M) {
                    if (!visit[x][y + 1] && !visit[x + 1][y])
                        return map[x][y + 1] + map[x + 1][y];
                }
                break;
            case 2: // 좌 상
                if (x - 1 >= 0 && y - 1 >= 0) {
                    if (!visit[x][y - 1] && !visit[x - 1][y])
                        return map[x][y - 1] + map[x - 1][y];
                }
                break;
            case 3: // 우 상
                if (x - 1 >= 0 && y + 1 < M) {
                    if (!visit[x - 1][y] && !visit[x][y + 1])
                        return map[x][y + 1] + map[x - 1][y];
                }
                break;
        }
        return -1;
    }

    static void updateBool(int dir, int x, int y) {
        visit[x][y] = !visit[x][y];
        switch (dir) {
            case 0: // 좌 하
                visit[x][y - 1] = !visit[x][y - 1];
                visit[x + 1][y] = !visit[x + 1][y];
                break;
            case 1: // 우 하
                visit[x][y + 1] = !visit[x][y + 1];
                visit[x + 1][y] = !visit[x + 1][y];
                break;
            case 2: // 좌 상
                visit[x][y - 1] = !visit[x][y - 1];
                visit[x - 1][y] = !visit[x - 1][y];
                break;
            case 3: // 우 상
                visit[x - 1][y] = !visit[x - 1][y];
                visit[x][y + 1] = !visit[x][y + 1];
                break;
        }
    }

}