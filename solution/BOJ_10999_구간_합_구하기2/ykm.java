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
        System.setIn(new FileInputStream("input.txt"));
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
            
            if(a == 1){ // b 부터 C에 d 더하기
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                add(1,1,height,b,c,d);

            }else if(a == 2){ // b 부터 c까지 구간합 구하기
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                sb.append(sum(1,1,height,b,c)+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int sum(int node, int start, int end, int b, int c) {
        if(node >= tree.length) return 0;

        long sum = 0;
        
        if(b<=start && end<=c) return tree[node]; 

        if(end - start == 1){
            if(b <= start && start <= c) sum += tree[node*2];
            if(b <= end && end <= c) sum += tree[node*2+1];
        }else{
            int mid = (start + end)/2;
            sum += sum(node*2, start, mid, b, c);     //left
            sum += sum(node*2+1, mid+1, end, b, c);   //right
        }
        return sum;
    }

    // dfs방식으로 구간합을 저장한 노드 만들기
    public static int makeTree(int node) {
        
        if(node < tree.length){
            if(tree[node]!=0) return tree[node];
            return tree[node] = makeTree(node*2)+ makeTree(node*2+1);
        }
        return 0;
    } 

    // 현재 노드가 가지고 있는 값의 범위, 찾는 구간
    public static void add(int node, int start, int end, int b, int c, int d){
        if(node > tree.length) return;
        if(c<start || end<b) return;    // 현재 노드의 범위 밖
        else if(start<=b && c<=end){    // 현재 노드의 범위 속
            tree[node] += (c-b+1)*d;        
        }else{                          // 현재 노드의 범위에 걸칠때
            if(start<=b && b<=end){     // 뒤쪽에 걸침
                c = end;
            }else if(start<= c && c<=end){// 앞쪽에 걸침
                b=start;
            }
            tree[node] += (c-b+1)*d;  
        }

        // 자식에게 물어보기
        if(start == end) return;
        else if(end-start==1){
            if(b <= start && start <= c) tree[node*2] += d;
            if(b <= end && end <= c) tree[node*2+1] += d;
        }else{
            int mid = (start + end)/2;
            add(node*2, start, mid, b, c, d);     //left
            add(node*2+1, mid+1, end, b, c, d);   //right
        }


    }
}
