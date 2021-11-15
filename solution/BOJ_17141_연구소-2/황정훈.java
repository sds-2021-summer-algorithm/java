import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, MaxTime = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map, copy;
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static ArrayList<Pos> virus = new ArrayList<>();
    static Queue<Pos> Q;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = Integer.MAX_VALUE;
                if (type==1) map[i][j] = -1;
                else if (type==2) virus.add(new Pos(i, j));
            }
        }
        pick(0, M);

        bw.write(MaxTime<Integer.MAX_VALUE ? MaxTime+"\n" : "-1\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void pick(int cur, int remain){
        if (remain==0){
            spread();
            MaxTime = Math.min(MaxTime, calc());
            return;
        }
        for (int i = cur; i < virus.size()-remain+1; i++) {
            Pos p = virus.get(i);
            map[p.r][p.c] = 0;
            pick(i+1, remain-1);
            map[p.r][p.c] = Integer.MAX_VALUE;
        }
    }
    static void spread(){
        Q = new ArrayDeque<>();
        copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
                if (map[i][j]==0) Q.add(new Pos(i, j));
            }
        }

        while(!Q.isEmpty()){
            Pos cur = Q.poll();
            for (int w = 0; w < 4; w++) {
                int nr = cur.r + dr[w];
                int nc = cur.c + dc[w];
                if(0<=nr && nr<N && 0<=nc && nc<N && copy[nr][nc]==Integer.MAX_VALUE){
                    copy[nr][nc] = copy[cur.r][cur.c] + 1;
                    Q.add(new Pos(nr, nc));
                }
            }
        }
    }
    static int calc(){
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j]<0) continue;
                if (copy[i][j]==Integer.MAX_VALUE) return Integer.MAX_VALUE;
                tmp = Math.max(tmp, copy[i][j]);
            }
        }
        return tmp;
    }
}
