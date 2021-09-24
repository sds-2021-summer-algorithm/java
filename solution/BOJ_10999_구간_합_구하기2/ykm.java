package P10999;

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 수의갯수
    static int M; // 변경횟수
    static int K; // 구간합 계산 횟수
    static int[] tree; // 인덱스 트리
    static int height; // 트리 높이

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P10999/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리의 높이 구하기
        height = 1;
        while(height<N){
            height*=2;
        }tree = new int[height*2];

        // 데이터 입력받기
        for(int i = 0 ; i<N ; i++){
            tree[height + i] = Integer.parseInt(br.readLine());
        }
        // 인덱스 트리 만들기
        makeTree(1);

        for(int i = 0 ; i<M+K; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==1){ // b 부터 C에 d 더하기
                int d = Integer.parseInt(st.nextToken());

                // b부터 c 노드를 찾아야함.
                // 각 노드에 d 더하기

            }else if(a==2){ // b 부터 c까지 구간합 구하기
                int sum = 0;
                // b 부터 c노드를 찾아야함.

                // 더하기
                sb.append(sum+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // dfs방식으로 구간합을 저장한 노드 만들기
    private static int makeTree(int node) {
        
        if(node < tree.length){
            if(tree[node]!=0) return tree[node];
            return tree[node] = makeTree(node*2)+ makeTree(node*2+1);
        }
        return 0;
    } 

    // b,c노드 사이에 해당되는 노드 찾기
    private static void find(int b, int c){

    }
}
