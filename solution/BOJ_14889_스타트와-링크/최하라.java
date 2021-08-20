import java.io.*;
import java.util.*;

public class Main {
    static int N, M, min;
    static boolean[] visit;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        M = N / 2;

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. bfs 활용해 최소 능력치 찾기
        visit = new boolean[N + 1];
        visit[1] = true;
        min = Integer.MAX_VALUE;
        bfs(1, 2); // 1은 이미 한 팀에 배정된 상태로 진행

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int depth, int at) {
        if (depth == M) {
            // 두 팀의 능력치 비교 & 업데이트
            compStreng();
            return;
        }

        for (int i = at; i <= N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                bfs(depth + 1, i);
                visit[i] = false;
            }
        }
    }

    static void compStreng() {
        int team1 = 0;
        int team2 = 0;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (visit[i] && visit[j]) { // team 1의 능력치
                    team1 += map[i][j] + map[j][i];
                } else if (!visit[i] && !visit[j]) { // team 2의 능력치
                    team2 += map[i][j] + map[j][i];
                }
            }
        }

        // 최소 값 저장
        min = Math.min(min, Math.abs(team1 - team2));

        // 최소 값이 0 이라면 시스템 종료
        if (min == 0) {
            System.out.println(min);
            System.exit(0);
        }
    }
}