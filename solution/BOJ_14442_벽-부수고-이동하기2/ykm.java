import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    public static class node{
        int x, y;
        int move; // 이동횟수
        int wall; // 부슨 벽의 수

        public node(int x, int y, int move, int wall){
            this.x = x;
            this.y = y;
            this.move = move;
            this.wall = wall;
        }
    }

    static int M;
    static int N; 
    static int K; // 부슬수있는 벽의 갯수
    static int answer = Integer.MAX_VALUE;

    static char[][] map;
    static boolean[][][] isVisited;
    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,1,-1};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        isVisited = new boolean[M][N][K+1]; // 위치 x,y , 부슨벽의 수

        for(int i = 0 ; i<M; i++){
            map[i] = br.readLine().toCharArray();
        }

        isVisited[0][0][0] = true;
        Queue<node> q = new ArrayDeque<node>();
        q.offer(new node(0,0,1,0));

        while(!q.isEmpty()){
            node current = q.poll();

            if(current.move >= answer){
                continue;
            }

            if(current.x == M-1 && current.y == N-1){
                answer = Math.min(answer, current.move);
            }

            for(int i = 0 ; i<4; i++){
                int nextX = current.x + mx[i];
                int nextY = current.y + my[i];

                if(nextX<0 || nextY<0|| nextX>=M || nextY>=N ){ // 범위를 벗어남
                    continue;
                }

                if(map[nextX][nextY]=='1'){ // 벽
                    if(current.wall<K && !isVisited[nextX][nextY][current.wall+1]){        // 벽을 부슬수 있는 경우
                        isVisited[nextX][nextY][current.wall+1] = true;
                        q.offer(new node(nextX, nextY, current.move+1, current.wall+1));
                    }
                }else{
                    if(isVisited[nextX][nextY][current.wall]){
                        continue;
                    }
                    isVisited[nextX][nextY][current.wall] = true;
                    q.offer(new node(nextX, nextY, current.move+1, current.wall));
                }
            }
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
    }
}
