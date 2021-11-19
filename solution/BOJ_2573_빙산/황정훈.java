import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, Y = 0, mass = 1;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static boolean[][] visit;
    static Queue<Point> Q;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(mass==1){
            Y++;
            melt();
            split_check();
        }

        bw.write(mass>1 ? Y+"\n" : "0\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void melt(){
        int[][] tmp = new int[N][M];
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                if (map[i][j]<=0) continue;
                int cnt = 0;
                for (int w = 0; w < 4; w++) {
                    int nr = i + dr[w];
                    int nc = j + dc[w];
                    if(map[nr][nc]<=0) cnt++;
                }
                tmp[i][j] -= cnt;
            }
        }

        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                map[i][j] += tmp[i][j];
            }
        }
    }
    static void split_check(){
        mass = 0;
        visit = new boolean[N][M];

        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                if (map[i][j]>0 && !visit[i][j]){
                    mass++;
                    bfs(i, j);
                }
            }
        }
    }
    static void bfs(int i, int j){
        Q = new ArrayDeque<>();
        Q.add(new Point(i, j));
        visit[i][j] = true;

        while(!Q.isEmpty()){
            Point cur = Q.poll();
            for (int w = 0; w < 4; w++) {
                int nr = cur.r + dr[w];
                int nc = cur.c + dc[w];
                if (map[nr][nc]>0 && !visit[nr][nc]){
                    Q.add(new Point(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }
    }
}
