import java.io.*;
import java.util.*;

public class Main {

    static int N, M, area = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] board;
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }static ArrayList<Point> V = new ArrayList();
    static Queue<Point> Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==2)
                    V.add(new Point(i, j)); // 바이러스 위치 체크
            }
        }
        pick(0, 0, 3); // (0, 0) 에서 부터 3개 뽑는다.
        bw.write(area+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static void pick(int y, int x, int remain){
        if (remain==0){ // 다 뽑았으면
            spread(); // 바이러스 확산
            return;
        }
        for (int i = y; i < N; i++) { // 이전에 뽑은 위치에서부터 시작
            for (int j = y==i ? x : 0; j < M; j++) {
                if(board[i][j]==0){
                    board[i][j] = 1;
                    pick(i, j, remain-1);
                    board[i][j] = 0;
                }
            }
        }
    }
    static void spread(){
        int[][] copy = new int[N][M]; // deep copy를 위한 배열 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = board[i][j];
            }
        }
        Q = new LinkedList(); // queue 초기화
        for(Point virus : V){
            Q.add(virus); // 초기 바이러스 위치 추가하고
        }
        int nr, nc;
        while(!Q.isEmpty()){
            Point now = Q.poll(); // 큐에서 하나씩 뽑아내며
            for (int i = 0; i < 4; i++) { // 4방향 돌면서
                nr = now.r + dr[i];
                nc = now.c + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M && copy[nr][nc]==0){ // 조건 만족하면
                    copy[nr][nc] = 2; // 바이러스 옮기고
                    Q.add(new Point(nr, nc)); // 큐 추가
                }
            }
        }
        chk(copy); // 다 퍼지면 빈공간 탐색
    }
    static void chk(int[][] copy){
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j]==0) // 빈공간일때 증가
                    count++;
            }
        }
        area = Math.max(area, count); // 최대 면적 갱신
    }
}
