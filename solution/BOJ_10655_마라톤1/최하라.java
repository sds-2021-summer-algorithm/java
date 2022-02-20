import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] coords;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        coords = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken());
            coords[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dist = new int[N - 1];
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            dist[i] = manhattan(coords[i], coords[i + 1]);
            sum += dist[i];
        }

        int max = 0;
        for (int i = 0; i < N - 2; i++) {
            int skip = manhattan(coords[i], coords[i + 2]);
            max = Math.max(max, dist[i] + dist[i + 1] - skip);
        }

        System.out.println(sum - max);
    }

    static int manhattan(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
}