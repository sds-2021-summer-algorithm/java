import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;
    static boolean check;

    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        // build graph
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = st.nextToken().charAt(0);
        }

        // 2. 장애물 모든 경우 확인
        check = false;
        search(0);

        // 3. 결과 출력
        System.out.println(check ? "YES" : "NO");
    }

    static void search(int stickCount) {
        if (stickCount == 3) { // 총 3개의 장애물
            if (isPossible())
                check = true;
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = 'O';
                    search(stickCount + 1);
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 모든 학생들이 감시에서 벗어 날 수 있는지 체크
    static boolean isPossible() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'S') {
                    if (!checkTeacher(i, j))
                        return false;
                }
            }
        }
        return true;
    }

    // 선생님 위치 체크
    static boolean checkTeacher(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int mx = x;
            int my = y;
            while (true) {
                mx = mx + dx[i];
                my = my + dy[i];
                if (mx < 0 || my < 0 || mx >= N || my >= N)
                    break;
                if (board[mx][my] == 'O')
                    break;
                if (board[mx][my] == 'T')
                    return false;
            }
        }
        return true;
    }
}