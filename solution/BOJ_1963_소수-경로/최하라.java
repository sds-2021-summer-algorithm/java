import java.io.*;
import java.util.*;

public class Main {
    static boolean[] primes = new boolean[10000];
    static boolean[] checked = new boolean[10000];
    static int[] count;
    static int MAX = 10000;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. 에라토스테네스의 체
        eratosthenes();

        // 2. bfs를 활용해 최소 변환 회수 구하기
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from == to) {
                sb.append(0).append("\n");
                continue;
            }

            count = new int[10000];
            visit = new boolean[10000];
            int result = bfs(from, to);
            sb.append(visit[to] ? result : "Impossible").append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void eratosthenes() {
        for (int i = 2; i < MAX; i++) {
            if (!checked[i]) {
                primes[i] = true;
                for (int j = i * 2; j < MAX; j += i) {
                    checked[j] = true;
                }
            }
        }
    }

    static int bfs(int from, int to) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(from);
        visit[from] = true;

        while (!queue.isEmpty()) {
            int val = queue.poll();
            if (from == to)
                break;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    if (i == 0 && j == 0)
                        continue; // 시작점이 0이 되면 안된다

                    int next = replace(val, i, j);
                    if (primes[next] && !visit[next]) {
                        count[next] = count[val] + 1;
                        queue.add(next);
                        visit[next] = true;
                    }
                }
            }
        }
        return count[to];
    }

    static int replace(int current, int index, int val) {
        StringBuilder sb = new StringBuilder(String.valueOf(current));
        sb.setCharAt(index, (char) (val + '0'));
        return Integer.parseInt(sb.toString());
    }
}