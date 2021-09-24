import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int R, C, T;
    static int Top, Bottom;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] A;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
            if(A[i][0]==-1)
                Bottom = i;
        }
        Top = Bottom - 1;

        for (int i = 0; i < T; i++) {
            spread();
            cycle();
        }
        sb.append(sum());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void cycle() {
        for (int i = Top-1; i > 0; i--) {
            A[i][0] = A[i-1][0];
        }
        for (int i = 0; i < C-1; i++) {
            A[0][i] = A[0][i+1];
        }
        for (int i = 0; i < Top; i++) {
            A[i][C-1] = A[i+1][C-1];
        }
        for (int i = C-1; i > 1; i--) {
            A[Top][i] = A[Top][i-1];
        }
        A[Top][1] = 0;

        for (int i = Bottom+1; i < R-1; i++) {
            A[i][0] = A[i+1][0];
        }
        for (int i = 0; i < C-1; i++) {
            A[R-1][i] = A[R-1][i+1];
        }
        for (int i = R-1; i > Bottom; i--) {
            A[i][C-1] = A[i-1][C-1];
        }
        for (int i = C-1; i > 1; i--) {
            A[Bottom][i] = A[Bottom][i-1];
        }
        A[Bottom][1] = 0;
    }

    static void spread() {
        int[][] tmp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(A[i][j] > 4){
                    int nr, nc, dust = A[i][j]/5;
                    for (int w = 0; w < 4; w++) {
                        nr = i + dr[w];
                        nc = j + dc[w];
                        if(0<=nr && nr<R && 0<=nc && nc<C && !(nc==0 && (nr==Top || nr==Bottom))){
                            tmp[nr][nc] += dust;
                            tmp[i][j] -= dust;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                A[i][j] += tmp[i][j];
            }
        }
    }

    static int sum(){
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(A[i][j]>0)
                    cnt+=A[i][j];
            }
        }
        return cnt;
    }
}
