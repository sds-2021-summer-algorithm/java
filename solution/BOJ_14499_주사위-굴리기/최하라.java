import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[] rDice, cDice;
    static int[][] board;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // set variables
        rDice = new int[3];
        cDice = new int[4];
        board = new int[N][M];

        // build board
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        // 2. 다이스 이동
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());

            moveDice(dir);
        }

        // 3. 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void moveDice(int dir) {
        int current;
        switch (dir) {
            case 1: // 동
                if (y + 1 == M)
                    return; // 범위 벗어남
                rotateRow(0); // 주사위 굴리기
                current = board[x][++y]; // 현재 위치의 보드 숫자
                updateDice(current); // 주사위 업데이트
                break;
            case 2: // 서
                if (y - 1 < 0)
                    return;
                rotateRow(1);
                current = board[x][--y];
                updateDice(current);
                break;
            case 3: // 북
                if (x - 1 < 0)
                    return;
                rotateCol(0);
                current = board[--x][y];
                updateDice(current);
                break;
            case 4: // 남
                if (x + 1 == N)
                    return;
                rotateCol(1);
                current = board[++x][y];
                updateDice(current);
                break;
        }
    }

    static void rotateRow(int dir) {
        int tmp;
        switch (dir) {
            case 0: // 우
                tmp = cDice[3];
                cDice[3] = rDice[2];
                rDice[2] = rDice[1];
                rDice[1] = rDice[0];
                rDice[0] = tmp;
                break;
            case 1: // 좌
                tmp = cDice[3];
                cDice[3] = rDice[0];
                rDice[0] = rDice[1];
                rDice[1] = rDice[2];
                rDice[2] = tmp;
                break;
        }
        cDice[1] = rDice[1];
    }

    static void rotateCol(int dir) {
        int tmp;
        switch (dir) {
            case 1: // 북
                tmp = cDice[3];
                for (int i = 3; i > 0; i--)
                    cDice[i] = cDice[i - 1];
                cDice[0] = tmp;

                break;
            case 0: // 남
                tmp = cDice[0];
                for (int i = 0; i < 3; i++)
                    cDice[i] = cDice[i + 1];
                cDice[3] = tmp;
                break;
        }
        rDice[1] = cDice[1];

    }

    static void updateDice(int current) {
        if (current == 0)
            board[x][y] = cDice[3];
        else {
            cDice[3] = board[x][y];
            board[x][y] = 0;
        }
        sb.append(rDice[1]).append("\n"); // 결과 저장
    }
}