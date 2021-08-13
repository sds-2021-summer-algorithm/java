import java.io.*;
import java.util.*;

public class Main {
    static int T, F;
    static int[] parent;
    static int[] level;
    static HashMap<String, Integer> idMap;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 1. 입력 받기
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());

            // parent 초기화
            parent = new int[F * 2 + 1];
            level = new int[F * 2 + 1];
            for (int i = 1; i < parent.length; i++) {
                parent[i] = i;
                level[i] = 1;
            }

            // 친구 연결
            idMap = new HashMap<>();
            int idx = 1;
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                idMap.put(a, idMap.getOrDefault(a, idx++));
                idMap.put(b, idMap.getOrDefault(b, idx++));

                sb.append(union(idMap.get(a), idMap.get(b)) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pa] = parent[pb]; // b 밑에 a가 들어간다
            level[pb] += level[pa]; // b가 추가됐으므로 level[pb] 갱신
        }
        return level[pb];
    }

    static int find(int id) {
        count++;
        if (parent[id] == id)
            return id;
        return parent[id] = find(parent[id]);
    }
}