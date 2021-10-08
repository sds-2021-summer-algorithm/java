import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static int[] works;     // 각 소요 시간 
    static int[] sum;       // 소요시간 합

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        works = new int[N+1];
        sum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        int s = 0;
        for(int i = 1; i<=N; i++){
            works[i] = Integer.parseInt(st.nextToken()); 
            s += works[i];
            sum[i] = s;
        }

        // 출력을 위해 
        for(int i = 0 ; i<M ; i++) {
            bw.write(predict(Integer.parseInt(br.readLine()))+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 이분 탐색 도입하기
    // t 시간동안 할수있는 일의 개수
    private static int predict(int t) {

        int start = 0;
        int end = N;
        int mid = (int) (Math.ceil((float)(start + end)/2));
        while(start<end){
            if(t==sum[mid]){
                return mid;
            }else if(t>sum[mid]){
                start = mid;
            }else{
                end = mid - 1;
            }
            mid = (int) (Math.ceil((float)(start + end)/2));
        }
        
        return end;
    }
}
