import java.io.*;
import java.util.*;

public class Main {

    static int N, L, R, Day = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] A;
    static class Nation{
        int r, c;
        public Nation(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static class United{
        int all;
        ArrayList<Nation> nations;
        public United(){
            this.all = 0;
            this.nations = new ArrayList<>();
        }
    }static ArrayList<United> unites;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    
        while(true){
            lookup();
            if (unites.size()==0)
                break;
            move();
            Day++;
        }
        bw.write(Day+"\n");
    
        bw.flush();
        bw.close();
        br.close();
    }
    static void lookup(){
        boolean[][] visit = new boolean[N][N];
        unites = new ArrayList<>();

        for (int i = 0; i < N; i++) { // 0행 부터 N-1행까지
            for (int j = 0; j < N; j++) {
                Nation from = new Nation(i, j);
                for (int w = 1; w < 3; w++) { // 오른쪽하고 아랫쪽만 확인
                    Nation to = new Nation(i + dr[w], j + dc[w]);

                    if(can_move(from, to) && !visit[to.r][to.c]){ // 갈 수 있고, 간적없으면,
                        United tmp = new United(); // 새로운 연합 만들고,
                        Queue<Nation> Q = new LinkedList();
                        Q.add(from);
                        visit[from.r][from.c] = true;
                        while (!Q.isEmpty()){ // BFS
                            Nation cur = Q.poll();
                            tmp.nations.add(cur); // 연합에 추가
                            tmp.all += A[cur.r][cur.c]; // 연합인구 추가
                            for (int k = 0; k < 4; k++) {
                                Nation next = new Nation(cur.r + dr[k], cur.c + dc[k]);
                                if(can_move(cur, next) && !visit[next.r][next.c]) {// 갈 수 있고, 간적없으면,
                                    Q.add(next);
                                    visit[next.r][next.c] = true; // 확인
                                }
                            }
                        }
                        unites.add(tmp);
                    }

                }
            }
        }
    }
    static boolean can_move(Nation from, Nation to){
        if (0<=to.r && to.r<N && 0<= to.c && to.c<N // 범위안에 있고
                && L <= Math.abs(A[to.r][to.c] - A[from.r][from.c]) // 최솟값 만족하고
                && Math.abs(A[to.r][to.c] - A[from.r][from.c]) <= R) // 최댓값 만족하면
            return true; // 이동가능
        return false; // 이동못함
    }
    static void move(){
        for (United united : unites){
            int population = united.all / united.nations.size();
            for (Nation nation : united.nations){
                A[nation.r][nation.c] = population;
            }
        }
    }
}
