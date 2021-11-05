import java.io.*;
import java.util.*;

public class Main {
    static int[][] input;
    static boolean[] selected;
    static boolean[][] visited;
    static int N, M, min = Integer.MAX_VALUE, zeroCount = 0;
    static List<int[]> candidate;
    static Queue<int[]> q;
    // 상 하 좌 우
    static final int[] mx = {0, 0, -1, 1};
    static final int[] my = {-1, 1, 0, 0};
    static boolean cant = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][N];

        candidate = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                input[i][j] = val;
                if(val == 2) candidate.add(new int[]{i, j});
                if(val != 1) zeroCount++;
            }
        }
        selected = new boolean[candidate.size()];
        backtracking(0, 0);
        if(cant) bw.write("-1\n");
        else bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
    static void backtracking(int now, int count) {
        int len = selected.length;
        if(count == M) {
            visited = new boolean[N][N];
            q = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                if(selected[i]) {
                    int[] cur = candidate.get(i);
                    q.add(new int[]{cur[0], cur[1], 0});
                    visited[cur[0]][cur[1]] = true;
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
    static void bfs() {
        int max = Integer.MIN_VALUE;
        int count = zeroCount - M;
        while(!q.isEmpty()) {
            int[] cur = q.remove();

            for (int i = 0; i < 4; i++) {
                int ty = cur[0] + my[i];
                int tx = cur[1] + mx[i];
                int time = cur[2];
                if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                    if(!visited[ty][tx] && (input[ty][tx] == 0 || input[ty][tx] == 2)) {
                        count--;
                        visited[ty][tx] = true;
                        max = Math.max(max, time + 1);
                        q.add(new int[]{ty, tx, time + 1});
                    }
                }
            }
        }
        if(max == Integer.MIN_VALUE) max = 0;
        if(count == 0) {
            cant = false;
            min = Math.min(max, min);
        }
    }
}
