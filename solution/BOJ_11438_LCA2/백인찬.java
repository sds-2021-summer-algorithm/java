import java.io.*;
import java.util.*;

public class Main {
    static int[][] parent; // parent[i][j] : 노드 j의 i번째 부모
    static int[] depth; // [i] : 노드 i의 깊이
    static List[] tree;
    static int N, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 변수 초기화
        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        depth = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        // 2^S > N 인 S 찾기
        S = 0;
        for (int i = 1; i <= N; i *= 2) {
            S++;
        }
        parent = new int[S][N + 1];
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        setDepth(1, 1);

        // 2^n 부모는 2^(n-1) 부모의 2^(n-1) 부모
        for (int i = 1; i < S; i++) { // 2^0 부모는 dfs하면서 체크했으니까
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    // dfs 로 깊이 저장
    static void setDepth(int node, int count) {
        depth[node] = count;
        int len = tree[node].size();
        for (int i = 0; i < len; i++) {
            int nextNode = (int) tree[node].get(i);
            if(depth[nextNode] == 0) {
                setDepth(nextNode, count + 1);
                // 바로 위 부모도 세팅
                parent[0][nextNode] = node;
            }
        }
    }

    static int lca(int a, int b) {
        if(depth[a] > depth[b]) { // 항상 b가 더 깊도록 맞춰줌
            int temp = a;
            a = b;
            b = temp;
        }

        // b를 a와 같은 높이로 끌어올려줌
        for (int i = S - 1; i >= 0; i--) {
            if (depth[a] <= depth[parent[i][b]]) {
                b = parent[i][b];
            }
        }
        int ret = a;
        // lca 찾기
        if (a != b) {
            for (int i = S-1; i >= 0; i--) { // 0 -> S 순서로 탐색하게 되면 중간에 건너 뛰는 LCA가 발생할 수 있음
                if(parent[i][a] != parent[i][b]) {
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            ret = parent[0][a];
        }
        return ret;
    }
}
