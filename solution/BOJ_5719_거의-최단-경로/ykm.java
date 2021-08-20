import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 20000000;
    static int V; // 500
    static int E; // 10000
    static ArrayList<Edge>[] edges; // 인접 리스트

    static PriorityQueue<Edge> pq = new PriorityQueue<>(); // 다익스트라를 위한 우선순위 큐
    static int[] min; // 각 node까지 최단거리 저장용

    static boolean[][] isShortest;
    static ArrayList<Integer>[] prev; // 직전 방문노드 저장
    
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            if(V==0 && E==0) break;

            edges = new ArrayList[V];
            isShortest = new boolean[V][V];
            min = new int[V]; 
            prev = new ArrayList[V];

            for(int i = 0 ; i<V ; i++){
                edges[i] = new ArrayList<Edge>();
                prev[i] = new ArrayList<Integer>();
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 간선 입력받기
            for(int i = 0 ; i<E ; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges[from].add(new Edge(to,cost));
            }

            // 첫번째 다익스트라
            Dij(start, end);
            // prev를 역으로 따라가면서 사용된 간선 제거
            deleteEdge(end, start);
            // 두번째 다익스트라
            Dij(start, end);

            if(min[end]==INF) sb.append(-1+"\n");
            else sb.append(min[end]+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    } 

    public static void Dij(int start, int end){
        for(int i = 0 ; i<V ; i++){
            min[i] = INF;
        }
        min[start] = 0;
        pq.add(new Edge(start,0));

        // 1. 큐에서 꺼내기
        // 2. 목적지 확인
        // 3. 갈수있는곳
        // 4. 조건확인
        // 5. 체크인
        // 6. 큐에 넣기
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            
            //업데이트가 불가능한 경우 제외
            if(min[current.to] < current.cost) continue;
            if(current.to==end) continue;

            for(int i = 0 ; i < edges[current.to].size() ; i++ ){
                Edge next = edges[current.to].get(i);

                if(isShortest[current.to][next.to]) continue;

                int sum = current.cost + next.cost;
                if(min[next.to]> sum){
                    min[next.to] = sum; // 더짧은 경로 발견시 업데이트
                    prev[next.to].clear();
                    prev[next.to].add(current.to);
                    pq.offer( new Edge(next.to, min[next.to]));
                }else if(min[next.to] == sum){
                    prev[next.to].add(current.to);
                }
            }
        }
    }
    
    // end부터 사용된 간선표시
    public static void deleteEdge(int parent, int start){
        if(parent== start) return;
        for(int i = 0 ; i < prev[parent].size(); i++){
            int a = (int) prev[parent].get(i);
            
            if(!isShortest[a][parent]){ // 포인트 -> 이미 true이면 호출하지 말자
                isShortest[a][parent] = true;
                deleteEdge(a, start);
            }
        }
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
}
