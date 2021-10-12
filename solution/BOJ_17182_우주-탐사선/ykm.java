import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;           // 행성의 개수 ~10
    static int start;       // ana호 발사 행성의 위치
    static int [][] dist;   // 행성간 이동시간
    static int[][] next;     // 다음 행성
    static int INF = 1000000;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        next = new int[N][N]; 

        
        //dist배열에 입력받기
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 1; k<=N ; k++){
            for(int i = 0 ; i<N ; i++){
                for(int j = 0 ; j <N ; j++){
                    // 정점 i에서 정점 j까지 가는 기존 경로보다
                    // 정점 k를 거치는게 더 짧으면 업데이트
                    if(dist[i][j]>dist[i][k]+dist[k][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                        next[i][j] = k;
                    }
                
                }
            }
        }

        System.out.println(findRoute(start));        
    }

    private static int findRoute(int start) {
        int sum = 0 ;
        for(int i = 0 ; i<N ; i++){
            if(next[start][i]!=0){
                sum += dist[start][i];
            }
        }
        return 0;
    }
}
