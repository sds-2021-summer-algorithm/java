import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] depth, nodeInSeq, zeroInSeq, oneInSeq;
    static int[][] parent; // j번쨰 노드의 2^i 번쨰 부모 : 2^i 번째 부모는 2^(i-1)번쨰 부모의 2^(i-1)번쨰 부모

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = 0;
        for (int i = 1; i <= N; i *= 2) {
            S++;
        }
        depth = new int[N + 1];
        parent = new int[S][N + 1];
        String seq = br.readLine();
        nodeInSeq = new int[2 * N + 1];
        zeroInSeq = new int[N + 1];
        oneInSeq = new int[N + 1];
        int len = seq.length();
        int current = 1;
        int id = 1;

        for (int i = 1; i <= len; i++) {
            if (seq.charAt(i - 1) == '0') {
                parent[0][id] = current;
                depth[id] = depth[current] + 1;
                nodeInSeq[i] = id;
                zeroInSeq[id] = i;
                current = id++;
            } else {
                nodeInSeq[i] = current;
                oneInSeq[current] = i;
                current = parent[0][current];
            }
        }
        for (int i = 1; i < S; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int A = nodeInSeq[a];
        int B = nodeInSeq[b];
        int lca = lca(A, B);
        bw.write(zeroInSeq[lca] + " " + oneInSeq[lca] + "\n");
        bw.flush();
        bw.close();
    }
    static int lca(int a, int b) {
        if(depth[a] > depth[b]) { // 항상 b가 더 깊도록
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = S - 1; i >= 0; i--) { // 깊이 맞춰주기
            if (depth[a] <= depth[parent[i][b]]) {
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
