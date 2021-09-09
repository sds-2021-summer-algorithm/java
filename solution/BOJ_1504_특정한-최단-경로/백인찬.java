import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 200000001;
    static long[] dist;
    static List[] edges;
    static int N, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];
        dist = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<EDGE>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new EDGE(b, c));
            edges[b].add(new EDGE(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        dijkstra(1, N);
        long toV1 = dist[v1];
        long toV2 = dist[v2];
        long ret = Math.min(toV1 + dijkstra(v1, v2) + dijkstra(v2, N), toV2 + dijkstra(v2, v1) + dijkstra(v1, N));
        if(ret >= MAX) ret = -1;
        bw.write(ret + "\n");
        bw.flush();
        bw.close();
    }
    static long dijkstra(int start, int end) {
        PriorityQueue<EDGE> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        Arrays.fill(dist, MAX);
        dist[start] = 0;
        pq.add(new EDGE(start, 0));
        while(!pq.isEmpty()) {
            EDGE cur = pq.remove();
            for (int i = 0; i < edges[cur.to].size(); i++) {
                EDGE edge = (EDGE) edges[cur.to].get(i);
                long nextDist = edge.distance + dist[cur.to];
                if(nextDist < dist[edge.to]) {
                    dist[edge.to] = nextDist;
                    pq.add(edge);
                }
            }
        }
        return dist[end];
    }
    private static class EDGE {
        int to, distance;

        public EDGE(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
