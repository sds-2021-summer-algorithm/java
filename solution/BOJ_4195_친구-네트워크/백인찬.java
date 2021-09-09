import java.io.*;
import java.util.*;

public class Main {
    static int T, F;
    static int[] parent;
    static int[] child;
    static Map<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            parent = new int[2 * F + 1];
            child = new int[2 * F + 1];
            map = new HashMap<>();
            for (int i = 1; i <= 2 * F; i++) {
                parent[i] = i;
                child[i] = 1;
            }
            int id = 1;
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                int aId;
                int bId;
                if(map.containsKey(a)) {
                    aId = map.get(a);
                } else {
                    aId = id++;
                    map.put(a, aId);
                }
                if(map.containsKey(b)) {
                    bId = map.get(b);
                } else {
                    bId = id++;
                    map.put(b, bId);
                }
                sb.append(union(aId, bId)).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa != pb) {
            parent[pa] = parent[pb];
            child[pb] += child[pa];
        }
        return child[pb];
    }
    static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}
