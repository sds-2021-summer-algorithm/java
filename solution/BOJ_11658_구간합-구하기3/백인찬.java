import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S;
    static Seg[] tree2D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree2D = new Seg[S * 2];
        // init Tree
        for (int i = S; i < tree2D.length; i++) {
            tree2D[i] = new Seg(S * 2);
            st = new StringTokenizer(br.readLine());
            for (int j = S; j < tree2D.length; j++) {
                tree2D[i].tree[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = S - 1; j >= 1; j--) {
                tree2D[i].tree[j] = tree2D[i].tree[2 * j] + tree2D[i].tree[2 * j + 1];
            }
        }
        for (int i = S - 1; i >= 1; i--) {
            tree2D[i] = new Seg(S * 2);
            for (int j = S; j < tree2D.length; j++) {
                tree2D[i].tree[j] = tree2D[2 * i].tree[j] + tree2D[2 * i + 1].tree[j];
            }
            for (int j = S - 1; j >= 1; j--) {
                tree2D[i].tree[j] = tree2D[i].tree[2 * j] + tree2D[i].tree[2 * j + 1];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            if(w == 1) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                if(y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }
                if(x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }
                sb.append(yQuery(x1, x2, 1, 1, S, y1, y2)).append("\n");
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(x, y, c);
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void update(int x, int y, int c) {
        int i = x + S - 1, j = y + S - 1;
        tree2D[i].tree[j] = c;
        while(j > 1) {
            j /= 2;
            tree2D[i].tree[j] = tree2D[i].tree[2 * j] + tree2D[i].tree[2 * j + 1];
        }
        while(i > 1) {
            j = y + S - 1;
            i /= 2;
            tree2D[i].tree[j] = tree2D[2 * i].tree[j] + tree2D[2 * i + 1].tree[j];
            while(j > 1) {
                j /= 2;
                tree2D[i].tree[j] = tree2D[i].tree[2 * j] + tree2D[i].tree[2 * j + 1];
            }
        }
    }
    static int xQuery(int y, int queryLeft, int queryRight, int node, int left, int right){
        if(left > queryRight || right < queryLeft) return 0;
        else if(queryLeft <= left && right <= queryRight) return tree2D[y].tree[node];
        int mid = (left + right) / 2;
        return xQuery(y, queryLeft, queryRight, 2 * node, left, mid) + xQuery(y, queryLeft, queryRight, 2 * node + 1, mid + 1, right);
    }

    static int yQuery(int queryLeft, int queryRight, int node, int left, int right, int x1, int x2) {
        if(left > queryRight || right < queryLeft) return 0;
        else if(queryLeft<= left && right <= queryRight) return xQuery(node, x1, x2, 1, 1, S);
        int mid = (left + right) / 2;
        return yQuery(queryLeft, queryRight, 2 * node, left, mid, x1, x2) + yQuery(queryLeft, queryRight, 2 * node + 1, mid + 1, right, x1, x2);
    }
}

class Seg {
    int[] tree;

    public Seg(int n) {
        this.tree = new int[n];
    }
}
