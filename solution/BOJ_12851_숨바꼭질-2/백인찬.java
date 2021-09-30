import java.io.*;
import java.util.*;

public class Main {
    static final int max = 100000, min = 0;
    static class Pair {
        int i, day;

        public Pair(int i, int count) {
            this.i = i;
            this.day = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(N, 0));

        visited[N] = true;
        int min_day = -1;
        int count = 0;
        while (!q.isEmpty()) {
            Pair now = q.remove();
            visited[now.i] = true;
            if(min_day == -1 && now.i == K) {
                min_day = now.day;
                count++;
            } else if(now.i == K && min_day == now.day) {
                count++;
            }

            if(now.i + 1 >= min && now.i + 1 <= max) {
                if (!visited[now.i + 1]) {
                    q.add(new Pair(now.i + 1, now.day + 1));
                }
            }

            if (now.i - 1 >= min && now.i - 1 <= max) {
                if (!visited[now.i - 1]) {
                    q.add(new Pair(now.i - 1, now.day + 1));
                }
            }

            if (2 * now.i >= min && 2 * now.i <= max) {
                if (!visited[2 * now.i]) {
                    q.add(new Pair(2 * now.i, now.day + 1));
                }
            }
        }
        bw.write(min_day + "\n" + count + "\n");
        bw.flush();
        bw.close();
    }
}
