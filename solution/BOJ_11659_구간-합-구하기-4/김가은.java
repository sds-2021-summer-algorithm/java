package P11659;

import java.io.*;
import java.util.*;


public class Main {
	
	static int N, M;
	static int[] nums;
	static int a, b;
	

	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        nums = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	nums[i] = nums[i-1] + Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(nums[b] - nums[a-1]);
        }


		bw.flush();
		bw.close();
		br.close();
        
	}
	

}

