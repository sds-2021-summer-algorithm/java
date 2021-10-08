import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static PriorityQueue<Integer> pq = new PriorityQueue();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            if (P<L) {
                pq.add(1);
                br.readLine();
            }
            else{
                Integer[] points = new Integer[P];
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < P; j++) {
                    points[j] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(points, Collections.reverseOrder());
                pq.add(points[L-1]);
            }
        }
        for (int i = 0; i < N; i++) {
            M -= pq.poll();
            if (M<0){
                sb.append(i);
                break;
            }
        }
        
        bw.write(sb.length()>0 ? sb.toString() : String.valueOf(N)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
