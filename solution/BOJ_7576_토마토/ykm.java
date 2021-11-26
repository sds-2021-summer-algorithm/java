
import java.io.*;
import java.util.*;

public class Main {
    static int map[][];
    static int row;
    static int col;

    static Queue<int[]> q = new LinkedList<int[]>();
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int area = 0; // 익혀야 되는 토마토의 개수

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.add(new int[] { i, j, 1 });
                } else if (map[i][j] == 0) {
                    area++;
                }
            }
        }

        if (area == 0) {
            System.out.println(0);
        } else {
            int turn = spread();

            if (area != 0)
                System.out.println(-1);
            else
                System.out.println(turn);

        }

        br.close();

    }

    private static int spread() {
        int turn = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col)
                    continue;

                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = current[2] + 1;
                    turn = Math.max(turn, map[nextX][nextY]);
                    q.add(new int[] { nextX, nextY, current[2] + 1 });
                    area --;
                }
            }
        }

        return turn - 1 ;
    }
}
