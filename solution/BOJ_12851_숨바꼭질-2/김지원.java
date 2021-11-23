import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] visit;
    static int minTime = 987654321;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        // 1. 값 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new int[100001];
        if (N >= M) {
            System.out.println(0);
        } else {
            bfs();
            System.out.println(minTime + "\n" + count);
        }
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(N);
        visit[N] = 1;

        while (!que.isEmpty()) {
            int now = que.poll();

            // now 방문 시간이 최소 시간보다 크면 뒤는 더 볼 필요 없음
            if (minTime < visit[now]) return;

            for (int i=0; i<3; i++) {
                int next;

                if (i == 0)         next = now + 1;
                else if (i == 1)    next = now - 1;
                else                next = now * 2;

                if (next < 0 || next > 100000) continue;

                if (next == M) {
                    minTime = visit[now];
                    count++;
                }

                // 첫 방문이거나 (time[next] == 0)
                // 이미 방문한 곳이어도 같은 시간에 방문했다면 (time[next] == time[now] + 1)
                // 경우의 수에 추가될 수 있기 때문에 Queue 에 한번 더 넣어줌
                if (visit[next] == 0 || visit[next] == visit[now] + 1) {
                    que.add(next);
                    visit[next] = visit[now] + 1;
                }
            }
        }
    }
}
