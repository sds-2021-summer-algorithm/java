import java.util.*;
import java.io.*;

class Main {
    static int N, K;
    static int minTime = Integer.MAX_VALUE;
    static boolean[] visit = new boolean[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println((N-K));
            return;
        }
        else{
            bfs();
            System.out.println(minTime);
        }
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {N, 0});

        while(!q.isEmpty()) {
            int[] node = q.poll();
            visit[node[0]] = true;
            if(node[0] == K) minTime = Math.min(minTime, node[1]);

            if(node[0] * 2 <= 100000 && visit[node[0] * 2] == false) q.offer(new int[] {node[0] * 2, node[1]});
            if(node[0] + 1 <= 100000 && visit[node[0] + 1] == false) q.offer(new int[] {node[0] + 1, node[1] + 1});
            if(node[0] - 1 >= 0 && visit[node[0] - 1] == false) q.offer(new int[] {node[0] - 1, node[1] + 1});
        }
    }

}
