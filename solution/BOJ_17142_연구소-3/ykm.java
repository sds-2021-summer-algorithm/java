import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
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

        @Override
        public int compareTo(Main.virus o) {
            return this.count - o.count;
        }
    }

    static int N; // 연구소의 크기
    static int M; // 바이러스 개수
    static int[][] Map; // 0: 빈칸, 1:벽, 2:바이러스(비활성), 3:바이러스(활성)
    static boolean[][] isVirus;

    static ArrayList<virus> v = new ArrayList<virus>();

    static int[] mx = { -1, 1, 0, 0 };
    static int[] my = { 0, 0, -1, 1 };
    static int answer;
    static int area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N][N];
        area = N * N; // 바이러스가 갈수있는 공간의 넓이 = 연구소에서 벽과 바이러스 제외한 공간의 넓이
        answer = N * N; // 걸리는 최종 시간

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
                if (Map[i][j] == 1)
                    area--;
                else if (Map[i][j] == 2) {
                    v.add(new virus(i, j));
                    area--;
                }
            }
        }

        if (area == 0)
            answer = 0;
        else
            activate(-1, 0); // 바이러스가 퍼져야하는 공간이 있을때만 활성화 시키기

        if (answer == N * N)
            System.out.println(-1);
        else
            System.out.println(answer);
        br.close();

    }

    // DFS로 활성화 시킬 바이러스 고르기
    public static void activate(int index, int count) {
        if (count == M) { // 활성화가 끝나면 퍼뜨려봐야 함.
            answer = Math.min(spread(), answer);
            return;

        } else { // 활성화 시킬 M개의 바이러스 고르기
            for (int i = index + 1; i < v.size(); i++) {
                virus current = v.get(i);
                current.active = true;
                current.count = 0;
                activate(i, count + 1);
                current.active = false;
                current.count = -1;
            }
        }
    }

    // 불가능한 경우 -> 0
    // 가능한 경우 -> 퍼뜨리는데 걸린 시간
    // BFS로 퍼뜨리기
    public static int spread() {
        isVirus = new boolean[N][N];
        int countVirus = 0;
        int turn = 0;
        Queue<virus> q = new LinkedList<virus>();
        // pq는 메모리 효율이 좋지만 정렬때문에 느림

        for (int i = 0; i < v.size(); i++) { // 전체 바이러스중 활성화 된 바이러스만 q에 넣기
            virus current = v.get(i);
            if (current.active) {
                isVirus[current.x][current.y] = true; // 바이러스가 이미 퍼졌음을 표시
                q.add(current);
            }
        }

        while (!q.isEmpty()) {
            virus current = q.poll();
            int t = current.count;

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + mx[i];
                int nextY = current.y + my[i];

                // 연구소 범위를 벗어나면 안됨.
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                // 벽으로는 바이러스가 퍼질수 없음.
                // 이미 바이러스가 퍼진곳 제외
                if (Map[nextX][nextY] == 1 || isVirus[nextX][nextY])
                    continue;

                // 비활성화 바이러스를 만난 경우
                if (Map[nextX][nextY] == 2) {
                    isVirus[nextX][nextY] = true;
                    q.add(new virus(nextX, nextY, true, t + 1));
                } else if (Map[nextX][nextY] == 0) {
                    isVirus[nextX][nextY] = true;
                    countVirus++;
                    q.add(new virus(nextX, nextY, true, t + 1));
                    turn = Math.max(turn, t + 1);
                }
            }
        }

        if (countVirus == area)
            return turn;
        else
            return N * N;
    }
}
