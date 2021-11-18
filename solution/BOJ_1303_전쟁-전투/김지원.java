import java.util.*;
import java.io.*;

class Main {
    static int N,M;
    static int c =0;
    static Character[][] map;
    static int[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> que = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        map = new Character[N][M];
        visit = new int[N][M];
        int me = 0;
        int you = 0;
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if (visit[i][j] != 1){
                    c=1;
                    que.add(new int[] {i,j});
                    visit[i][j] = 1;
                    bfs();
                    if(map[i][j]=='W')
                        me += c*c;
                    else
                        you += c*c;
                }
            }
        }

        System.out.println(me + " " + you);
    }

    static void bfs() {
        // bfs 시작
        while(!que.isEmpty()) {
            int[] out = que.poll();
            int x = out[0];
            int y = out[1];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 > nx || nx >= N || 0 > ny || ny >= M || visit[nx][ny] == 1) {
                    continue;
                }
                if (map[nx][ny] != map[x][y])
                    continue;
                c++;
                visit[nx][ny] = 1;
                que.add(new int[] {nx, ny});
            }
        }
    }
}
