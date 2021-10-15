import java.io.*;
import java.util.*;

public class tmp {
    static int N, K, min = Integer.MAX_VALUE;
    static boolean[] visit;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 2. 플로이드 와샬 - 각 행성별 최단 거리로 업데이트
        floydWarshall();

        // 3. 백트랙킹 - 최단 거리 검색
        visit = new boolean[N];
        visit[K] = true;
        backTracking(K, 0, 0);

        // 4. 결과 출력
        System.out.println(min);
    }

    static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j)
                        continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    static void backTracking(int at, int depth, int sum) {
        if (depth == N - 1) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (at == i)
                continue;
            if (!visit[i]) {
                visit[i] = true;
                backTracking(i, depth + 1, sum + map[at][i]);
                visit[i] = false;
            }
        }
    }
}