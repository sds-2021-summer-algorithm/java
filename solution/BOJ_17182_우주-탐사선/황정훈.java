import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K, mintime = 10000;
    static boolean[] visit;
    static int[][] adj, dist;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        visit = new boolean[N];
        visit[K] = true;
        adj = new int[N][N];
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = adj[i][j];
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        search(K, 0, 1);

        bw.write(mintime+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void search(int s, int time, int visited){
        if(visited==N){
            mintime = Math.min(mintime, time);
            return;
        }

        for (int e = 0; e < N; e++) {
            if(!visit[e]) {
                visit[e] = true;
                search(e, time+dist[s][e], visited+1);
                visit[e] = false;
            }
        }
    }
}
