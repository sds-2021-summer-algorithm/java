import java.util.*;

public class Solution {
    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static Queue<Coord> pQueue;
    static boolean[][] visit;
    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        // 1. 전체 대기실 보기
        for (int room = 0; room < 5; room++) {
            // 2. 각 대기실 map 만들기
            map = new char[5][5];
            pQueue = new ArrayDeque<>();
            for (int i = 0; i < 5; i++) {
                String info = places[room][i];
                for (int j = 0; j < 5; j++) {
                    map[i][j] = info.charAt(j);

                    // 사람 위치 큐에 저장
                    if (map[i][j] == 'P')
                        pQueue.offer(new Coord(i, j));
                }
            }

            // 3. dfs를 활용해 거리두기 준수 하였는지 확인
            visit = new boolean[5][5];
            answer[room] = dfs() ? 1 : 0;
        }

        return answer;
    }

    static boolean dfs() {
        while (!pQueue.isEmpty()) {
            Coord current = pQueue.poll();
            visit[current.x][current.y] = true; // 나중에 확인하는 과정 중 현재 사람위치 제외하기

            for (int i = 0; i < 4; i++) {
                int mx = dx[i] + current.x;
                int my = dy[i] + current.y;
                if (mx >= 0 && my >= 0 && mx < 5 && my < 5) {
                    if (map[mx][my] == 'O') { // 테이블 사이 사람 있는지 확인
                        for (int j = 0; j < 4; j++) {
                            int ox = dx[j] + mx;
                            int oy = dy[j] + my;
                            if (ox >= 0 && oy >= 0 && ox < 5 && oy < 5) {
                                if (map[ox][oy] == 'P' && !visit[ox][oy]) // 사람!
                                    return false;
                            }
                        }
                    } else if (map[mx][my] == 'P') // 바로 옆에 사람 위치
                        return false;
                }
            }
        }
        return true;
    }
}