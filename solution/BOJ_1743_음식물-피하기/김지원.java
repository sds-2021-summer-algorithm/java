import java.util.*;
import java.io.*;

class Main {
    static int N,M,T;
    static int[][] map,visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visit = new int[N+1][M+1];
        int count = 0;

        while(T-- >0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x-1][y-1] = 1;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
//                System.out.print(map[i][j] +" ");
                if (map[i][j] == 1 && visit[i][j] == 0){
                    int result = bfs(i,j);
                    if (count < result){
                        count = result;
                    }
                }
            }
//            System.out.println();
        }
        System.out.println(count);
    }

    static int bfs(int a, int b) {
        Queue<int[]> que = new LinkedList<>();
        int cnt = 1;
        que.add(new int[] {a,b});
        visit[a][b] = 1;

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
                if(map[nx][ny] == 1) {
                    visit[nx][ny] = 1;
                    cnt = cnt+1;
                    que.add(new int[] {nx, ny});
                }
            }
        }
        return cnt;
    }
}
