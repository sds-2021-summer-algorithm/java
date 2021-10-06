import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class maze{
    int x;
    int y;
    int cnt;
    public maze(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {

    static int N,M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];

        for(int i =0; i< N; i++){
            String input = br.readLine();
            for (int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input.substring(j,j+1));
            }
        }

        bfs(0,0);
    }

    private static void bfs(int a, int b) {
        Queue<maze> que = new LinkedList<>();
        que.add(new maze(a,b,0));
        visit[a][b] = true;

        while(!que.isEmpty()){
            maze z = que.poll();

            for(int i=0; i<4; i++){
                int nr = z.x + dr[i];
                int nc = z.y + dc[i];

                if(nr < 0 || nc < 0 || nr > N || nc > M || visit[nr][nc] || map[nr][nc] == 0) continue;
//                System.out.println(nr+" "+nc+" "+z.cnt);
                if(nr == N-1 && nc == M-1){
                    System.out.println(z.cnt+2);
                    System.exit(0);
                }

                que.add(new maze(nr,nc,z.cnt+1));
                visit[nr][nc] = true;

            }
        }
    }
}
