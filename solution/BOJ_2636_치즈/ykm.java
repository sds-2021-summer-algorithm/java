package P2636;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int height;
    static int width;
    static int[][] cheeseShape;
    static boolean[][] visited;
    static int[] mx = { -1, 1, 0, 0 };
    static int[] my = { 0, 0, -1, 1 };
    static int cheeseArea = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        cheeseShape = new int[height][width];

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                cheeseShape[i][j] = Integer.parseInt(st.nextToken());
                if(cheeseShape[i][j]==1) cheeseArea++;
            }
        }

        int turn = 0;
        int last = 0;
        while(cheeseArea>0){
            visited = new boolean[height][width];
            last = cheeseArea;
            melt(0, 0);
            turn++;
        }
        System.out.println(turn);
        System.out.println(last);
        br.close();
    }

    // dfs
    private static void melt(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + mx[i];
            int nextY = y + my[i];

            if (nextX < 0 || nextY < 0 || nextX >= height || nextY >= width)
                continue;

            if(!visited[nextX][nextY]){
                
                if (cheeseShape[nextX][nextY] == 0) { // 공기
                    melt(nextX, nextY);
                } else { // 치즈
                    visited[nextX][nextY] = true;
                    cheeseShape[nextX][nextY] = 0;
                    cheeseArea--;
                }
            }
        }
    }
}
