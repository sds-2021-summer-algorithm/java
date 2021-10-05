import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int end, cost;
        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static ArrayList<Edge>[] edges;
    static int n, m, t, s, g, h;
    static List<Integer> dest;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            edges = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                if((a == g && b == h) || (a == h && b == g)) {
                    edges[a].add(new Edge(b, 2 * cost - 1));
                    edges[b].add(new Edge(a, 2 * cost - 1));
                }
                else {
                    edges[a].add(new Edge(b, 2 * cost));
                    edges[b].add(new Edge(a, 2 * cost));
                }
            }
            dist = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dist[i] = Integer.MAX_VALUE / 2 * 2;
            }
            dist[s] = 0;
            dest = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int a = Integer.parseInt(br.readLine());
                dest.add(a);
            }
            dijkstra();
            List<Integer> answer = new ArrayList<>();

            for (int a : dest) {
                if(dist[a] % 2 == 1) answer.add(a);
            }
            Collections.sort(answer);

            for (int a : answer) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Edge(s, 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.remove();

            for (int i = 0; i < edges[cur.end].size(); i++) {
                Edge edge = edges[cur.end].get(i);
                int nextDist = edge.cost + dist[cur.end];
                if(nextDist < dist[edge.end]) {
                    dist[edge.end] = nextDist;
                    pq.add(edge);
                }
            }
        }
    }
}
