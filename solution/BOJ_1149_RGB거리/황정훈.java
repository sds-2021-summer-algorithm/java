import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static int[][] colors, cost;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        colors = new int[N][3];
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }

        cost[0] = colors[0];
        for (int i = 1; i < N; i++) {
            cost[i][0] = Math.min(cost[i-1][1] + colors[i][0], cost[i-1][2] + colors[i][0]); // R 고를때
            cost[i][1] = Math.min(cost[i-1][0] + colors[i][1], cost[i-1][2] + colors[i][1]); // G 고를때
            cost[i][2] = Math.min(cost[i-1][0] + colors[i][2], cost[i-1][1] + colors[i][2]); // B 고를때
        }

        bw.write(Math.min(Math.min(cost[N-1][0], cost[N-1][1]), cost[N-1][2])+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
