import java.io.*;
import java.util.*;

public class Main {
    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R, moveCount;
    static int[][] groundMap;
    static Queue<Coord> updateQueue;
    static boolean checkUpdate;

    static boolean[][] visit;
    static int[] dx = new int[] { 0, 0, -1, 1 };
    static int[] dy = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // buiild gound
        groundMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                groundMap[i][j] = Integer.parseInt(st.nextToken());
        }

        // 2. 인구이동
        moveCount = 0;
        while (true) { // 이동할 인구가 없을 때까지
            checkUpdate = false;
            visit = new boolean[N][N];
            updateQueue = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j])
                        searchUnited(i, j);
                }
            }
            if (!checkUpdate) // 인구이동 일어나지 않음
                break;

            moveCount++; // 하루 + 1
        }

        // 3. 결과 출력
        System.out.println(moveCount);
        br.close();
    }

    static void searchUnited(int x, int y) { // bfs 사용
        Queue<Coord> unitedQueue = new ArrayDeque<>();
        unitedQueue.add(new Coord(x, y)); // 연합국
        updateQueue.add(new Coord(x, y)); // 나중에 업데이트 할 위치 저장
        visit[x][y] = true;

        int totalPeople = groundMap[x][y];
        int totalNation = 1;

        while (!unitedQueue.isEmpty()) {
            Coord current = unitedQueue.poll();
            for (int i = 0; i < 4; i++) {
                int mx = dx[i] + current.x;
                int my = dy[i] + current.y;

                int currentPop = groundMap[current.x][current.y]; // 비교할 현재 인구

                if (mx >= 0 && my >= 0 && mx < N && my < N) {
                    int diff = Math.abs(groundMap[mx][my] - currentPop);
                    if (diff >= L && diff <= R && !visit[mx][my]) { // 연합국!
                        checkUpdate = true; // 업에디이트 일어날 예정
                        updateQueue.add(new Coord(mx, my)); // 나중에 업데이트할 위치 저장
                        unitedQueue.add(new Coord(mx, my));
                        visit[mx][my] = true;

                        totalPeople += groundMap[mx][my];
                        totalNation++;
                    }
                }
            }
        }

        // 연합국 인구 update
        int average = totalPeople / totalNation;
        if (checkUpdate)
            updateMap(average);
        updateQueue = new ArrayDeque<>();
    }

    static void updateMap(int average) {
        while (!updateQueue.isEmpty()) {
            Coord current = updateQueue.poll();
            groundMap[current.x][current.y] = average;
        }
    }
}