import java.io.*;
import java.util.*;

// 11400 단절선
public class Main {
    static int V, E; // V : 정점 수, E : 간선 수
    static int A, B; // 입력 변수
    static int order; // 단절선 DFS용 순서 기록 변수저

    static ArrayList[] adjList; // 인접리스트
    static int[] visit; // 단절선 DFS용 방문 순서 배열

    static PriorityQueue<edge> ansList; // 정답 간선 리스트

    static class edge implements Comparable<edge> {
        int start, target;

        public edge(int start, int target) {
            super();
            this.start = start;
            this.target = target;
        }

        @Override // 사전순 출력
        public int compareTo(Main.edge o) {
            if (this.start > o.start) {
                return 1;
            }
            if (this.start == o.start) {
                return this.target - o.target;
            }
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 & 변수 준비
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            adjList[A].add(B);
            adjList[B].add(A);
        }

        visit = new int[V + 1];
        order = 1;

        // 2. 모든 정점 돌면서 단절선 체크하고 정답배열에 추가
        ansList = new PriorityQueue<edge>();
        for (int i = 1; i <= V; i++) {
            if (visit[i] == 0) {
                dfs(i, 0);
            }
        }

        // 3. 출력
        StringBuilder sb = new StringBuilder();
        int len = ansList.size();
        sb.append(len + "\n");

        while (!ansList.isEmpty()) {
            edge now = ansList.poll();
            sb.append(now.start + " " + now.target + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int id, int parent) {
        // 1. 방문순서 기록 후 자식(child)과 비교 준비
        visit[id] = order;
        order++;
        int ret = visit[id];

        // 2. 자식 DFS
        int len = adjList[id].size();
        for (int i = 0; i < len; i++) {
            int now = (int) adjList[id].get(i);

            // 왔던 길 제외하기 위한 로직
            if (now == parent)
                continue;

            // 2-1. 자식 최초 방문했을 경우
            if (visit[now] == 0) {

                // 자식 정점 중 방문순서가 빠른 값
                int low = dfs(now, id);

                // ★★★★★ 역전이 불가능한 경우 단절선.
                if (low > visit[id]) {
                    // edge 출발점이 더 작은 수로 바꿔서 정답배열에 추가 (문제 출력 요구사항)
                    if (now > id) {
                        ansList.add(new edge(id, now));
                    } else {
                        ansList.add(new edge(now, id));
                    }
                }
                ret = Math.min(ret, low);
            }
            // 2-2. 자식을 이미 방문한 경우
            else {
                ret = Math.min(ret, visit[now]);
            }
        }
        return ret;
    }
}