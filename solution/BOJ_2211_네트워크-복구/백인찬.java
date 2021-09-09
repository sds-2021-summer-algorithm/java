import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000000;
    static int N, M;
    static List[] edges;
    static int[] dist;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];
        dist = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            dist[i] = MAX;
        }
        dist[1] = 0;
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<EDGE>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new EDGE(b, c));
            edges[b].add(new EDGE(a, c));
        }
        dijkstra();
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(i == parent[i]) continue;
            sb.append(i).append(" ").append(parent[i]).append("\n");
            count++;
        }
        bw.write(count + "\n" + sb.toString());
        bw.flush();
        bw.close();
    }
    static void dijkstra() {
        PriorityQueue<EDGE> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        pq.add(new EDGE(1, 0));
        while(!pq.isEmpty()) {
            EDGE cur = pq.remove();

            for (int i = 0; i < edges[cur.to].size(); i++) {
                EDGE edge = (EDGE) edges[cur.to].get(i);
                int nextDist = edge.distance + dist[cur.to];
                if(nextDist < dist[edge.to]) {
                    dist[edge.to] = nextDist;
                    parent[edge.to] = cur.to;
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
