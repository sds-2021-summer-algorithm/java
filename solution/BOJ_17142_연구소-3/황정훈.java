import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static final int MAX = 5000;
    static int N, M, T = MAX;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map, copy;
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static ArrayList<Point> virus = new ArrayList<>();
    static Queue<Point> Q;

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int type = Integer.parseInt(st.nextToken());
                if (type==2) virus.add(new Point(i, j));
                map[i][j] = type==1 ? -1 : MAX;
            }
        }
        
        pick(0, M);

        bw.write(T < MAX ? T+"\n" : "-1\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void pick(int cur, int remain){
        if (remain==0){
            spread();
            calc();
            return;
        }

        for (int i = cur; i < virus.size()-remain+1; i++) {
            Point now = virus.get(i);
            map[now.r][now.c] = 0;
            pick(i+1, remain-1);
            map[now.r][now.c] = MAX;
        }
    }
    static void spread(){
        Q = new ArrayDeque<>();
        copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
                if(copy[i][j]==0) Q.add(new Point(i, j));
            }
        }

        while(!Q.isEmpty()){
            Point cur = Q.poll();
            for (int w = 0; w < 4; w++) {
                int nr = cur.r + dr[w];
                int nc = cur.c + dc[w];
                if(0<=nr && nr<N && 0<=nc && nc<N && copy[nr][nc]==MAX){
                    copy[nr][nc] = copy[cur.r][cur.c] + 1;
                    Q.add(new Point(nr, nc));
                }
            }
        }
    }
    static void calc(){
        for (Point v : virus){
            copy[v.r][v.c] = 0;
        }
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp = Math.max(tmp, copy[i][j]);
            }
        }
        T = Math.min(T, tmp);
    }
}
