package P2178;

import java.io.*;
import java.util.*;


public class Main {
    static int N, M; // 최대 100
    static boolean[][] map; // 미로
    static boolean[][] check; // 방문 여부 표시
    static int ans = Integer.MAX_VALUE;
    static Queue<position> q = new LinkedList<position>();
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P2178/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][M+1];
        check = new boolean[N+1][M+1];
        for(int i = 1; i<=N ; i++){
            String line = br.readLine();
            for(int j = 1; j<=M ; j++){
                if(line.charAt(j-1)=='1'){
                    map[i][j] = true;
                }else{
                    map[i][j] = false;
                }
            }
        }

        // 상, 하, 좌, 우
        int [] mx = {-1, 1, 0, 0};
        int [] my = {0, 0, -1, 1};

        check[1][1]=true;
        position start = new position(1,1,1);
        q.offer(start);
        while(!q.isEmpty()){
            position current = q.poll();

            if(current.x == N && current.y == M){
                ans = Math.min(ans, current.move);
                continue;
            }

            for(int i = 0 ; i<4 ; i++){
                int next_x = current.x + mx[i];
                int next_y = current.y + my[i];

                if(next_x > 0 && next_y >0 && next_x <=N && next_y <=M){
                    if(map[next_x][next_y] && !check[next_x][next_y]) {
                        check[next_x][next_y] = true;
                        q.offer(new position(next_x, next_y, current.move+1));
                    }
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    public static class position{
        int x, y;
        int move;
        
        public position(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public String toString() {
            return "position [move=" + move + ", x=" + x + ", y=" + y + "]";
        }
    }
}
