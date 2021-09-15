import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static boolean[] know;
    static int[] parent;
    static ArrayList<Integer> partyList[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken()); // 사람 수
        M = Integer.parseInt(st.nextToken()); // 파티 수
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

        // 2. 진실을 아는 사람이 0이라면 총 파티 수 리턴
        if (K == 0) {
            System.out.println(M);
            return;
        }

        // 3. build parent
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        // 4. 진실을 아는 사람저장
        know = new boolean[N + 1];
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            know[a] = true;
        }

        // 5. build party
        partyList = new ArrayList[M];
        for (int i = 0; i < M; i++)
            partyList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] sp = br.readLine().split(" ");
            int totalP = Integer.parseInt(sp[0]);

            if (totalP <= 1) {
                partyList[i].add(Integer.parseInt(sp[1]));
                continue;
            }

            for (int j = 1; j < totalP; j++) {
                int a = Integer.parseInt(sp[j]);
                int b = Integer.parseInt(sp[j + 1]);

                if (find(a) != find(b))
                    union(a, b);

                partyList[i].add(a);
                partyList[i].add(b);
            }
        }

        // 6. 진실 아는 사람과 함께 파티에 참관하는 사람 true로 업데이트
        boolean[] visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (know[i] && !visit[i]) {
                int p = find(i);
                for (int j = 1; j <= N; j++) {
                    if (find(j) == p) {
                        know[j] = true;
                        visit[j] = true;
                    }
                }
            }
        }

        // 7. 가능한 파티 수 구하기
        int maxParty = 0;
        for (int i = 0; i < M; i++) {
            int size = partyList[i].size();
            int count = 0;

            for (int j = 0; j < size; j++)
                if (!know[partyList[i].get(j)])
                    count++;

            if (count == size)
                maxParty++;
        }
        System.out.println(maxParty);
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static int find(int id) {
        if (parent[id] == 0)
            return id;
        if (parent[id] == id)
            return id;
        return parent[id] = find(parent[id]);
    }
}