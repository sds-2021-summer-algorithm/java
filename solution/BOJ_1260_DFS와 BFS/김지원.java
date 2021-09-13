import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,V;
    static int[][] map;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //1. Data 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }

        visited = new int[N+1];
        dfs(V);
        System.out.println();
        visited = new int[N+1];
        bfs(V);

    }

    private static void bfs(int v) {
        Queue<Integer> que =  new LinkedList<>();
        que.add(v);
        visited[v] = 1; //방문함

        while(!que.isEmpty()){
            int out = que.poll();
            System.out.print(out+" ");
            for(int i=1; i < N+1; i++){
                if(map[out][i] == 1 && visited[i] == 0){
                    que.add(i);
                    visited[i] = 1; //방문함
                }
            }
        }
    }

    private static void dfs(int v) {
        System.out.print(v+" ");
        visited[v] = 1; //방문함
        for(int i = 1; i<N+1; i++){
            if (map[v][i] == 1 && visited[i] == 0){
                dfs(i);
            }
        }
    }
}
