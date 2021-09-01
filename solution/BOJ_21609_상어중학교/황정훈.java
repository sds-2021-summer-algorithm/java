import java.io.*;
import java.util.*;

public class Main {

    static int N, M, score = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static Queue<Point> Q;
    static class Block{
        int main;
        ArrayList<Point> nonrain, rain;
        public Block(Point p, int color){
            this.main = color;
            this.nonrain = new ArrayList<>();
            this.rain = new ArrayList<>();
            this.nonrain.add(p);
        }
        public int size(){
            return this.nonrain.size() + this.rain.size();
        }
    }
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            Block best = find(); // 가장 큰 블록 찾고
            if (best==null) // 없으면
                break; // 종료
            remove(best); // 찾은 블록 없애고, 점수추가
            drop(); // 중력적용
            rotate(); // 회전
            drop(); // 중력적용
        }
        bw.write(score+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static Block find(){
        Block best = new Block(new Point(0, 0), 0);
        boolean[][] visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]>0 && !visit[i][j]) { // 색이 존재하고, 확인한적 없으면
                    visit[i][j] = true;
                    Block tmp = new Block(new Point(i, j), map[i][j]); // 새 블록만들고
                    Q = new LinkedList();
                    Q.add(new Point(i, j));
                    while (!Q.isEmpty()){
                        Point now = Q.poll();
                        for (int w = 0; w < 4; w++) { // 4방향 돌면서
                            Point nxt = new Point(now.r+dr[w], now.c+dc[w]);
                            if (0<=nxt.r && nxt.r<N && 0<=nxt.c && nxt.c<N && !visit[nxt.r][nxt.c] && (map[nxt.r][nxt.c]==0 || map[nxt.r][nxt.c]==tmp.main)){ // 확인한적없고, 조건 만족하면
                                Q.add(nxt);
                                if (map[nxt.r][nxt.c]==0) // 무지개 블록 추가
                                    tmp.rain.add(nxt);
                                else
                                    tmp.nonrain.add(nxt);
                                visit[nxt.r][nxt.c] = true; // 표시.
                            }
                        }
                    }
                    for (Point p : tmp.rain){ // 블록 원소들 중 무지개 색은
                        visit[p.r][p.c] = false; // 다시 확인 안하걸로 표시
                    }
                    if((tmp.size()>=2 && tmp.size()>best.size()) || (tmp.size()>=2 && tmp.size()==best.size() && tmp.rain.size() >= best.rain.size())) // 최선책 갱신
                        best = tmp;
                }
            }
        }
        return best.main!=0 ? best : null;
    }
    static void remove(Block block){
        score += block.size()*block.size(); // score 추가
        for (Point p : block.nonrain){ // 블록에 있는 모든 무지개 아닌 원소들
            map[p.r][p.c] = -2; // 전부 빈값으로.
        }
        for (Point p : block.rain){ // 블록에 있는 모든 무지개 원소들
            map[p.r][p.c] = -2; // 전부 빈값으로.
        }
    }
    static void drop(){
        for (int j = 0; j < N; j++) {
            for (int i = N-2; i >= 0; i--) { // 왼쪽 아랫 행부터 위로 살펴보며
                if(map[i][j] > -1 && map[i+1][j] == -2) { // 지금은 있는데, 아랫쪽 색이 비어있으면,
                    int k=i;
                    while (k < N-1 && map[k+1][j] == -2) { // 다음칸이 비어있을때까지 밑으로 내리기
                        map[k + 1][j] = map[k][j];
                        map[k][j] = -2;
                        k++;
                    }
                }
            }
        }
    }
    static void rotate(){
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N-1-j][i] = map[i][j]; // 왼쪽으로 90도 회전
            }
        }
        map = tmp;
    }
}
