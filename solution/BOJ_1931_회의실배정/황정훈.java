import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, cnt = 0;
    static int[][] info;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        info = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(info, (a, b) -> a[1]==b[1] ? a[0]-b[0] : a[1]-b[1]);
        int s, e = 0;
        for (int[] cur : info) {
            s = cur[0];
            if(e<=s){
                cnt++;
                e = cur[1];
            }
        }

        bw.write(cnt+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
