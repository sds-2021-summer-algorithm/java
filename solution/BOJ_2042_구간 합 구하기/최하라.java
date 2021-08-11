import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, S;
    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 2. build tree
        S = 1;
        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];
        nums = new long[N];
        for (int i = 0; i < N; i++)
            nums[i] = Long.parseLong(br.readLine());
        initBU();

        // 3. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            switch (cmd) {
                case 1:
                    update(1, S, 1, b, c - tree[S + b - 1]);
                    break;
                case 2:
                    sb.append(query(1, S, 1, b, c)).append("\n");
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void initBU() {
        // 1. leaf 노드 set
        // S is the starting point of the leaf
        int n = 0;
        for (int i = S; i < tree.length; i++) {
            if (n < N) {
                tree[i] = nums[n++];
            }
        }

        // 2. 내부 노드 set
        for (int i = S - 1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 연관 없음 do nothing

        // 연관 있음 --> 현재 노드 update --> 자식 노드 update
        if (target >= left && target <= right) {
            tree[node] += diff;

            if (2 * node < tree.length) {
                int mid = (left + right) / 2;
                update(left, mid, 2 * node, target, diff);
                update(mid + 1, right, 2 * node + 1, target, diff);
            }
        }
    }

    static long query(int left, int right, int node, int queryLeft, long queryRight) {
        // out of boundary
        if (left > queryRight || right < queryLeft)
            return 0;
        // includes full boundary
        if (queryLeft <= left && right <= queryRight)
            return tree[node];
        // somewhat in the boundary
        int mid = (left + right) / 2;
        return query(left, mid, 2 * node, queryLeft, queryRight)
                + query(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
    }
}
