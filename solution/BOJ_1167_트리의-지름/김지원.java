import java.util.*;
import java.io.*;



class Main {

    static class node {
        int n;
        int w;

        node(int n, int w){
            this.n = n;
            this.w = w;
        }
    }

    static int N;
    static ArrayList<node>[] al;
    static boolean[] visited;
    static int num;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        al = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            al[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            String[] inputs = br.readLine().split(" ");
            int index = Integer.parseInt(inputs[0]);
            int j=1;
            while(true){
                int point = Integer.parseInt(inputs[j]);
                if(point == -1) break;
                int dis = Integer.parseInt(inputs[j+1]);

                al[point].add(new node(index,dis));
                al[index].add(new node(point,dis));

                j += 2;
            }
        }

        dfs(1,0);

        visited = new boolean[N+1];
        dfs(num,0);

        System.out.println(max);
    }

    public static void dfs(int x, int len) {
        if(len > max){
            max = len;
            num = x;
        }
        visited[x] = true;

        for(int i=0; i< al[x].size(); i++){
            node n = al[x].get(i);
            if(!visited[n.n]){
                dfs(n.n, n.w+len);
                visited[n.n] = true;
            }
        }
    }

}
