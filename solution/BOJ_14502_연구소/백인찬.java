import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static List<IJ> zeros = new ArrayList<>();
    static List<IJ> twos = new ArrayList<>();
    static int N, M, max = -1;
    static boolean[] visited;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
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
                if(map[i][j] == 0) zeros.add(new IJ(i, j));
                else if(map[i][j] == 2) twos.add(new IJ(i, j));
            }
        }
        visited = new boolean[zeros.size()];
        backtracking(0, 0);

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
    static void backtracking(int now, int count) {
        if(count == 3) {
            // bfs
            // count safe area
            max = Math.max(max, bfs());
            return;
        }
        int len = zeros.size();
        for (int i = now; i < len; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            IJ cur = zeros.get(i);
            map[cur.i][cur.j] = 1;
            backtracking(i, count + 1);
            map[cur.i][cur.j] = 0;
            visited[i] = false;
        }
    }

    static int bfs() {
        Queue<IJ> q = new LinkedList<>(twos);
        boolean[][] BFSvisited = new boolean[N][M];
        for (IJ ij : twos) {
            BFSvisited[ij.i][ij.j] = true;
        }
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(map[i], 0, temp[i], 0, M);
        }
        int safeAreas = zeros.size() - 3;
        while(!q.isEmpty()) {
            IJ cur = q.remove();

            for (int i = 0; i < 4; i++) {
                int ty = cur.i + my[i];
                int tx = cur.j + mx[i];

                if (ty >= 0 && ty < N && tx >= 0 && tx < M) {
                    if(!BFSvisited[ty][tx] && temp[ty][tx] == 0) {
                        temp[ty][tx] = 2;
                        q.add(new IJ(ty, tx));
                        BFSvisited[ty][tx] = true;
                        safeAreas--;
                    }
                }
            }
        }
        return safeAreas;
    }
}

class IJ {
    int i, j;

    public IJ(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
