import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        visit = new boolean[N+1];
        for(int i=1; i<N+1; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int i=0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());
            graph[val1].add(val2);
            graph[val2].add(val1);
        }

        for(int i =1; i<= N; i++){
            if(!visit[i]){
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int v) {
        if(visit[v]){
            return;
        }
        else{
            visit[v] = true;
            for(int i : graph[v]){
                if(!visit[i]){
                    dfs(i);
                }
            }
        }
    }
}
