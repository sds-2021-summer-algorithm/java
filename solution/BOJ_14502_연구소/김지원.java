
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    static int[][] virus_map;
    static int[] dx = {-1,1,0,0}; //상하좌우
    static int[] dy = {0,0,-1,1};
    static int safe_count;

    static class virus {
        int x,y;
        virus(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        safe_count = 0; //안전구간

        //데이터 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0); //dfs 바로 돌리기

        //확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(virus_map[i][j] + " ");
            }
            System.out.println();
        }

//        //안전구간 갯수 카운트
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (virus_map[i][j] == 0){
//                    safe_count++;
//                }
//            }
//        }
        System.out.println(safe_count);

    }

    private static void dfs(int dep) {
        if (dep == 3){ //벽 세개 세웠으면 이제 바이러스가 움직임
            bfs(); //
            return;
        }
        //백트래킹
        for(int i =0; i < n; i++){
            for (int j=0; j < m; j++){
                if (map[i][j] == 0) { //빈공간이면
                    map[i][j] = 1;
                    dfs(dep +1); //백트래킹
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() { //bfs로 바이러스 움직여주기
        virus_map = new int[n][m];
        Queue<virus> queue1 = new LinkedList<virus>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                virus_map[i][j] = map[i][j];
            }
        }

        //시작 노드 Queue에 넣기
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(virus_map[i][j] == 2){
                    queue1.add(new virus(i,j));
                }
            }
        }
        while(!queue1.isEmpty()){ //not 꼭 붙여주기!!
            //Q를 하나씩 빼면서 visit에 넣고 인접한 노드 큐에 넣기
            virus v = queue1.remove();

            for(int i=0; i<4; i++){
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m){ //범위를 넘어가지 않으면
                    if (virus_map[nx][ny] == 0){
                        virus_map[nx][ny] =2;
                        queue1.add(new virus(nx,ny));
                    }
                }
            }
        }

        safe(virus_map);
    }

    private static void safe(int[][] virus_map) {
        int count = 0;
        //안전구간 갯수 카운트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virus_map[i][j] == 0){
                    count++;
                }
            }
        }

        safe_count = count;
    }
}
