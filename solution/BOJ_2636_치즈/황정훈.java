import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int R, C, T = 0, pred = 0, remain = 0;
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

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        do {
            remain = melt();
            pred = predict();
            T++;
        }while(remain > pred);

        bw.write(T+"\n"+remain+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static int predict(){
        boolean[][] visit = new boolean[R][C];
        Queue<Point> Q = new ArrayDeque<>();
        Q.add(new Point(0, 0));
        visit[0][0] = true;
        int tmp = 0;

        while(!Q.isEmpty()){
            Point cur = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(0<=nr && nr<R && 0<=nc && nc<C && !visit[nr][nc]){
                    visit[nr][nc] = true;
                    if(map[nr][nc]==0) Q.add(new Point(nr, nc));
                    else if(map[nr][nc]==1){
                        map[nr][nc] = 2;
                        tmp++;
                    }
                }
            }
        }
        return tmp;
    }
    static int melt(){
        int tmp = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==2) map[i][j] = 0;
                else if (map[i][j]==1) tmp++;
            }
        }
        return tmp;
    }
}
