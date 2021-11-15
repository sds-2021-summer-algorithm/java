import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class virus implements Comparable<virus> {
        private int x, y;
        private boolean active = false;
        private int count = -1; // 활성화 될때 0로 바꿔야함.

        @Override
        public String toString() {
            return "virus [active=" + active + ", count=" + count + ", x=" + x + ", y=" + y + "]";
        }

        public virus(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public virus(int x, int y, boolean active, int count) {
            this.x = x;
            this.y = y;
            this.active = active;
            this.count = count;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int compareTo(Main.virus o) {
            return this.count - o.count;
        }
    }

    static int N; // 연구소의 크기
    static int M; // 바이러스 개수
    static int[][] Map; // 0: 빈칸, 1:벽, 2:바이러스(비활성), 3:바이러스(활성)

    static ArrayList<virus> v = new ArrayList<virus>();

    static int[] mx = { -1, 1, 0, 0 };
    static int[] my = { 0, 0, -1, 1 };
    static int answer;
    static int area;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N][N];
        area = N * N; // 바이러스가 갈수있는 공간의 넓이 = 연구소에서 벽을 제외한 공간의 넓이
        answer = N * N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
                if (Map[i][j] == 1)
                    area--;
                else if (Map[i][j] == 2) {
                    v.add(new virus(i, j));
                }
            }
        }

        activate(-1, 0);
        if (answer == N * N)
            System.out.println(-1);
        else
            System.out.println(answer);
        br.close();

    }

    // DFS로 활성화 시킬 바이러스 고르기
    public static void activate(int index, int count) {
        if (count == M) { // 활성화가 끝나면 퍼뜨려봐야 함.
            int turn = spread();
            if (turn > 0) {
                answer = Math.min(turn, answer);
            }
            return;

        } else { // 활성화 시킬 M개의 바이러스 고르기
            for (int i = index + 1; i < v.size(); i++) {
                virus current = v.get(i);
                current.setActive(true);
                current.setCount(0);
                activate(i, count + 1);
                current.setActive(false);
                current.setCount(-1);
            }
        }
    }

    // 불가능한 경우 -> 0
    // 가능한 경우 -> 퍼뜨리는데 걸린 시간
    // BFS로 퍼뜨리기
    public static int spread() {
        boolean[][] isVisited = new boolean[N][N];

        Queue<virus> q = new PriorityQueue<virus>();
        for (int i = 0; i < v.size(); i++) {
            virus current = v.get(i);
            if (current.isActive()) {
                q.add(current);
            }
        }

        int countVirus = 0;
        int turn = 0;
        while (!q.isEmpty()) {
            virus current = q.poll();
            isVisited[current.x][current.y] = true;
            countVirus++;
            turn = current.getCount();

            if (countVirus == area)
                return turn;

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + mx[i];
                int nextY = current.y + my[i];

                // 연구소 범위를 벗어나면 안됨.
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                // 벽으로는 바이러스가 퍼질수 없음.
                // 이미 바이러스가 퍼진곳 제외
                if (Map[nextX][nextY] == 1 || isVisited[nextX][nextY])
                    continue;

                else {
                    isVisited[nextX][nextY] = true;
                    q.add(new virus(nextX, nextY, true, turn + 1));
                }
            }

        }
        return 0;
    }
}
