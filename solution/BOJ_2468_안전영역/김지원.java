import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] map;
    static int[][] transmap;
    static boolean[][] visited;
    static int max = 0;
    static int max_num = 0;
    static int min_num = 101;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i =0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<N; j++){
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if(input > max_num){
                    max_num = input;
                }
                if(input < min_num){
                    min_num = input;
                }
            }
        }

        for(int i=min_num-1; i<max_num; i++){
            bfs(i);
        }
        System.out.println(max);

    }

    public static void bfs(int input) {
        transmap = new int[N][N];
        visited = new boolean[N][N];

        int count = 0;

        for(int a=0; a<N; a++){
            for(int b=0; b<N; b++){
                if(map[a][b] < input){
                    transmap[a][b] = 0;
                }
                else transmap[a][b] = 1;
            }
        }

        for(int a=0; a<N; a++){
            for(int b=0; b<N; b++){
                if(transmap[a][b] == 1 && !visited[a][b]){
                    Search(a,b);
                    count++;
                }
            }
        }
        max = Math.max(max,count);

    }

    private static void Search(int a, int b) {
        visited[a][b] = true;
        for(int i=0; i<4; i++){
            int nx = a + dr[i];
            int ny = b + dc[i];

            if(nx >= 0 && ny >= 0 && nx < N && ny <N){
                if(transmap[nx][ny] == 1 && !visited[nx][ny]){
                    Search(nx,ny);
                }
            }
        }
    }

}
