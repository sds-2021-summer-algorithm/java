import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 무한대 |v - 1| * maxLength
    private static int INF = 16 * 1_000_000;
    // vertex 수
    static int n;
    // graph 배열
    static int arr[][];
    // dp[node][visit] = k -> 현재 node번에 잇고 visit를 방문하고 왔을 때
    // 0번 노드로 가는 최소의 거리
    static int dp[][];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][(1 << n) - 1];

        for(int i = 0 ; i < n; i++)
            Arrays.fill(dp[i], INF);

        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int value = Integer.parseInt(st.nextToken());
                arr[i][j] = value;
            }
        }

        bw.write(tsp(0, 1) + "\n");

        br.close();
        bw.close();
    }
  
    private static int tsp(int node, int visit){
    }
}
