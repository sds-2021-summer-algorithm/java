import java.io.*;
import java.util.*;

public class Main {
    static class Coord implements Comparable<Coord>{
        int x, y, dist;
        public Coord(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Coord o) {
            if (this.dist != o.dist) {
                return this.dist - o.dist;
            } else {
                if (o.x == this.x)
                    return this.y - o.y;
                return this.x - o.x;
            }
        }
    }

    static int N, totalFSize, seconds, sharkSize, eatCount;
    static int[][] map;
    static boolean[][] visit;
    static Coord shark, nearfish;
    static PriorityQueue<Coord> pq;

    static int[] dx = new int[]{-1,0,0,1};
    static int[] dy = new int[]{0,-1,1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //1. 입력 받기
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        //build map
        totalFSize = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Coord(i, j, 0);
                    map[i][j] = 0;
                } else {
                    totalFSize += map[i][j];
                }
            }
        }

        //2. no fish exist
        if (totalFSize == 9) {
            System.out.println(0);
            return;
        }

        //3. 시간 구하기
        seconds = 0; eatCount = 0;
        sharkSize = 2;
        pq = new PriorityQueue<>();
        while (totalFSize > 0) {
            nearfish = new Coord(0,0,0);
            visit = new boolean[N][N];
            visit[shark.x][shark.y] = true;

            //가가운 물고기 위치 찾기
            pq = new PriorityQueue<>();
            pq.add(new Coord(shark.x, shark.y, 0));
            searchFish();

            if (nearfish.dist == 0) break;

            //상어 사이즈 조정
            eatCount++;
            if (eatCount == sharkSize) {
                eatCount = 0;
                sharkSize++;
            }

            //물고기 먹힘 --> set to 0
            totalFSize -= map[nearfish.x][nearfish.y];
            map[nearfish.x][nearfish.y] = 0;

            //상어 위치 조정
            shark = new Coord(nearfish.x, nearfish.y, 0);
            //이동 시간
            seconds += nearfish.dist;
        }

        System.out.println(seconds);
    }

    static void searchFish() {
        while (!pq.isEmpty()) {
            Coord current = pq.poll();

            if (map[current.x][current.y] != 0 && map[current.x][current.y] < sharkSize) {
                nearfish = new Coord(current.x, current.y, current.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int mx = current.x + dx[i];
                int my = current.y + dy[i];
                if (mx < 0 || my < 0 || mx >= N || my >= N) continue;
                if (map[mx][my] <= sharkSize && !visit[mx][my]) {
                    visit[mx][my] = true;
                    pq.add(new Coord(mx, my, current.dist + 1));
                }
            }
        }
    }
}
