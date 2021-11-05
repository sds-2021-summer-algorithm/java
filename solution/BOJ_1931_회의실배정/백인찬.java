import java.io.*;
import java.util.*;

public class Main {
    static int[][] info;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        info = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            info[i][0] = start;
            info[i][1] = end;
        }
        Arrays.sort(info, (o1, o2) -> {
            if (o2[1] == o1[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int count = 1;
        int end = info[0][1];
        for (int i = 1; i < info.length; i++) {
            if(end > info[i][0]) continue;
            count++;
            end = info[i][1];
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
