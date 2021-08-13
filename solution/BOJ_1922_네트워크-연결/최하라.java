import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int start, target, cost;

        public Edge(int start, int target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력받기
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 2 parent 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 3. build graph
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        // 4. Use MST to find the minimum cost
        int total = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (find(cur.start) != find(cur.target)) {
                union(cur.start, cur.target);
                total += cur.cost;
            }
        }

        // 5. 결과 출력
        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static int find(int id) {
        if (parent[id] == id)
            return id;
        return parent[id] = find(parent[id]);
    }
}
