import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M, K, min = Integer.MAX_VALUE;
    static int[][] arr, info, copy, tmp;
    static boolean[] picked;
    static Stack<Integer> jobq = new Stack<>();

    public static void main(String[] args) throws IOException{

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        copy = new int[N][M];
        tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        info = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken())-1;
            info[i][1] = Integer.parseInt(st.nextToken())-1;
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        picked = new boolean[K];
        pick(K);

        bw.write(min+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static void pick(int remain){
        if(remain==0){
            calc();
            return;
        }
        for (int i = 0; i < K; i++) {
            if(!picked[i]){
                picked[i] = true;
                jobq.add(i);
                pick(remain-1);
                jobq.pop();
                picked[i] = false;
            }
        }
    }
    static void calc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        for(Integer i : jobq){
            rotate(info[i][0], info[i][1], info[i][2]);
        }

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += copy[i][j];
            }
            min = Math.min(min, sum);
        }
    }
    static void rotate(int r, int c, int s){
        if(s==0) return;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = copy[i][j];
            }
        }

        for (int i = 0; i < 2*s; i++) {
            copy[r-s][c-s+i+1] = tmp[r-s][c-s+i];
        }
        for (int i = 0; i < 2*s; i++) {
            copy[r-s+i+1][c+s] = tmp[r-s+i][c+s];
        }
        for (int i = 0; i < 2*s; i++) {
            copy[r+s][c+s-i-1] = tmp[r+s][c+s-i];
        }
        for (int i = 0; i < 2*s; i++) {
            copy[r+s-i-1][c-s] = tmp[r+s-i][c-s];
        }

        rotate(r, c, s-1);
    }
}
