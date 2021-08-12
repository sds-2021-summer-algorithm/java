import java.io.*;
import java.util.*;

public class Main {
    static int V, E, order;
    static ArrayList<Integer> adjList[];
    static int[] visit;
    static boolean[] isDjj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            adjList[i] = new ArrayList<>();

        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 2. DFS
        visit = new int[V + 1];
        isDjj = new boolean[V + 1];

        order = 1;
        for (int i = 1; i <= V; i++) {
            if (visit[i] == 0) {
                dfs(i, true, 0);
            }
        }

        // 3. 단절점 총 개수와 단절점 출력
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (isDjj[i]) {
                count++;
                sb.append(i + " ");
            }
        }

        bw.write(count + "\n" + sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int id, boolean isRoot, int parent) {
        visit[id] = order;
        order++;

        int ret = visit[id];
        int child = 0;

        int size = adjList[id].size();
        for (int i = 0; i < size; i++) {
            int now = adjList[id].get(i);

            // 다시 방문하는 것 방지
            if (now == parent)
                continue;

            // 자식 첫 방문
            if (visit[now] == 0) {
                child++;

                int low = dfs(now, false, id);

                if (!isRoot && low >= visit[id]) {
                    isDjj[id] = true;
                }
                ret = Math.min(ret, low);
            }

            // 자식 첫 방문이 아니라면
            else {
                ret = Math.min(ret, visit[now]);
            }
        }

        if (isRoot && child >= 2) {
            isDjj[id] = true;
        }
        return ret;
    }
}