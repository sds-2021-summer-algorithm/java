import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] indegree;
    static int[] parent;
    static ArrayList<Integer> edgeList[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //2. parent 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        //3. build graph
        edgeList = new ArrayList[N + 1];
        indegree = new int[N + 1];
        for (int i = 1; i <= N; i++) edgeList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edgeList[a].add(b);

            indegree[b]++;
        }

        //4. topological sort
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) if (indegree[i] == 0) queue.add(i);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur + " ");
            int size = edgeList[cur].size();
            for (int i = 0; i < size; i++) {
                int next = edgeList[cur].get(i);
                indegree[next]--;
                if (indegree[next] == 0) queue.add(next);
            }
        }

        //5. 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}