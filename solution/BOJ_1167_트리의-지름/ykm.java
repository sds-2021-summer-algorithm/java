import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static class graph{
        private int V;
        private int[][] graph;
        private boolean[] isVisited;
        private int[] indegrees;
        private int diameter = 0;

        public graph(int V){
            this.V = V;
            graph = new int[V+1][V+1];
            indegrees = new int [V+1];
        }

        public void addEdge(int from, int to, int dist){
            indegrees[from]++;
            graph[from][to] = dist;
        }

        public int getDiameter(){
            return diameter;
        }

        // 시작 노드가 i인 지름 구하기
        /*
        1. cycle 이 있는 경우?
         */
        public int calculateDiameter() {
            for(int i = 1; i<=V; i++){
                isVisited = new boolean[V+1];
                diameter = Math.max(diameter,DFS(i,0));
            }
            return 0;
        }

        public int DFS(int node, int currentSum){

            int max = currentSum;
            isVisited[node] = true;
            // 현재 노드에서 방문할 다음 노드 찾기
            for(int i = V; i>0; i--){
                if(isVisited[i] || graph[node][i] ==0 ) continue;
                
                int nextNode = i;
                int nextEdge = graph[node][nextNode];

                max = Math.max(max,DFS(nextNode, currentSum + nextEdge));
            }
            isVisited[node] = false;

            return max;
        }

    }
    
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        graph graph = new graph(V);

        for(int i = 1 ; i<=V; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while(true){
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                graph.addEdge(from, to, dist);
            }
        }
        graph.calculateDiameter();
        System.out.println(graph.getDiameter());
    }

}
