import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static boolean done = false;
    static boolean[][] already;
    static int N, M, D = -1;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] arr;
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static ArrayList<Pos> pre, post;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        already = new boolean[N][M];
        pre = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) pre.add(new Pos(i, j));
            }
        }

        while(!done){
            check();
            flow();
            if(pre.isEmpty() && !done){
                D = -1;
                break;
            }
            D++;
        }
        bw.write(D+"\n");

        br.close();
        bw.flush();
        bw.close();
    }
    static void check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) return;
            }
        }
        done = true;
    }
    static void flow(){
        post = new ArrayList<>();
        for(Pos point : pre){
            int r = point.r;
            int c = point.c;
            already[r][c] = true;
            for (int w = 0; w < 4; w++) {
                int nr = r + dr[w];
                int nc = c + dc[w];
                if (0<=nr && nr<N && 0<=nc && nc<M && arr[nr][nc]==0 && !already[nr][nc]){
                    arr[nr][nc] = 1;
                    post.add(new Pos(nr, nc));
                    already[nr][nc] = true;
                }
            }
        }
        pre = post;
    }
}
