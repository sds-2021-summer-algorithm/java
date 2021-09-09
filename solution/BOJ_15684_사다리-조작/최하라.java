import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, H, minNew;
    private static boolean[][] ladderMap;
    private static boolean done = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 2. 사다리 짓기
        ladderMap = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ladderMap[a][b + 1] = true;
        }

        // 3. 총 3개까지 다리 놔보기
        for (int i = 0; i <= 3; i++) {
            minNew = i; // 추가 가로선 최소 값
            dfs(1, 0); // i 만큼 build 해보기
            if (done)
                break;
        }

        System.out.println((done) ? minNew : -1);
    }

    private static void dfs(int x, int count) {
        // 답을 찾았다면 더이상 연산하지 않음
        if (done)
            return;

        if (minNew == count) { // minNew 만큼 사다리를 놓은 상태에서
            if (possible())
                done = true; // 모든 세로선 조건 만족 체크
            return;
        }

        for (int i = x; i <= H; i++) {
            for (int j = 1; j < N; j++) { // 1번부터 마지막 전 세로선까지
                if (!ladderMap[i][j + 1] && !ladderMap[i][j]) { // 가로선 존재 X
                    ladderMap[i][j + 1] = true; // 임시 가로선
                    dfs(i, count + 1);
                    ladderMap[i][j + 1] = false; // 임시 가로선 제거
                }
            }
        }
    }

    // 세로선 조합 체크
    static boolean possible() {
        for (int i = 1; i <= N; i++) { // 모든 세로선 체크
            int row = 1, col = i;
            while (row <= H) {
                if (ladderMap[row][col]) // 왼쪽 이동
                    col--;
                else if (col + 1 <= N) // 오른쪽 이동
                    if (ladderMap[row][col + 1])
                        col++;

                row++; // 아래로 이동
            }
            if (col != i)
                return false; // 시작 세로선과 끝 세로선 같은지 확인
        }
        return true; // 모든 결과 같음
    }
}