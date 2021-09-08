import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000000;
    static List[] edges;
    static int N, M;
    static int[] dist;
    static int[] parent; // path[i] = i에서 다음 경로
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
            edges[i] = new ArrayList<EDGE>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges[a].add(new EDGE(b, t));
            edges[b].add(new EDGE(a, t));
        }
        Arrays.fill(dist, MAX);
        dist[1] = 0;
        PriorityQueue<EDGE> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        pq.add(new EDGE(1, 0));
        dijkstra(true, 0, 0);
        int initial = dist[N];
        int delay = 0;
        for (int i = N; i >= 1; i = parent[i]) {
            int block = parent[i];
            Arrays.fill(dist, MAX);
            dist[1] = 0;
            dijkstra(false, i, block);
            if(dist[N] == MAX) {
                System.out.println(-1);
                return;
            }
            delay = Math.max(delay, dist[N] - initial);
        }
        bw.write(delay + "\n");
        bw.flush();
        bw.close();
    }
    static void dijkstra(boolean initial, int blockA, int blockB) {
        PriorityQueue<EDGE> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        pq.add(new EDGE(1, 0));
        while (!pq.isEmpty()) {
            EDGE cur = pq.remove();
            if(dist[cur.to] < cur.distance) continue;
            for (int j = 0; j < edges[cur.to].size(); j++) {
                EDGE edge = (EDGE) edges[cur.to].get(j);
                if((blockA == edge.to && blockB == cur.to) || (blockA == cur.to && blockB == edge.to)) continue;
                int nextDist = dist[cur.to] + edge.distance;
                if(nextDist < dist[edge.to]) {
                    if(initial) parent[edge.to] = cur.to;
                    dist[edge.to] = nextDist;
                    pq.add(edge);
                }
            }
        }
    }
    private static class EDGE {
        int to;
        int distance;

        public EDGE(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}

