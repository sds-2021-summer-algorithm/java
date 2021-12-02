import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.BufferUnderflowException;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static int K;
    static int[] indexedTree;
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
        int h = 1;
        while(h<N) h*=2;
        
        // 트리의 초기 원소 입력받기
        indexedTree = new int[ h*2 + 1]; // 원소는 index=1부터 채움
        for(int i = 1; i<= h*2; i++){
            indexedTree[i] = 1;
        }
        for(int i = 0 ; i<N ; i++){
            indexedTree[h+i] = Integer.parseInt(br.readLine());
        }
        fillTree(1, h);

        // 쿼리 입력받기
        for(int i = 0 ; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==1){
                change(b,c);
            }else if(a==2){
                sb.append(multi(b,c)+"\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int multi(int b, int c) {
        return 1;
    }

    private static void change(int b, int c) {

    }

    private static int fillTree(int node, int h) {
        if(node>=h) return indexedTree[node];
        else return indexedTree[node] = indexedTree[node*2] * indexedTree[node*2 + 1];
    }
}
