import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] parent;
    static int[] depth;
    static ArrayList<Integer> edge[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());

        // build graph
        edge = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            edge[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }

        // 높이 찾기
        K = 0;
        for (int i = 1; i <= N; i *= 2)
            K++;

        // dfs를 사용해 각 노드의 depth찾기
        depth = new int[N + 1];
        parent = new int[K][N + 1];
        dfs(1, 1);

        // set parent
        setParent();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(LCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int id, int count) {
        // depth 저장
        depth[id] = count;

        // 자식 depth 저장
        int size = edge[id].size();
        for (int i = 0; i < size; i++) {
            int next = edge[id].get(i);
            if (depth[next] == 0) {
                dfs(next, count + 1);
                parent[0][next] = id;
            }
        }
        return;
    }

    static void setParent() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++)
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
        }
    }

    static int LCA(int a, int b) {
        // depth[a] >= depth[b]가 되도록 조정
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // a가 더 깊다면 2^K 높이로 점프하여 맞추기
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b])
                a = parent[i][a];
        }

        // depth가 같다면 종료
        if (a == b)
            return a;

        // 같은 depth 이면서 조상이 다를 때
        for (int i = K - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}