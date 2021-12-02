import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    static final int mod = 1000000007;
    static int N; // 원소수
    static int M; // 변경 횟수
    static int K; // 구간곱 계산 횟수
    static int h; // 트리의 높이
    static long[] tree;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 원소수
        M = Integer.parseInt(st.nextToken()); // 변경 횟수
        K = Integer.parseInt(st.nextToken()); // 구간곱 계산 횟수

        // 트리의 높이를 구해야 함
        h = 1;
        while(h<N) h*=2;
        
        // 트리의 초기 원소 입력받기
        tree = new long[ h*2 + 1]; // 원소는 index=1부터 채움
        for(int i = 1; i<= h*2; i++)
            tree[i] = 1;
        for(int i = 0 ; i<N ; i++)
            tree[h+i] = Integer.parseInt(br.readLine());
        fillTree(1);

        // 쿼리 입력받기
        for(int i = 0 ; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(type==1){
                change(from,to,1,h,1);
            }else if(type==2){
                sb.append(multi(from,to,1,h,1) % mod+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // start부터 end까지 index의 노드값을 곱한 결과가 currentNode에 들어있음
    // from, to는 구간곱을 구하고 싶은 구간
    private static long multi(int from, int to, int start, int end, int currentNode) {
        
        int mid = (start+end)/2;
        if(to<start || end<from){           // 현재 노드의 범위와 관련 없음
            return 1;
        }else if(from<=start && end<=to){   // 현재 노드의 범위를 포함
            return tree[currentNode];
        }else{                              // 현재 노드의 범위에 걸쳐지는 경우 
            return (multi(from, to, start, mid, currentNode*2) * multi(from, to, mid+1, end, currentNode*2+1)) % mod;
        }
    }

    // start부터 end까지 index의 노드값을 곱한 결과가 currentNode에 들어있음
    // 위쪽에서 부터 변경사항을 반영하며 내려가자
    private static long change(int from, int to, int start, int end, int currentNode) {
        if(start == from && from == end){ // 목적지 도착
            return tree[currentNode] = to % mod;
        }
        else if(start<=from && from<= end){
            int mid = (start+end) /2;
            return tree[currentNode] = (change(from, to, start, mid, currentNode*2)* change(from, to, mid+1, end, currentNode*2+1)) %mod;
        }else{
            return tree[currentNode];
        }
    }

    // 부분곱 계산
    private static long fillTree(int node) {
        if(node>=h) return tree[node];
        else return tree[node] = (fillTree(node*2)* fillTree(node*2 + 1))% mod;
    }
}
