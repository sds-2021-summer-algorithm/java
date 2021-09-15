import java.io.*;
import java.util.*;

public class Main {
    static List[] tree;
    static int[][] parent;
    static int[] depth;
    static int N, S, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        S = 0;
        for (int i = 1; i <= N; i *= 2) {
            S++;
        }
        parent = new int[S][N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        depth = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        Arrays.fill(depth, -1);

        dfs(1, 0);

        for (int i = 1; i < S; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
        M = Integer.parseInt(br.readLine());
        int total = 0;
        int start = Integer.parseInt(br.readLine());
        for (int i = 0; i < M - 1; i++) {
            int end = Integer.parseInt(br.readLine());
            int lca = lca(start, end);
            total += (depth[start] + depth[end] - 2 * depth[lca]);
            start = end;
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }

    static void dfs(int node, int count) {
        depth[node] = count;
        int len = tree[node].size();
        for (int i = 0; i < len; i++) {
            int next = (int) tree[node].get(i);;
            if(depth[next] == -1) {
                dfs(next, count + 1);
                parent[0][next] = node;
            }
        }
    }

    static int lca(int a, int b) {
        if(depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = S - 1; i >= 0; i--) {
            if (depth[a] <= depth[parent[i][b]]) {
                b = parent[i][b];
            }
        }
        if(a == b) return a;

        for (int i = S-1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}
