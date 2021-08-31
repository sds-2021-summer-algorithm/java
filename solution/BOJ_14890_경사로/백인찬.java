import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(makeRoad() + "\n");
        bw.flush();
        bw.close();
    }
    static int makeRoad(){
        int roads = 0;
        for (int i = 0; i < N; i++) {
            // 좌 -> 우 체크
            boolean isRoad = true;
            int standardValue = map[i][0];
            for (int j = 1; j < N; j++) {
                if(standardValue != map[i][j]) {
                    if (Math.abs(standardValue - map[i][j]) > 1) {
                        isRoad = false;
                        break;
                    }
                    int nextCol = checkSuccessive(map[i][j], i, j, 3);
                    if(nextCol >= L) {
                        nextCol = L;
                        standardValue = map[i][j];
                        j += nextCol - 1;
                    } else {
                        isRoad = false;
                        break;
                    }
                }
            }
            if(isRoad) roads++;
            else {
                // 우 -> 좌
                isRoad = true;
                standardValue = map[i][N - 1];
                for (int j = N - 2; j >= 0; j--) {
                    if (standardValue != map[i][j]) {
                        if (Math.abs(standardValue - map[i][j]) > 1) {
                            isRoad = false;
                            break;
                        }
                        int nextCol = checkSuccessive(map[i][j], i, j, 2);
                        if(nextCol >= L) {
                            nextCol = L;
                            standardValue = map[i][j];
                            j = j - nextCol + 1;
                        } else {
                            isRoad = false;
                            break;
                        }
                    }
                }
                if(isRoad) roads++;
            }
        }
        for (int j = 0; j < N; j++) {
            boolean isRoad = true;
            int standardValue = map[0][j];
            for (int i = 1; i < N; i++) {
                // 상 -> 하
                if(standardValue < map[i][j]) {
                    if (Math.abs(standardValue - map[i][j]) > 1) {
                        isRoad = false;
                        break;
                    }
                    int nextRow = checkSuccessive(map[i][j], i, j, 1);
                    if(nextRow >= L) {
                        nextRow = L;
                        standardValue = map[i][j];
                        i += nextRow - 1;
                    } else {
                        isRoad = false;
                        break;
                    }
                }
            }
            if(isRoad) roads++;
            else {
                // 하 -> 상
                isRoad = true;
                standardValue = map[N - 1][j];
                for (int i = N-2; i >= 0; i--) {
                    if(standardValue < map[i][j]) {
                        if (Math.abs(standardValue - map[i][j]) > 1) {
                            isRoad = false;
                            break;
                        }
                        int nextRow = checkSuccessive(map[i][j], i, j, 0);
                        if(nextRow >= L) {
                            nextRow = L;
                            standardValue = map[i][j];
                            i =  i - nextRow + 1;
                        } else {
                            isRoad = false;
                            break;
                        }
                    }
                }
                if (isRoad) roads++;
            }
        }
        return roads;
    }
    static int checkSuccessive (int level, int row, int col, int dir){ // dir - 0 : 상, 1 : 하, 2 : 좌, 3: 우
        int[] mx = {0, 0, -1, 1};
        int[] my = {-1, 1, 0, 0};
        int count = 1;
        while(true) {
            int ty = row + my[dir];
            int tx = col + mx[dir];
            if (!(ty >= 0 && ty < N && tx >= 0 && tx < N)) break;
            if(map[ty][tx] != level) break;
            count++;
            row = ty;
            col = tx;
        }
        return count;
    }
}
