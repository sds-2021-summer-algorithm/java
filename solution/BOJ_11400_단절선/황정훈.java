import java.io.*;
import java.util.*;

public class Main {

    static int V, E;
    static class Node{
        int order;
        ArrayList<Integer> link;
        public Node(){
            this.link = new ArrayList<>();
        }
    }static Node[] nodes;
    static ArrayList<int[]> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        nodes = new Node[V+1];
        for (int i = 1; i <= V; i++)
            nodes[i] = new Node();

        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodes[start].link.add(end);
            nodes[end].link.add(start);
        }

        dfs(1, 1);

        answer.sort( (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0] );
        bw.write(answer.size()+"\n");
        for (int[] edge : answer)
            bw.write(edge[0]+" "+edge[1]+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static int cnt=1;
    static int dfs(int id, int parent){
        if(nodes[id].order==0)
            nodes[id].order = cnt++;

        int ret = nodes[id].order;
        for(int next : nodes[id].link){
            if (next==parent)
                continue;

            if (nodes[next].order==0){
                int low = dfs(next, id);
                if(low>nodes[id].order)
                    answer.add( (id<next) ? new int[]{id, next} : new int[]{next, id});
                ret = Math.min(ret, low);
            }
            else
                ret = Math.min(ret, nodes[next].order);
        }
        return ret;
    }
}
