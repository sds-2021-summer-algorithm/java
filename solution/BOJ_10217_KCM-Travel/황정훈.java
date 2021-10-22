import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K;
    static final int MAX_TIME = 100001;
    static boolean[] visit;
    static int[][] dp;
    static class Flight{
        int dest, cost, time;
        public Flight(int dest, int cost, int time){
            this.dest = dest;
            this.cost = cost;
            this.time = time;
        }
    }
    static ArrayList<Flight>[] adj;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws Exception {
        
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            visit = new boolean[N+1];
            dp = new int[N+1][M+1];
            adj = new ArrayList[N+1];
            for (int j = 1; j < N+1; j++) {
                adj[j] = new ArrayList<>();
                Arrays.fill(dp[j], MAX_TIME);
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adj[u].add(new Flight(v, c, d));
            }
            dijk();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void dijk(){
        pq = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        pq.add(new int[]{1, 0});
        dp[1][0] = 0;

        while(!pq.isEmpty()){
            int n = pq.poll()[0];
            if(visit[n]) continue;
            visit[n] = true;
            for(Flight next : adj[n]){
                int min = MAX_TIME;
                for (int m = 0; m <= M-next.cost; m++) {
                    if(dp[n][m]==MAX_TIME) continue;
                    dp[next.dest][m+next.cost] = Math.min(dp[next.dest][m+next.cost], dp[n][m]+next.time);
                    min = Math.min(min, dp[next.dest][m+next.cost]);
                }
                pq.add(new int[]{next.dest, min});
            }
        }
        Arrays.sort(dp[N]);
        sb.append(dp[N][0]<MAX_TIME ? dp[N][0]+"\n" : "Poor KCM\n");
    }
}
