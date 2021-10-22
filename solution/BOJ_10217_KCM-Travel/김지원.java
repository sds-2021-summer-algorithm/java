import java.io.*;
import java.util.*;

public class Main{
    static class AirPlane implements Comparable<AirPlane>{
        int end;
        int cost;
        int time;

        public AirPlane(int end, int cost, int time){
            this.end = end;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(AirPlane airPlane) {
            if(this.time == airPlane.time) return cost - airPlane.cost;
            return this.time - airPlane.time;
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 100 * 1_000;
    static int n, m, k;
    static List<AirPlane> list[];
    static int dp[][];

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < t; i++){
            init();
            int result = dijkstra();
            sb.append(result == INF ? "Poor KCM\n" : result + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dijkstra() {
        for(int i = 1 ; i < dp.length; i++)
            Arrays.fill(dp[i], INF);


       PriorityQueue<AirPlane> queue = new PriorityQueue<>();
       queue.add(new AirPlane(1, 0, 0));
       // 1번 노드까지 가는데 0 비용으로 갔을 때의 최소 시간
       dp[1][0] = 0;

       while(!queue.isEmpty()){
           AirPlane airPlane = queue.poll();
           int node = airPlane.end;
           int cost = airPlane.cost;
           int time = airPlane.time;

           if(node == n) break;
           if(dp[node][cost] < time) continue;
           dp[node][cost] = time;

           for(int i = 0 ; i < list[node].size(); i++){
               AirPlane toAirplane = list[node].get(i);
               int toNode = toAirplane.end;
               int toCost = cost + toAirplane.cost;
               int toTime = time + toAirplane.time;

               if(toCost > m) continue;
               if(dp[toNode][toCost] > toTime){
               	   // 불필요한 push를 막기위해서
                   // 다음과 같이 값을 설정해준다.
                   for(int j = toCost; j <= m; j++){
                       if(dp[toNode][j] > toTime) dp[toNode][j] = toTime;
                   }
                   queue.add(new AirPlane(toNode, toCost, toTime));
               }
           }
       }

       int result = Integer.MAX_VALUE;

       for(int i = 1; i <= m; i++)
           result = Math.min(result, dp[n][i]);


       return result;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];
        list = new ArrayList[n + 1];

        for(int i = 0 ; i <= n; i++)
            Arrays.fill(dp[i], INF);

        for(int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0 ; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            list[start].add(new AirPlane(end, cost, time));
        }
    }
}
