import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{

    public static class graph{
        private int V;
        private ArrayList<int[]>[] graph;
        private boolean[] isVisited;
        private int[] indegrees;
        private int diameter = 0;
        private int node = 0;

        public graph(int V){
            this.V = V;
            graph = new ArrayList[V+1];
            indegrees = new int [V+1];
            for(int i = 1; i<=V; i++){
                graph[i] = new ArrayList<int[]>();
            }
        }

        public void addEdge(int from, int to, int dist){
            indegrees[from]++;
            graph[from].add(new int[] {to, dist});
        }

        public int getDiameter(){
            return diameter;
        }

        public void calculateDiameter() {
        
            isVisited = new boolean[V+1];
            DFS(1,0);
            isVisited = new boolean[V+1];
            DFS(node,0);
        }
  
        public void DFS(int currentNode, int currentSum){
            if(currentSum> diameter){
                diameter = currentSum;
                node = currentNode;
            }
            isVisited[currentNode] = true;

            // 현재 노드에서 방문할 다음 노드 찾기
            int size = graph[currentNode].size();
            for(int i = 0; i<size; i++){
                int nextNode = graph[currentNode].get(i)[0];
                int nextEdge = graph[currentNode].get(i)[1];

                if(isVisited[nextNode]) continue;
                DFS(nextNode, currentSum + nextEdge);
            }
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
