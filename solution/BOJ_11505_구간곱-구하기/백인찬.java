import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static int N, M, K, S;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        Arrays.fill(tree, 1);
        for (int i = S; i < S + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        init();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 1 : update / 2 : query
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1) {
                update(1, S, 1, b, c);
            } else {
                sb.append(query(1, S, 1, b, c)).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void init() {
        for (int i = S-1; i >= 1; i--) {
            tree[i] = (tree[2*i] * tree[2*i +1]) % MOD;
        }
    }
    static long query(int left, int right, int node, int queryLeft, long queryRight) { // query(1,S,1,3,7)
        // 연관이 없음 return 0
        if (right < queryLeft || left > queryRight) {
            return 1;
        }
        // 판단 가능 -> 현재 노드 값 return
        else if(queryLeft <= left && queryRight >= right) {
            return tree[node];
        }
        // 판단 불가, 자식에게 위임, 자식에서 올라온 합을 return
        int mid = (left + right) / 2;
        return ((query(left, mid, 2 * node, queryLeft, queryRight) % MOD)
                * (query(mid + 1, right, 2 * node + 1, queryLeft, queryRight) % MOD)) % MOD;
    }
    static void update(int left, int right, int node, int target, long to) {
        if(target < left || right < target) return;
        if(left == right) {
            tree[node] = to;
            return;
        }
        int mid = (left + right) / 2;
        update(left, mid, 2*node, target, to);
        update(mid + 1, right, 2 * node + 1, target, to);
        tree[node] = (tree[2*node] * tree[2*node+1]) % MOD;
    }
}
