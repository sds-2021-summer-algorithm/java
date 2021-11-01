import java.io.*;
import java.util.*;

public class Main {
    static int[] lastYear, thisYear, inDegree;
    static boolean[][] outs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            lastYear = new int[n + 1];
            outs = new boolean[n + 1][n + 1];
            inDegree = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                lastYear[num] = i;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    if(lastYear[i] < lastYear[j]) { // i가 j보다 잘했으면
                        outs[i][j] = true;
                        inDegree[j]++;
                    }
                }
            }
            Queue<Integer> q = new LinkedList<>();
            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(lastYear[a] > lastYear[b]) {
                    // 작년엔 b가 잘했는데 b -> a
                    // 올해는 a가 b보다 잘함 a -> b
                    inDegree[a]--;
                    inDegree[b]++;
                    outs[b][a] = false;
                    outs[a][b] = true;
                } else {
                    inDegree[b]--;
                    inDegree[a]++;
                    outs[a][b] = false;
                    outs[b][a] = true;
                }
            }
            for (int i = 1; i <= n; i++) {
                if(inDegree[i] == 0) {
                    q.add(i);
                    break;
                }
            }
            if(q.size() == 0) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }
            int i = 1;
            thisYear = new int[n + 1];
            boolean flag = false;
            while (!q.isEmpty()) {
                if(q.size() > 1) {
                    flag = true;
                    break;
                }
                int cur = q.remove();
                thisYear[i++] = cur;
                for (int j = 1; j <= n; j++) {
                    if(outs[cur][j]) {
                        inDegree[j]--;
                        if(inDegree[j] == 0) q.add(j);
                    }
                }
            }
            if(flag) {
                sb.append("?\n");
                continue;
            }
            if(i < n) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }
            for (int j = 1; j <= n; j++) {
                sb.append(thisYear[j]);
                if(j != n) sb.append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
