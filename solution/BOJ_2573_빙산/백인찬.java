import java.io.*;
import java.util.*;

public class Main {
    static int N, M, iceCount = 0;
    static int[][] map, seaCount;
    // 상 하 좌 우
    static final int[] mx = {0, 0, -1, 1};
    static final int[] my = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) iceCount++;
            }
        }

        int year = 0;
        while(bfs()) year++;

        if(iceCount == 0) bw.write("0\n");
        else bw.write(year + "\n");
        bw.flush();
        bw.close();


    }
    static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        seaCount = new int[N][M];
        int chunkCount = 0, steps = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    chunkCount++;
                    if(chunkCount >= 2) return false;
                    q.add(new int[]{i, j});
                    visited[i][j] = true;

                    while(!q.isEmpty()) {
                        int[] cur = q.remove();
                        int count = 0;
                        for (int k = 0; k < 4; k++) {
                            int ty = cur[0] + my[k];
                            int tx = cur[1] + mx[k];

                            if (ty >= 0 && ty < N && tx >= 0 && tx < M) {
                                if(!visited[ty][tx]) {
                                    if(map[ty][tx] == 0) count++;
                                    else {
                                        visited[ty][tx] = true;
                                        q.add(new int[]{ty, tx});
                                        steps++;
                                    }
                                }
                            }
                        }
                        seaCount[cur[0]][cur[1]] = count;
                    }
                }
                if(steps == iceCount) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        iceCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    map[i][j] = Math.max(0, map[i][j] - seaCount[i][j]);
                    if(map[i][j] != 0) iceCount++;
                }
            }
        }
        return iceCount != 0;
    }
}