import java.io.*;
import java.util.*;

public class Main {

	static int N, S, cnt;
	static int[] arr;
	static boolean[] pick;

    public static void main(String[] args) throws Exception {
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken()); 
        // 여기까지 입력
        
        cnt = 0;
        pick = new boolean[N];
        for (int i = 1; i <= N; i++) // 1부터 N개까지 고르는 경우의 수
			combi(0, i);
        bw.write(cnt+"\n"); // cnt 출력
        
        bw.flush();
        bw.close();
        br.close();
    }
    static void combi(int start, int remain) {
    	if(remain==0) { // 다 골랐는데
    		int sum=0;
    		for (int i = 0; i < N; i++) {
				if(pick[i])
					sum += arr[i];
			}
    		if (sum==S) // 그 합이 S랑 같으면
    			cnt++; // cnt 추가
    		return;
    	}
    	
    	for (int i = start; i <= N-remain; i++) {
			pick[i] = true;
			combi(i+1, remain-1);
			pick[i] = false;
		}
    }
}
