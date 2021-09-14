import java.io.*;
import java.util.*;

public class Main {
    static List[] tree;
    static int[][] parent; // parent[i][j] : j의 2^i 번째 부모
    static int[] depth;
    static long[] dist;
    static int N, S, M;
    static class Edge {
        int to;
        long dist;

        public Edge(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        S = 0;
        for (int i = 1; i <= N; i *= 2) {
            S++;
        }
        parent = new int[S][N + 1];
        tree = new List[N + 1];
        depth = new int[N + 1];
        dist = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Edge>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            tree[u].add(new Edge(v, w));
            tree[v].add(new Edge(u, w));
        }
        dfs(1, 1);
        for (int i = 1; i < S; i++) {
            for (int j = 1; j <= N; j++) { // 2^i 번째 부모는 2^i-1번째 부모의 2^i-1번째 부모
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = lca(a, b);
            if(type == 1) {
                sb.append(dist[a] + dist[b] - 2 * dist[lca]).append("\n");
            } else {
                int k = Integer.parseInt(st.nextToken());
                sb.append(getKth(a, b, lca, k)).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static int getKth(int a, int b, int lca, int k) {
        if(k == depth[a] - depth[lca] + 1) return lca;
        if(k < depth[a] - depth[lca] + 1) { // a ~ lca에서 탐색
            int depthK = depth[a] - k + 1;
            for (int i = S - 1; i >= 0; i--) {
                if (depthK <= depth[parent[i][a]]) {
                    a = parent[i][a];
                }
            }
            return a;
        } else { // lca ~ b 에서 탐색
            int depthK = depth[lca] + (k - (depth[a] - depth[lca])) - 1;
            for (int i = S-1; i >= 0 ; i--) {
                if (depthK <= depth[parent[i][b]]) {
                    b = parent[i][b];
                }
            }
            return b;
        }
    }
    static void dfs(int node, int count) {
        depth[node] = count;
        int len = tree[node].size();
        for (int i = 0; i < len; i++) {
            Edge edge = (Edge) tree[node].get(i);
            int next = edge.to;
            if(depth[next] == 0) {
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

        for (int i = S-1; i >= 0; i--) {
            if(depth[a] <= depth[parent[i][b]]) {
                b = parent[i][b];
            }
        }

        if(a == b) return a;

        for (int i = S-1; i >= 0; i--) {
            if(parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}
