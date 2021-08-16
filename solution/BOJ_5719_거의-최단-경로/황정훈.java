import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Integer>[] edges;
    static ArrayList<Integer>[] parent;
    static int N, M, S, D;
    static int[] dist;
    static boolean[][] visit;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while(N!=0 && M!=0){

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            edges = new HashMap[N];
            for (int i = 0; i < N; i++)
                edges[i] = new HashMap<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                edges[U].put(V, P);
            }

            dijkstra(); //최단경로 찾기
            visit = new boolean[N+1][N+1];
            remove(D); // 최단경로 지우기
            dijkstra(); //최단거리 찾기

            bw.write(dist[D]!=MAX ? dist[D]+"\n" : "-1\n");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static void dijkstra(){
        dist = new int[N];
        parent = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            dist[i] = MAX;
            parent[i] = new ArrayList<>();
        }
        dist[S] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
        pq.add(new int[]{S, 0});
        while(!pq.isEmpty()){
            int[] tmp = pq.poll();
            int now = tmp[0];
            if (tmp[1] > dist[now])
                continue;

            for( Map.Entry<Integer, Integer> edge : edges[now].entrySet()){
                int next = edge.getKey();
                int cost = edge.getValue();

                if (dist[now] + cost < dist[next]){
                    dist[next] = dist[now] + cost;
                    pq.add(new int[]{next, dist[next]});
                    parent[next].clear();
                    parent[next].add(now);
                } else if (dist[now] + cost == dist[next])
                    parent[next].add(now);
            }
        }
    }
    static void remove(int child){
        if (child!=S)
            for(int p : parent[child]){
                if (!visit[child][p]) {
                    visit[child][p] = true;
                    edges[p].remove(child);
                    remove(p);
                }
            }
    }
}
