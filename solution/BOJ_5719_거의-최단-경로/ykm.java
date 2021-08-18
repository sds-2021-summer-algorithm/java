package P5719;
import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 20000000;
    static int V; // 500
    static int E; // 10000
    static int [][] edges; // 인접행렬
    static int start;
    static int end;

    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); // 다익스트라를 위한 우선순위 큐
    static int[] min; // 각 node까지 최단거리 저장용
    static ArrayList[] prev; // 직전 방문노드 저장
    static boolean[] check; // 방문여부 확인
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            if(V==0 && E==0) break;

            edges = new int[V][V];
            min = new int[V]; 
            prev = new ArrayList[V];
            check = new boolean[V];
            for(int i = 0; i<V ; i++){
                min[i]= INF;
                check[i] = false;
                //prev[i] = new ArrayList<Integer>(); // 직전에 방문한 노드 저장용
            }

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            // 간선 입력받기
            for(int i = 0 ; i<E ; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges[from][to] = cost;
            }

            // 첫번째 다익스트라 준비
            min[start] = 0;
            pq.add(new Edge(start,0));

            while(!pq.isEmpty()){
                // 1. 체크인
                // 2. 목적지 인가
                // 3. 연결된 노드
                // 4. 조건 확인
                // 5. 방문
                // 6. 체크아웃

                Edge current = pq.poll();

                // 절대 업데이트가 안되는 경우 가지치기
                if(current.to == end) continue;
                if(current.cost > min[current.to]) continue;

                for(int j = 0 ; j<V; j++){
                    if (edges[current.to][j]==0) continue;
                    
                    Edge next = new Edge(j, edges[current.to][j]);

                    //최단경로로 업데이트 가능한지 확인
                    int sum = min[current.to] + next.cost;
                    if( min[next.to] > sum){
                        min[next.to] = sum;
                        prev[next.to] = new ArrayList<Integer>(); // 이전에 저장된 경로 제거 필요
                        prev[next.to].add(current.to);
                        pq.add(next);
                    }else if(min[next.to] == sum){
                        // 최단 경로가 여러개인 경우도 고려해야함.
                        // 업데이트는 필요없지만 경로저장은 필요
                        prev[next.to].add(current.to);
                    }
                }
            }

            // prev를 역으로 따라가면서 사용된 간선 제거
            deleteEdge(end);

            // 두번째 다익스트라 준비
            for(int j = 0 ; j<V; j++){
                check[j]=false;
                min[j] = INF; // 최단거리 저장용
            }

            min[start] = 0;
            pq.add(new Edge(start,0));

            while(!pq.isEmpty()){
                // 위와 같은 방식으로 다익스트라 한번더
                Edge current = pq.poll();

                if(current.cost > min[current.to]) continue;
                check[current.to] = true;

                for(int j = 0 ; j<V; j++){
                    if(edges[current.to][j]==0) continue;

                    Edge next = new Edge(j,edges[current.to][j]);

                    if(check[next.to]) continue;

                    //최단경로로 업데이트 가능한지 확인
                    int sum = min[current.to] + next.cost;
                    if( min[next.to] > sum){
                        min[next.to] = sum;
                        pq.add(next);
                    }
                }
            }

            if(min[end]==INF) bw.write(-1+"\n");
            else bw.write(min[end]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    } 
    
    public static class Edge implements Comparable<Edge>{
        int to;
        int cost; // 1000
        
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "cost [cost=" + cost + ", to=" + to + "]";
        }        
    }

    // end부터 사용된 간선 제거
    public static void deleteEdge(int b){
        if(b== start) return;
        for(int i = 0 ; i < prev[b].size(); i++){
            int a = (int) prev[b].get(i);
            
            edges[a][b] = 0;
            deleteEdge(a);
        }
    }
}
