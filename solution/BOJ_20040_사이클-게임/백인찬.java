import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st =  new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(find(a) == find(b)) {
                bw.write(m + "\n");
                bw.flush();
                bw.close();
                return;
            }
            union(a, b);
        }
        bw.write("0\n");
        bw.flush();
        bw.close();
    }
    static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int pa = parent[a];
        int pb = parent[b];

        parent[pb] = pa;
    }
}
