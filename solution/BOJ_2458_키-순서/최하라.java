import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. dist[][] 초기화
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = INF;
            }
        }

        // 3. build map
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dist[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        // 4. do floyd warshall to find the minimum path
        floydWarshall();

        // 5. 결과 출력
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] != INF || dist[j][i] != INF)
                    cnt++;
            }
            if (cnt == N - 1)
                answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}