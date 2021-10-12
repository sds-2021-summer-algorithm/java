import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int end, cost, time;

        public Edge(int end, int cost, int time) {
            this.end = end;
            this.cost = cost;
            this.time = time;
        }
    }
    static ArrayList<Edge>[] edges;
    static int[][] dp;
    static int N, M, K;
    static final int MAX = 1000000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N + 1];
            dp = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                edges[i] = new ArrayList<>();
                Arrays.fill(dp[i], MAX);
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                edges[u].add(new Edge(v, c, d));
            }
            dijkstra();
            int min = MAX;
            for (int i = 0; i <= M; i++) {
                min = Math.min(min, dp[N][i]);
            }
            if(min == MAX) sb.append("Poor KCM\n");
            else sb.append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.time == o2.time) {
                return o1.cost - o2.cost;
            }
            return o1.time - o2.time;
        });
        pq.add(new Edge(1, 0, 0));
        dp[1][0] = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.remove();
            if(cur.end == N) break;
            for (Edge next : edges[cur.end]) {
                int nextCost = next.cost + cur.cost;
                int nextTime = next.time + cur.time;
                if (nextCost > M) continue;

                if (dp[next.end][nextCost] > nextTime) {
//                    dp[next.end][nextCost] = nextTime;
                    for (int i = nextCost; i <= M; i++) {
                        dp[next.end][i] = Math.min(nextTime, dp[next.end][i]);
                    }
                    pq.add(new Edge(next.end, nextCost, nextTime));
                }
            }
        }
    }
}