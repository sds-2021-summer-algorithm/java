import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    static int N; // 수의 개수
    static int Q; // 턴의 개수
    static int L; // 트리의 길이
    static long[] nums;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        // 트리를 위한 배열의 길이 구하기
        L = 1;
        while(L<N){
            L*=2;
        }
        nums = new long[L*2 + 1];

        // index = h인 위치 부터 숫자 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N ; i++){
            nums[L+i] = Long.parseLong(st.nextToken());
        }

        // 세그먼트 트리 만들기
        tree(1);

        for(int i = 0 ; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // start 부터 end까지 합
            if(a>b){
                int temp = a;
                a=b;
                b=temp;
            }
            bw.write(add(a, b, 1, L, 1)+ "\n");

            int index = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            // index번째 수를 value로 바꾸기
            change(index, value);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 현재 노드가 가지고 있는 범위 start ~ end
    // 구해야 하는 범위 a ~ b
    private static long add(int a, int b, int start, int end , int node) {
        
        // 관련 없는 경우
        if(b<start || end < a) return 0;
        // 현재 노드가 가진 값을 모두 포함 하는 경우
        else if(a<=start && end<=b) return nums[node];
        // 현재 노드가 가진 값을 일부 포함 하는 경우 -> 자식에게 물어보기
        else {
            int mid = (start+ end)/2;
            return add(a,b, start, mid, node*2) + add(a,b,mid+1,end,node*2+1);
        }
    }

    // 현재값과 바뀔값 사이 편차를 구해서
    // 관련있는 노드에 편차를 더하자
    public static void change(int index, long value) {
        index = L+index-1;
        long diff = value - nums[index] ;

        while(index>0){
            nums[index] += diff;
            index /= 2;
        }
    }

    // 자식노드의 합을 부모노드에 저장
    public static long tree(int index) {
        if(index>= L) return nums[index];
        return nums[index] = tree(index*2) + tree(index*2+1);
    }
}