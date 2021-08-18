import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] dp, mem, cost;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        mem = new int[N];
        cost = new int[N];
        int total=0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        	mem[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	cost[i]= Integer.parseInt(st.nextToken());
        	total += cost[i];
        }
        
        dp = new int[total+1]; // dp[i] : i 의 cost로 확보할 수 있는 최대 메모리
        for (int i = 0; i < N; i++)
			for (int j = total; j >= cost[i]; j--) // 최대 cost에서부터 1씩 내려오며
				dp[j] = Math.max(dp[j], dp[j-cost[i]] + mem[i]); // i번째 app의 실행여부에 따라 최댓값 갱신
        
        for (int i = 0; i <= total; i++)
			if (dp[i]>=M){
				bw.write(i+"\n");
				break;
			}
        
        bw.flush();
        bw.close();
        br.close();
    }
}
