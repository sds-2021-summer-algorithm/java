import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000000002;
    static int T, N, D, C;
    static int[] dist;
    static List[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            edges = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                edges[i] = new ArrayList<EDGE>();
            }
            dist = new int[N + 1];
            Arrays.fill(dist, MAX);
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                edges[b].add(new EDGE(a, s));
            }
            dijkstra();
            int conta = 0;
            int time = 0;
            for (int b : dist) {
                if(b != MAX){
                    conta++;
                    if(time < b) {
                        time = b;
                    }
                }

            }
            sb.append(conta).append(" ").append(time).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void dijkstra() {
        PriorityQueue<EDGE> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        dist[C] = 0;
        pq.add(new EDGE(C, 0));
        while (!pq.isEmpty()) {
            EDGE cur = pq.remove();
            for (int i = 0; i < edges[cur.to].size(); i++) {
                EDGE edge = (EDGE) edges[cur.to].get(i);
                int nextDist = edge.distance + dist[cur.to];
                if(dist[edge.to] > nextDist) {
                    dist[edge.to] = nextDist;
                    pq.add(edge);
                }
            }
        }
    }
    private static class EDGE {
        int to, distance;

        public EDGE(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
