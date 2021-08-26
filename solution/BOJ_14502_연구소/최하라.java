import java.io.*;
import java.util.*;

public class Main {

    static int N, M, maxArea;
    static int[][] origin;
    static int[][] wallMap;
    static int[][] virusMap;
    static boolean[][] visit;

    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. build map
        origin = new int[N][M];
        wallMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                wallMap[i][j] = origin[i][j];
            }
        }

        // 3. 모든 경우 고려
        // 벽세우기
        maxArea = 0;
        virusMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wallMap[i][j] == 0) {
                    // wallMap 초기화
                    resetMap(origin, wallMap);
                    wallMap[i][j] = 1;
                    // wallMap = origin;
                    wallDfs(1);
                    wallMap[i][j] = 0;
                }
            }
        }

        // 4. 안전지대 최대값 출력
        bw.write(String.valueOf(maxArea));
        bw.flush();
        bw.close();
        br.close();
    }

    static void wallDfs(int wallCount) {
        if (wallCount == 3) {
            // 업데이트 된 wall map을 바이러스 맵에 저장
            resetMap(wallMap, virusMap);

            // 바이러스 퍼뜨리기
            visit = new boolean[N][M];
            spreadVirus();

            // 안전지대 최대값 저장
            maxArea = Math.max(safeArea(), maxArea);
            return;
        }

        // 벽 세우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wallMap[i][j] == 0) {
                    wallMap[i][j] = 1;
                    wallDfs(wallCount + 1);

                    wallMap[i][j] = 0;
                }
            }
        }
    }

    static void spreadVirus() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 2 && !visit[i][j]) {
                    virusDFS(i, j);
                }
            }
        }
    }

    static void virusDFS(int x, int y) {
        visit[x][y] = true;
        virusMap[x][y] = 2;
        for (int i = 0; i < 4; i++) {
            int mx = x + dx[i];
            int my = y + dy[i];
            if (mx >= 0 && mx < N && my >= 0 && my < M) {
                if (!visit[mx][my] && virusMap[mx][my] == 0) {
                    virusDFS(mx, my);
                }
            }
        }
    }

    static int safeArea() {
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 0)
                    area++;
            }
        }
        return area;
    }

    static void resetMap(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}