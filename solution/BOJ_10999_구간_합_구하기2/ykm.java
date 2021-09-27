import java.util.*;
import java.io.*;

public class Main {

    static class node {
        int start, end;
        long sum = 0;
        int height;

        public node(int start, int end, long sum, int height) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.height = height;
        }

        @Override
        public String toString() {
            return "node [end=" + end + ", height=" + height + ", start=" + start + ", sum=" + sum + "]";
        }
    }

    static int N; // 수의갯수
    static int M; // 변경횟수
    static int K; // 구간합 계산 횟수

    static node[] tree; // 인덱스 트리
    static int H; // 트리 높이

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/P10999/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리의 높이 구하기
        H = 1;
        while (H < N) {
            H *= 2;
        }
        tree = new node[H * 2 + 1];

        for(int i = 1 ; i<H*2+1; i++){
            tree[i] = new node(i,i,0,0);
        }

        // 데이터 입력받기
        for (int i = 0; i < N; i++) {
            tree[H + i].start = i+1;
            tree[H + i].end = i+1;
            tree[H + i].sum = Integer.parseInt(br.readLine());
        }

        // 인덱스 트리 만들기
        makeTree(1, 1, H, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) { // b 부터 C에 d 더하기
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                add(1, 1, H, b, c, d);

            } else if (a == 2) { // b 부터 c까지 구간합 구하기
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                sb.append(sum(1, 1, H, b, c) + "\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // dfs방식으로 구간합을 저장한 노드 만들기
    public static long makeTree(int node, int start, int end, int h) {

        if (node < tree.length) {
            if (node>=H){ // 말단 노드
                tree[node].height = h;
                return tree[node].sum;
            }
            else {
                tree[node].start = start;
                tree[node].end = end;
                tree[node].height = h;
        
                int mid = (start + end) / 2;
                return tree[node].sum = makeTree(node * 2, start, mid, h + 1)
                        + makeTree(node * 2 + 1, mid + 1, end, h + 1);
            }

        }
        return 0;
    }

    // 현재 노드가 가지고 있는 값의 범위, 찾는 구간
    public static void add(int node, int start, int end, int b, int c, int d) {
        if (node > tree.length)
            return;
        if (c < start || end < b)
            return; // 현재 노드의 범위 밖
        else if (start <= b && c <= end) { // 현재 노드의 범위 속
            tree[node].sum += (c-b+1) * d;
        } else { // 현재 노드의 범위에 걸칠때
            if (start <= b && b <= end) { // 뒤쪽에 걸침
                c = end;
            } else if (start <= c && c <= end) {// 앞쪽에 걸침
                b = start;
            }
            tree[node].sum += (c-b + 1) * d;
        }

        // 자식에게 물어보기
        int mid = (start + end) / 2;
        add(node * 2, start, mid, b, c, d); // left
        add(node * 2 + 1, mid + 1, end, b, c, d); // right

    }

    public static long sum(int node, int start, int end, int b, int c) {
        if (node >= tree.length)
            return 0;

        long sum = 0;
        // 현재 노드가 가지고 있는 범위를 포함
        if (b <= start && end <= c)
            return tree[node].sum;
        // 현재 노드의 범위를 전혀 포함하지 않을때
        else if(c<start || end<b){
            return 0;
        }
        // 현재 노드의 범위가 일부 포함 될때
        else {
            if(start<=b && b<=end && end<c){ // 뒤쪽에 걸침
                c = end;
            }else if(b<start &&start<=c && c<=end){ // 앞쪽에 걸침
                b = start;
            }

            // 자식에게 물어보기
            int mid = (start + end) / 2;
            sum += sum(node * 2, start, mid, b, c); // left
            sum += sum(node * 2 + 1, mid + 1, end, b, c); // right

        }

        return sum;
    }
}
