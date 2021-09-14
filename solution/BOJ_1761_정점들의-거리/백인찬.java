import java.io.*;
import java.util.*;

public class Main {
    static List[] tree;
    static int[] depth; // depth[i]의 깊이
    static int[][] parent; // parent[i][j] : j의 2^i 번째 부모
    static int N, S;
    static int[] dist;
    static class Edge {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        depth = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Edge>();
        }
        S = 0;
        for (int i = 1; i <= N; i*=2) {
            S++;
        }
        parent = new int[S][N + 1];
        StringTokenizer st;
        dist = new int[N + 1];
        dist[1] = 0;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new Edge(b, c));
            tree[b].add(new Edge(a, c));
        }

        dfs(1, 1);

        // 2^i 번째 부모는 j의 2^i-1번째 부모의 2^i-1번째 부모
        for (int i = 1; i < S; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = lca(a, b);
            int distance = dist[a] + dist[b] - 2 * dist[lca];
            sb.append(distance).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int node, int count) {
        depth[node] = count;
        int len = tree[node].size();
        for (int i = 0; i < len; i++) {
            Edge edge = (Edge) tree[node].get(i);
            int next = edge.to;
            if(depth[next] == 0){
                dist[next] = dist[node] + edge.dist;
                dfs(next, count + 1);
                parent[0][next] = node;
            }
        }
    }
    static int lca(int a, int b) {
        if(depth[a] > depth[b]) { // 항상 b가 더 깊도록
            int temp = a;
            a = b;
            b = temp;
        }
        for (int i = S - 1; i >= 0; i--) {
            if(depth[a] <= depth[parent[i][b]]) {
                b = parent[i][b];
            }
        }

        if(a == b) return a;

        for (int i = S - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }

}
