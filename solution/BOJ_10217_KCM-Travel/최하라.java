import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int target, cost, dist;

        public Edge(int target, int cost, int dist) {
            this.target = target;
            this.cost = cost;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static int N, M, K;
    static ArrayList<Edge> list[];
    static StringBuilder result;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        result = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // 1. 입력 받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 2. build graph
            list = new ArrayList[N + 1];
            dist = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                list[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            // 3. 다익스트라 - 주어진 비용안에서 최단거리 찾기
            dijkstra();

            // 4. 최소 가격 찾기
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= M; i++)
                min = Math.min(min, dist[N][i]);

            // 5. 결과 저장
            result.append(min == Integer.MAX_VALUE ? "Poor KCM" : min).append("\n");
        }

        // 6. 결과 출력
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.dist > dist[current.target][current.cost])
                continue;

            for (Edge next : list[current.target]) {
                int totalM = current.cost + next.cost;
                int totalD = current.dist + next.dist;
                if (totalM > M)
                    continue;
                if (totalD < dist[next.target][totalM]) {
                    for (int i = totalM; i <= M; i++) {
                        if (dist[next.target][i] > totalD)
                            dist[next.target][i] = totalD;
                        else
                            break;
                    }
                    dist[next.target][totalM] = totalD;
                    pq.add(new Edge(next.target, totalM, totalD));
                }
            }
        }
    }
}