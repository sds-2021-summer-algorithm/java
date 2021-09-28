import java.io.*;
import java.util.*;

public class boj_3020 {
    static int N, H;
    static int[] cave;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        cave = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if(i % 2 == 0) { // 석순 = 아래에서
                for (int j = 1; j <= height; j++) {
                    cave[j]++;
                }
            } else { // 종유석 = 위에서
                for (int j = H; j > H - height; j--) {
                    cave[j]++;
                }
            }
        }
        Arrays.sort(cave);
        int count = 1;
        for (int i = 2; i <= H; i++) {
            if(cave[i] == cave[1]) count++;
            else break;
        }
        bw.write(cave[1] + " " + count + "\n");
        bw.flush();
        bw.close();
    }
}
