import java.util.*;

class Solution {
    static ArrayList<Integer> edgeList[];
    static int[] dist;
    static boolean[] visit;

    public int solution(int n, int[][] edge) {

        // 1. build graph
        edgeList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            edgeList[i] = new ArrayList<>();

        for (int i = 0; i < edge.length; i++) {
            edgeList[edge[i][0]].add(edge[i][1]);
            edgeList[edge[i][1]].add(edge[i][0]);
        }

        // 2.bfs를 활용해 노드 길이 찾기
        dist = new int[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++)
            dist[i] = Integer.MAX_VALUE;

        bfs();

        int max = 0;
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                answer = 1;
            } else if (dist[i] == max)
                answer++;
        }

        return answer;
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        Deque<Integer> deque = new ArrayDeque<>();
        queue.add(1);
        deque.add(1);
        visit[1] = true;
        int count = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                int current = deque.pollFirst();
                int leng = edgeList[current].size();
                for (int j = 0; j < leng; j++) {
                    int next = edgeList[current].get(j);
                    if (!visit[next]) {
                        visit[next] = true;
                        dist[next] = count;
                        deque.addLast(next);
                    }
                }
            }
            count++;
        }
    }
}