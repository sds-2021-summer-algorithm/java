import java.io.*;
import java.util.*;;

public class Main {
    static int N, K;
    static int[][] dist;
    static boolean[] visited;
    static List<Integer> permutation = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        visited[K] = true;
        backtracking(1);
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
    static void backtracking(int count) {
        if(count == N) {
            int cur = K;
            int total = 0;
            for (int next : permutation) {
                total += dist[cur][next];
                cur = next;
            }
            min = Math.min(min, total);
            return;
        }
        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            permutation.add(i);
            backtracking(count + 1);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }
    }
}
