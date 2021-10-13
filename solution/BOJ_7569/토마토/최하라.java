import java.io.*;
import java.util.*;

public class Main {
    static class Tomato {
        int x, y, h;
        public Tomato(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static int N, M, H, notReady, days;
    static ArrayList<Integer>[][] box;
    static Deque<Tomato> deque; //익은 토마토 위치 저장
    static boolean possible;
    static int[] dx = new int[]{0,0,-1,1};
    static int[] dy = new int[]{-1,1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력 받기
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new ArrayList[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                box[i][j] = new ArrayList<>();
        }

        //토마토 정보 저장
        notReady = 0;
        deque = new ArrayDeque<>();
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[i][j].add(Integer.parseInt(st.nextToken()));
                    if (box[i][j].get(h) == 1) deque.add(new Tomato(i, j, h));
                    else if (box[i][j].get(h) == 0) notReady++;
                }
            }
        }

        //2. 익지 않은 토마토가 없는 경우 || 익을 수 없는 경우
        if (notReady == 0) {
            System.out.println(0);
            return;
        }
        if (deque.isEmpty()) { //no need?
            System.out.println(-1);
            return;
        }

        //3. 몇일 걸리는지 확인
        days = 0;
        search();

        System.out.println(possible ? days : -1);
    }

    static void search() {
        while(!deque.isEmpty()) {
            days++;
            int size = deque.size();
            possible = false; //한번이라도 익었는지 체크
            for (int idx = 0; idx < size; idx++) {
                Tomato current = deque.pollFirst();

                //상,하,좌,우 체크
                for (int i = 0; i < 4; i++) {
                    int mx = current.x + dx[i];
                    int my = current.y + dy[i];
                    if (mx < 0 || my < 0 || mx >= N || my >= M) continue;
                    if (box[mx][my].get(current.h) == 0) {
                        notReady--;
                        box[mx][my].set(current.h, 1);
                        deque.addLast(new Tomato(mx, my, current.h));
                        possible = true;
                    }
                }

                //위층 체크
                if (current.h - 1 >= 0) {
                    if (box[current.x][current.y].get(current.h - 1) == 0) {
                        notReady--;
                        box[current.x][current.y].set(current.h - 1, 1);
                        deque.addLast(new Tomato(current.x, current.y, current.h - 1));
                        possible = true;
                    }
                }
                // 아래층 체크
                if (current.h + 1 < H) {
                    if (box[current.x][current.y].get(current.h + 1) == 0) {
                        notReady--;
                        box[current.x][current.y].set(current.h + 1, 1);
                        deque.addLast(new Tomato(current.x, current.y, current.h + 1));
                        possible = true;
                    }
                }

            }

            //모두 익음
            if (notReady == 0) break;
            if (!possible) break;
        }
    }
}
