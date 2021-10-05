
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class knight {
    int r;
    int c;
    int cnt;
    knight(int r, int c, int cnt){
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}


public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int r1,c1,r2,c2;
    static int[] dr = {-2,-2,0,0,2,2};
    static int[] dc = {-1,1,-2,2,-1,1};



    public static void main(String[] args) throws IOException {
        // 1. 값 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        visit = new boolean[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        bfs();
    }

    private static void bfs() {
        Queue<knight> que = new LinkedList<knight>();
        que.add(new knight(r1,c1,0));
        visit[r1][c1] = true;

        while(!que.isEmpty()){
            knight k = que.poll();

            for(int i =0; i<6; i++){
                int nr = k.r + dr[i];
                int nc = k.c + dc[i];

                if(nr <0 || nr > N || nc < 0 || nc > N || visit[nr][nc]){
                    continue;
                }
                if(nr == r2 && nc == c2){
                    System.out.println(k.cnt+1);
                    System.exit(0);
                }
                visit[nr][nc] = true;
                que.add(new knight(nr,nc,k.cnt+1));
            }
        }
        System.out.println(-1);
    }

}
