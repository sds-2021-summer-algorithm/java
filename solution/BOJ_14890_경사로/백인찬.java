import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
            boolean isRoad = true;
            int curValue = map[i][0];
            int sameCount = 1;
            for (int j = 1; j < N; j++) {
                if (curValue != map[i][j]) {
                    if(Math.abs(curValue - map[i][j]) > 1) {
                        isRoad = false;
                        break;
                    }
                    if(curValue < map[i][j]) { // 높아지는 경우
                        if(sameCount >= L) { // 경사로 놔서 이동 가능
                            curValue = map[i][j];
                            sameCount = 1;
                        }
                        else {
                            isRoad = false;
                            break;
                        }
                    }
                    else { // 낮아지는 경우
                        int lowCount = checkSuccessive(map[i][j], i, j, 3);
                        if(lowCount >= L) {
                            curValue = map[i][j];
                            j = j + L - 1;
                            sameCount = 0;
                        } else {
                            isRoad = false;
                            break;
                        }
                    }
                } else {
                    sameCount++;
                }
            }
            if(!isRoad) {
                curValue = map[i][N - 1];
                sameCount = 1;
                isRoad = true;
                for (int j = N - 2; j >= 0; j--) {
                    if (curValue != map[i][j]) {
                        if(Math.abs(curValue - map[i][j]) > 1) {
                            isRoad = false;
                            break;
                        }
                        if (curValue < map[i][j]) { // 높아지는 경우
                            if (sameCount >= L) { // 경사로 놔서 이동 가능
                                curValue = map[i][j];
                                sameCount = 1;
                            } else {
                                isRoad = false;
                                break;
                            }
                        } else { // 낮아지는 경우
                            int lowCount = checkSuccessive(map[i][j], i, j, 2);
                            if (lowCount >= L) {
                                curValue = map[i][j];
                                j = j - L + 1;
                                sameCount = 0;
                            } else {
                                isRoad = false;
                                break;
                            }
                        }
                    } else {
                        sameCount++;
                    }
                }
            }
            if(isRoad) roads++;
        }
        for (int j = 0; j < N; j++) {
            boolean isRoad = true;
            int curValue = map[0][j];
            int sameCount = 1;
            // 상 -> 하
            for (int i = 1; i < N; i++) {
                if (curValue != map[i][j]) {
                    if(Math.abs(curValue - map[i][j]) > 1) {
                        isRoad = false;
                        break;
                    }
                    if(curValue < map[i][j]) { // 높아지는 경우
                        if(sameCount >= L) { // 경사로 놔서 이동 가능
                            curValue = map[i][j];
                            sameCount = 1;
                        }
                        else {
                            isRoad = false;
                            break;
                        }
                    }
                    else { // 낮아지는 경우
                        int lowCount = checkSuccessive(map[i][j], i, j, 1);
                        if(lowCount >= L) {
                            curValue = map[i][j];
                            i = i + L - 1;
                            sameCount = 0;
                        } else {
                            isRoad = false;
                            break;
                        }
                    }
                } else {
                    sameCount++;
                }
            }
            if(!isRoad) {
                // 하 -> 상
                curValue = map[N - 1][j];
                sameCount = 1;
                isRoad = true;
                for (int i = N - 2; i >= 0; i--) {
                    if (curValue != map[i][j]) {
                        if(Math.abs(curValue - map[i][j]) > 1) {
                            isRoad = false;
                            break;
                        }
                        if (curValue < map[i][j]) { // 높아지는 경우
                            if (sameCount >= L) { // 경사로 놔서 이동 가능
                                curValue = map[i][j];
                                sameCount = 1;
                            } else {
                                isRoad = false;
                                break;
                            }
                        } else { // 낮아지는 경우
                            int lowCount = checkSuccessive(map[i][j], i, j, 0);
                            if (lowCount >= L) {
                                curValue = map[i][j];
                                i = i - L + 1;
                                sameCount = 0;
                            } else {
                                isRoad = false;
                                break;
                            }
                        }
                    } else {
                        sameCount++;
                    }
                }
            }
            if(isRoad) roads++;
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
