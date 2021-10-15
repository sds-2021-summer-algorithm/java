import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;           // 행성의 개수 ~10
    static int start;       // ana호 발사 행성의 위치
    static int [][] dist;   // 행성간 이동시간
    static int INF = 1000000;

    static boolean[] visited;     // 행성 방문여부 표시
    static int ans = INF;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        visited = new boolean[N];
        visited[start] = true;
        
        //dist배열에 입력받기
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k<N ; k++){
            for(int i = 0 ; i<N ; i++){
                for(int j = 0 ; j <N ; j++){
                    // 정점 i에서 정점 j까지 가는 기존 경로보다
                    // 정점 k를 거치는게 더 짧으면 업데이트
                    if(dist[i][j]>dist[i][k]+dist[k][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                
                }
            }
        }

        findRoute(start);        
    }


    // 시작지점에서 부터 dfs를 돌며 최소 경로 찾기
    private static void findRoute(int current) {

        dfs(0, current, 0);
        System.out.println(ans);

        return ;
    }


    private static void dfs(int count, int current, int sum) { // 현재 행성의 방문 순서, 방문할 행성번호
        
        if(sum>ans) return; // 현재 저장중인 값보다 크면 더 진행할 필요가 없음.

        // 모든 행성을 방문 했으면 return
        boolean allVisited = true;
        for(int i = 0 ; i< N; i++){
            if(!visited[i]){
                allVisited = false;
            }
        }
        if(allVisited) {
            ans = Math.min(ans, sum);
            return;
        }
        for(int i = 0 ; i<N ; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(count+1, i, sum + dist[current][i]);
                visited[i] = false;
            }
        }
    }
}
