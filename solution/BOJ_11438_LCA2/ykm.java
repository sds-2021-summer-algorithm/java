package P11438;
// 최소 공통 조상

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 전체 노드수
    static int M; // 공통조상을 알고싶은 개수
    static ArrayList<Integer>[] tree; // 이진 트리가 아닐수도!
    static int[][] parent;  // 조상노드들 저장 (2의 제곱형태로 조상 저장)
    static int[] depth; 
    static int count = 1;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        depth = new int[N+1];
        
        // arraylist 초기화
        for(int i = 1; i<=N ; i++){
            tree[i] = new ArrayList<Integer>();
        }

        // 저장할 부모의 개수 구하기
        int i = 1;
        while(i<N){
            i*=2;
            count++;
        }
        parent = new int [count][N+1]; //정점 v의 k번째 조상

        // 입력받기
        for(i = 1; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[b].add(a); // 양방향 간선 추가
            tree[a].add(b); // 이진트리가 아님에 주의! 
        }

        // depth찾기
        depth[1] = 0;       // rootnode
        findDepth(1, 0);

        // parent배열 채우기
        fillParent();

        // lca구하기
        M = Integer.parseInt(br.readLine());
        for(i = 0 ; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a,b)+"\n");
        }    
        bw.flush();
        bw.close();
        br.close();
    }

    // x의 자식에는 d+1 , 부모 넣기
    public static void findDepth(int x, int d){
        for(int i = 0 ; i<tree[x].size(); i++){
            int c = tree[x].get(i);

            if(depth[c]==0 && c!=1){
                depth[c] = d+1;
                parent[0][c] = x;
                findDepth(c, d+1);
            }
        }
    }

    public static void fillParent(){
        for(int i = 1; i<count; i++){
            for(int j = 1; j<=N; j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }    
        }
    }

	static int lca(int a, int b) {
		// 1. depth[a] >= depth[b] 이도록 조정하기
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		// 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
		for (int i = count-1; i>=0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parent[i][a];
			}
		}
		
		// 3. depth를 맞췄는데 같다면 종료
		if (a == b) return a;
		
		// 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
		for (int i = count-1; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}
}
