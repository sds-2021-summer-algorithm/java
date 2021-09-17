import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K;
    static int[] depth;
    static int[][] arr;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        depth = new int[N+1];
        tree = new ArrayList[N+1];
        while(Math.pow(2, K)<=N)
            K++;
        arr = new int[N+1][K]; // arr[i][j] : i번째 노드의 2^j번째 부모

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (tree[a]==null)
                tree[a] = new ArrayList<>();
            if (tree[b]==null)
                tree[b] = new ArrayList<>();
            tree[a].add(b);
            tree[b].add(a);
        }

        init_dep(1, 1); // init_dep(a, b) -> a번째 노드의 depth를 b로 만듬
        for (int k = 1; k < K; k++) {
            for (int v = 1; v <= N; v++) {
                arr[v][k] = arr[ arr[v][k-1] ][ k-1 ]; // "v"의 "2^k번째 부모"는 "v의 2^(k-1)번째 부모"의 "2^(k-1)번째 부모"
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)+"\n"); // lca(a, b) -> a와 b의 최소 공통 조상 찾기
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
    static int lca(int a, int b){
        if(depth[a]<depth[b]){ // 무조건 a가 더 트리의 아랫쪽에 위치하도록 바꿔주기
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = K-1; depth[a]>depth[b]; i--) { // 제일 아랫쪽에서부터 올라오면서
            if(Math.pow(2, i) <= depth[a]-depth[b]) // 높이 차이가 2의 거듭제곱보다 작으면
                a = arr[a][i]; // 높이 맞춰주기
        }

        if (a==b) // 높이가 같은데 값도 같으면
            return a; // 바로 리턴

        for (int i = K-1; i >= 0; i--) { // 같은 높이에서 2^i만큼 높이를 올라가면서
            if(arr[a][i]!=arr[b][i]){ // 서로 다르면
                a = arr[a][i]; // 갱신
                b = arr[b][i];
            }
        }
        return arr[a][0]; // 공통조상 바로 밑이므로, 첫번째 조상 리턴
    }
    static void init_dep(int cur, int dep){
        depth[cur] = dep;
        for(int next : tree[cur]){
            if(depth[next]==0){
                arr[next][0] = cur;
                init_dep(next, dep+1);
            }
        }
    }
}
