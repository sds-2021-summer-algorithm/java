import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M, zeroCount = 0;
    static List<int[]> candidate = new ArrayList<>();
    static boolean[] selected;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int min = Integer.MAX_VALUE;
    static boolean cant = true;
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
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if (val == 2) candidate.add(new int[]{i, j});
                if (val == 0) zeroCount++;
            }
        }
        selected = new boolean[candidate.size()];
        backtracking(0, 0);
        if(cant) bw.write("-1\n");
        else bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static void backtracking(int now, int count) {
        int len = selected.length;
        if (count == M) {
            visited = new boolean[N][N];
            q = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                if(selected[i]){
                    int[] cur = candidate.get(i);
                    visited[cur[0]][cur[1]] = true;
                    q.add(new int[]{cur[0], cur[1], 0});
                }
            }

            bfs();
            return;
        }
        for (int i = now; i < len; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            backtracking(i, count + 1);
            selected[i] = false;
        }
    }

    public static void bfs() {
        int max = Integer.MIN_VALUE;
        int count = 0;
        while(!q.isEmpty()) {
            int[] cur = q.remove();

            for (int i = 0; i < 4; i++) {
                int ty = cur[0] + my[i];
                int tx = cur[1] + mx[i];

                if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                    if(map[ty][tx] != 1 && !visited[ty][tx]) {
                        visited[ty][tx] = true;
                        q.add(new int[]{ty, tx, cur[2] + 1});
                        if(map[ty][tx] == 0) {
                            count++;
                            max = Math.max(max, cur[2] + 1);
                        }
                    }
                }
            }
        }
        if(max == Integer.MIN_VALUE) max = 0;
        if(count == zeroCount) {
            cant = false;
            min = Math.min(max, min);
        }
    }
}

