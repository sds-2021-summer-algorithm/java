import java.util.*;
import java.io.*;

// https://www.acmicpc.net/source/20874585참고

public class Main {
    static final int MOD = 1000000007;
    public static class segTree{
        int H;
        int pointer;
        long tree[];

        public segTree(int N){ //수의 개수
            H = 1;
            while(H<N){
                H*=2;
            }
            tree = new long[H*2+1];  
            pointer = H;  
        }

        // 트리에 값 집어 넣기
        void append(int num){
            tree[pointer++] = num % MOD;
        }

        // 구간합 구하며 모든 노드 채우기
        long fill(int node){
            if(node>=H) return tree[node];
            return tree[node] = (fill(node*2) + fill(node*2+1))%MOD;
        }

        // from 부터 to 에 diff더하기
        // 노드마다 diff를 더하는 횟수가 달라야 함.
        // top-down
        void change(int from, int to, int diff){
            add(1, H, 1, diff, from, to);
        }

        void add(int start, int end, int currentNode, int diff, int from, int to){
            if(to<start || end<from) return;

            int count = 0;
            for(int i = start; i<=end; i++){
                if(from<=i && i<= to) count++;
            }
            tree[currentNode] += diff*count;
            
            if(start!=end){
                int mid = (start+end)/2;
                add(start, mid, currentNode*2, diff, from, to);
                add(mid+1, end, currentNode*2+1, diff, from, to);
            }
        }

        // from 부터 to 까지 구간합 구하기
        // bottom-up
        long sum(int from, int to){
            int index1 = H + from - 1; // tree에서 실제 index로 변환
            int index2 = H + to - 1;
            int ret = 0;

            while(index1<=index2){
                if ((index1 % 2) == 0) {
					index1 /= 2;
				} else {
					ret += tree[index1];
					ret %= MOD;
					index1 = index1 / 2 + 1;
				}
				if ((index2 % 2) == 1) {
					index2 /= 2;
				} else {
					ret += tree[index2];
					ret %= MOD;
					index2 = index2 / 2 - 1;
				}
            }

            return ret;
        }
    }

    static int N; // 수의갯수
    static int M; // 변경횟수
    static int K; // 구간합 계산 횟수

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        segTree segmentTree = new segTree(N);

        // 데이터 입력받기
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            segmentTree.append(num);
        }
        segmentTree.fill(1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) { // b 부터 C에 d 더하기
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                segmentTree.change(from, to, diff);

            } else if (a == 2) { // b 부터 c까지 구간합 구하기
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                sb.append(segmentTree.sum(from, to)+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
