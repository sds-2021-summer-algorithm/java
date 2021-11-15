import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] order;
    static class Node{
        int id, oriOrder, inDegree;
        Set<Integer> outDirect;
        public Node(int id, int ord){
            this.id = id;
            this.oriOrder = ord;
            this.inDegree = ord;
            this.outDirect = new HashSet<>();
        }
    }
    static HashMap<Integer, Node> nodes;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            order = new int[N];
            nodes = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int id = Integer.parseInt(st.nextToken());
                order[j] = id;
                nodes.put(id, new Node(id, j));
                for (int k = 0; k < j; k++) {
                    nodes.get(order[k]).outDirect.add(id);
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                swap(nodes.get(a), nodes.get(b));
            }
            sb.append(sort()+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void swap(Node A, Node B){
        if (!A.outDirect.contains(B.id)){
            Node tmp = A;
            A = B;
            B = tmp;
        }
        A.outDirect.remove(B.id);
        A.inDegree++;
        B.outDirect.add(A.id);
        B.inDegree--;
    }
    static String sort(){
        Queue<Node> Q = new ArrayDeque<>();
        for (Map.Entry<Integer, Node> E : nodes.entrySet()) {
            if (E.getValue().inDegree==0){
                if (Q.size()==1) return "?";
                Q.add(E.getValue());
            }
        }

        StringBuilder order = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (Q.isEmpty()) return "IMPOSSIBLE";
            Node cur = Q.poll();
            order.append(cur.id+" ");

            for (int next : cur.outDirect){
                nodes.get(next).inDegree--;
                if (nodes.get(next).inDegree == 0) Q.add(nodes.get(next));
            }
        }
        return order.toString();
    }
}
