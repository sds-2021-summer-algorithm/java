import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static boolean noRoot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // parent 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1)
                    union(i, j);
            }
        }

        // 2. 가능한 루트인지 확인
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int to;
        noRoot = false;
        for (int i = 1; i < M; i++) {
            to = Integer.parseInt(st.nextToken());
            if (find(start) != find(to)) {
                noRoot = true;
                break;
            }
            start = to;
        }

        // 3. 결과 출력
        bw.write(noRoot ? "NO" : "YES");
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
}