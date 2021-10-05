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
    static int[] works;     // 일별 소요 시간 / 소요시간 합
    static int[] limit;     // 제한된 시간 저장
    static int[] sum;   // 각 index를 시간으로 생각할때 처리가능한 최대 일의 개수 저장

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        works = new int[N];
        sum = new int[N];
        limit = new int[M];


        st = new StringTokenizer(br.readLine());
        int s = 0;
        for(int i = 0; i<N; i++){
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

    private static int predict(int a) {

        int i = 0;
        for(i = 0 ; i< N ; i++){
            if(sum[i]>a) break;
        }
        return i;
    }
}
