import java.io.*;
import java.util.*;

public class Main {
    static int N, L, count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // build map
        int[][] byRow = new int[N][N];
        int[][] byCol = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                byRow[i][j] = Integer.parseInt(st.nextToken());
                byCol[j][i] = byRow[i][j];
            }
        }

        // 2. 2N개의 길 다 확인하기
        count = 0;
        for (int i = 0; i < N; i++) {
            possibleRoad(byRow[i]);
            possibleRoad(byCol[i]);
        }

        System.out.println(count);
    }

    static void possibleRoad(int[] map) {
        boolean[] visit = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            // int diff = map[i + 1] - map[i];
            if (map[i] != map[i + 1]) {

                int diff = map[i + 1] - map[i];
                if (Math.abs(diff) != 1)
                    return; // 높이 차이 1 아님

                if (diff == 1) { // 오른쪽이 높은 경우
                    for (int j = 0; j < L; j++) {
                        if (i < j || visit[i - j])
                            return; // 범위 벗어남 || 경사로 있음
                        if (map[i] == map[i - j])
                            visit[i - j] = true;
                        else
                            return;
                    }
                } else { // 왼쪽이 높은 경우
                    for (int j = 1; j <= L; j++) {
                        if (i + j >= N || visit[i + j])
                            return; // 범위 벗어남 || 경사로 있음
                        if (map[i] - 1 == map[i + j])
                            visit[i + j] = true;
                        else
                            return;
                    }
                }
            }
        }
        count++;
    }
}