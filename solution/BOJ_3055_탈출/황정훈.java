import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int R, C;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static class Point{
        int r, c, dist;
        public Point(int d, int r, int c){
            this.dist = d;
            this.r = r;
            this.c = c;
        }
    }
    static Point S, D;
    static Queue<Point> Q = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (row[j]=='S') S = new Point(0, i, j);
                else if (row[j]=='D') D = new Point(0, i, j);
                else if (row[j]=='*') {
                    map[i][j] = 2500; // inf
                    Q.add(new Point(2500, i, j));
                }
                else if (row[j]=='X') map[i][j] = 2500; // inf
            }
        }

        Q.add(S);
        while(!Q.isEmpty()){
            Point cur = Q.poll();
            if (cur.r==D.r && cur.c==D.c)
                break;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if(0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc]<2500){
                    if (cur.dist==2500 && !(nr==D.r && nc==D.c)){
                        map[nr][nc] = 2500;
                        Q.add(new Point(2500, nr, nc));
                    }
                    else if(cur.dist<2500 && map[nr][nc]==0){
                        map[nr][nc] = cur.dist + 1;
                        Q.add(new Point(cur.dist + 1, nr, nc));
                    }
                }
            }
        }
        sb.append(map[D.r][D.c] != 0 ? map[D.r][D.c] : "KAKTUS");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
