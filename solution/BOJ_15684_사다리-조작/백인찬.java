import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] ladder; // [i][j] : [i][j+1]로 가는 사다리가 있는가?
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H + 1][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }
        if(move()) {
            bw.write("0\n");
        } else {
            // backtracking
            for (int i = 1; i <= 3; i++) {
                backtrack(1, 0, i);
            }
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
    }
    static void backtrack(int i, int count, int max) {
        if(count > max) return;

        if(move()) {
            System.out.println(count);
            System.exit(0); // max 개수를 1,2,3 으로 순차적으로 증가시키고 있으니 바로 exit해도 됨
        }

        for (int r = i; r <= H; r++) {
            for (int c = 1; c < N; c++) {
                if(ladder[r][c]) continue;
                // 사다리 놓기
                if(N > 2) {
                    if (c == 1) {
                        if (ladder[r][c + 1]) continue;
                    }
                    else if (c == N - 1) {
                        if(ladder[r][c - 1]) continue;
                    }
                    else {
                        if (ladder[r][c - 1] || ladder[r][c + 1]) continue;
                    }
                }
                ladder[r][c] = true;
                backtrack(r, count + 1, max);
                ladder[r][c] = false;
            }
        }
    }
    static boolean move() {
        for (int i = 1; i <= N; i++) {
            int nowRow = 1;
            int nowCol = i;
            while(nowRow <= H) {
                if(nowCol == 1) { // 맨 왼쪽 사다리에서는 오른쪽과 연결된 사다리만 체크
                    if(ladder[nowRow][nowCol]) {
                        nowCol++;
                    }
                } else if (nowCol == N) { // 맨 오른쪽 사다리에서는 왼쪽과 연결된 사다리만 체크
                    if (ladder[nowRow][nowCol - 1]) {
                        nowCol--;
                    }
                } else { // 가운데에서는 양쪽의 사다리를 체크 (접하거나 연결된 경우는 없으니 else if로 처리)
                    if (ladder[nowRow][nowCol]) {
                        nowCol++;
                    } else if (ladder[nowRow][nowCol - 1]) {

                        nowCol--;
                    }
                }
                nowRow++;
            }
            if(nowCol != i) return false;
        }
        return true;
    }
}
