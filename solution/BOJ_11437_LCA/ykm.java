package P11438;
// 최소 공통 조상 기본형

import java.io.*;
import java.util.*;

public class P11437 {
    static int N; // 전체 노드수
    static int M; // 공통조상을 알고싶은 개수
    static ArrayList<Integer>[] tree; // 이진 트리가 아닐수도!
    static int[][] parent;  // 조상과 루트로부터 거리 저장
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        
        // arraylist 초기화
        for(int i = 1; i<=N ; i++){
            tree[i] = new ArrayList<Integer>();
        }

        // 최소 높이 구하기
        int i = 1;
        while(i<N){
            i*=2;
        }
        parent = new int [i+1][2];

        // 입력받기
        for(i = 1; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[b].add(a); // 양방향 간선 추가
            tree[a].add(b); // 이진트리가 아님에 주의! 
        }

        // parent배열 채우기
        parent[1][0]=0; // rootnode
        parent[1][1]=0; 
        fillParent(1, 1);

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

    public static void fillParent(int x, int depth){
        // x의 자식들을 찾아 parent를 x로 표시 해주자 -> dfs
        for(int  i = 0 ; i<tree[x].size(); i++){
            int c = tree[x].get(i);
            if(parent[c][0]==0 && c!=1 ) {
                parent[c][0] = x;
                parent[c][1] = depth;
                fillParent(c, depth+1);
            }
        }
    }

    public static int lca(int a, int b){
        // a와 b의 공통조상을 찾는 과정
        // depth 찾기
        int dA = parent[a][1];
        int dB = parent[b][1];

        // depth 맞추기
        while(true){
            if(dA==dB){
                break;
            }else if(dA>dB){ // -> a를 올리자
                a = parent[a][0];
                dA --;
            }else{//dA<dB -> b를 올리자
                b = parent[b][0];
                dB--;
            }
        }
        // 한칸씩 올라가며 공통조상 찾기
        while(true){
            if(a==b){
                return a;
            }else{
                a = parent[a][0];
                b = parent[b][0];
            }
        }
    }
}
