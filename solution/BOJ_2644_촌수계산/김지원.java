import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class BOJ2644 {
 
    static int N, a, b, res = -1, dist[];
    static ArrayList<Integer> relation[];
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());    // 전체 사람 수
        dist = new int[N+1];    // 촌수
        relation = new ArrayList[N+1];    // 관계 정보
        for (int i = 1; i <= N; i++) 
            relation[i] = new ArrayList<Integer>();
        Arrays.fill(dist, -1);
        
        st = new StringTokenizer(br.readLine(), " ");
        // a, b 의 촌수를 구해야 함
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        int M = Integer.parseInt(br.readLine());    // 관계의 개수
        
        // 관계 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int ch = Integer.parseInt(st.nextToken());
            relation[p].add(ch);
            relation[ch].add(p);
        }
        
        Queue<Integer> q = new LinkedList<Integer>();
        // a와 b의 촌수를 찾아야 하므로, a부터 출발
        dist[a] = 0;
        q.add(a);
        while(!q.isEmpty()) {
            // 확인할 사람을 queue에서 빼고
            int now = q.poll();
            // 비교 대상을 찾으면 촌수(결과값) 저장
            if(now == b) {
                res = dist[now];
                break;
            }
            // 해당 사람과 관계있는 사람들을 확인
            for (int i = 0; i < relation[now].size(); i++) {
                int tmp = relation[now].get(i);
                // 이미 촌수를 확인한 사람이면 pass
                if(dist[tmp] != -1) continue;
                // 다음 관계는 현재까지 촌수 + 1
                dist[tmp] = dist[now] + 1;
                q.add(tmp);
            }
        }
        
        System.out.println(res);
    }
        
}
