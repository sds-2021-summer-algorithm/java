import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T,N,x1,y1,x2,y2;
    static int[][] map;
    static int[][] visit;
    static int[] dx = {1,2,2,1,-1,-2,-2,-1};
    static int[] dy = {2,1,-1,-2,-2,-1,1,2};

    public static void main(String[] args) throws IOException {
        // 1. 값 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T-- >0){
//            System.out.println(T+"번쨰");
            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            visit = new int[N+1][N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            if(x1 == x2 && y1 == y2){
                System.out.println(0);
            }
            else{
                bfs(x1,y1,x2,y2,N);
            }
        }


    }

    private static int bfs(int x1, int y1, int x2, int y2, int N) {
        Queue<int[]> que = new LinkedList<>();
        int c = 0;
        que.add(new int[] {x1,y1,c});
        visit[x1][y1] = 1;

        while (!que.isEmpty()) {
            int[] out = que.poll();
            int xx = out[0];
            int yy = out[1];
            int cnt = out[2];

            for(int i=0; i<8; i++){
                int dr = xx + dx[i];
                int dc = yy + dy[i];

                if( dr < 0 || dc < 0 || dr > N-1 || dc > N-1 || visit[dr][dc] == 1){
                    continue;
                }

                if(dr == x2 && dc == y2){
                    System.out.println(cnt+1);
                    return 0;
                }
                visit[dr][dc] = 1;
                que.add(new int[] {dr,dc,cnt+1});
            }
        }
        return 0;
    }
}
