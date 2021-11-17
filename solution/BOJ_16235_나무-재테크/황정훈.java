import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] A;
    static class Area{
        int hp;
        ArrayList<Integer> live;
        Queue<Integer> dead;
        public Area(){
            this.hp = 5;
            live = new ArrayList<>();
            dead = new ArrayDeque<>();
        }
    }
    static Area[][] areas;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        areas = new Area[N][N];
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                areas[i][j] = new Area();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            areas[x-1][y-1].live.add(z);
        }
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        bw.write(calc()+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void spring(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int live = areas[i][j].live.size();
                if (live > 1) Collections.sort(areas[i][j].live);
                for (int k = 0; k < areas[i][j].live.size(); k++) {
                    int age = areas[i][j].live.get(k);
                    if(areas[i][j].hp >= age){
                        areas[i][j].hp -= age;
                        areas[i][j].live.set(k, age+1);
                    }
                    else{
                        for (int l = k; l < live; l++) {
                            areas[i][j].dead.add(areas[i][j].live.remove(k));
                        }
                        break;
                    }
                }
            }
        }
    }
    static void summer(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while(!areas[i][j].dead.isEmpty()){
                    areas[i][j].hp += areas[i][j].dead.poll()/2;
                }
            }
        }
    }
    static void autumn(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int age : areas[r][c].live){
                    if (age%5 == 0){
                        for (int w = 0; w < 8; w++) {
                            int nr = r + dr[w];
                            int nc = c + dc[w];
                            if (0<=nr && nr<N && 0<=nc && nc<N) areas[nr][nc].live.add(1);
                        }
                    }
                }
            }
        }
    }
    static void winter(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                areas[i][j].hp += A[i][j];
            }
        }
    }
    static int calc(){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += areas[i][j].live.size();
            }
        }
        return cnt;
    }
}
