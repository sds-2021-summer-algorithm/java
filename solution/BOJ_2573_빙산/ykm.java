import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // 행
    static int M; // 열
    static int[][][] Map; // 위치, 빙산의 높이정보, 턴 정보
    static boolean[][] isVisited;
    static int ice = 0; // 빙산이 차지하고 있는 넓이 -> 빙산이 줄어드는지 확인할때 사용할 예정

    static int[] mx = { -1, 1, 0, 0 };
    static int[] my = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                Map[i][j][0] = Integer.parseInt(st.nextToken());
                if (Map[i][j][0] > 0)
                    ice++;
            }
        }

        int turn = 1;
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (Map[i][j][0] > 0) {
                        melt(i, j, turn);
                    }
                }
            }
            int c = check();
            if (c == -1) {
                System.out.println(0);
                break;
            } else if (c < ice) {
                System.out.println(turn);
                break;
            }
            turn++;

        }
        br.close();
    }

    private static void melt(int x, int y, int turn) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + mx[i];
            int nextY = y + my[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                continue;

            // 주위에 0이 있으면 녹음
            // 갓 녹은 빙하 주의
            if (Map[nextX][nextY][0] == 0 && Map[nextX][nextY][1] < turn) {
                Map[x][y][0]--;

                // 모두 녹으면
                if (Map[x][y][0] == 0) {
                    Map[x][y][1] = turn;
                    ice--;
                    break;
                }
            }
        }
    }

    // 빙산의 일부를 찾아 이어진 모든 빙산의 넓이를 구하기
    // dfs
    private static int check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (Map[i][j][0] == 0)
                    continue;
                else {
                    isVisited = new boolean[N][M];
                    isVisited[i][j] = true;
                    return dfs(i, j, 1);
                }
            }
        }

        // 모두 녹은 상태
        return -1;
    }

    private static int dfs(int x, int y, int area) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + mx[i];
            int nextY = y + my[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                continue;
            if (isVisited[nextX][nextY])
                continue;
            if (Map[nextX][nextY][0] == 0)
                continue;
            else {
                isVisited[nextX][nextY] = true;
                area = dfs(nextX, nextY, area + 1);
            }
        }

        return area;
    }
}
