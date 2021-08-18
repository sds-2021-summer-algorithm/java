package P2042;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, K;
	static long[] nums;
	static long[] tree;
	static int S;
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        nums = new long[N + 1];
        

        for (int i = 0; i < N; i++) {
        	nums[i] = Long.parseLong(br.readLine());
        }
        
        S = 1; //S는 leaf 의 시작
        while (S < N) {
        	S *= 2; // N이 5개라도 이진트리니까 8개가 필요함.
        }
        tree = new long[S * 2];
        
        init();
        
        

        for (int i = 0; i < M+K; i ++) {
            st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
      	
        	
        	if (a == 1) { // 변경을 하고 싶다면
            	int b = Integer.parseInt(st.nextToken());
            	long c = Long.parseLong(st.nextToken()); // a == 1인 경우 b번째 자리를 c로 바꾸라는 건데 c의 범위가 int 를 벗어날 수도 있음 (100%에서 Numberformat 에러 발생)
        		update(b, c);
        		
        	}
        	else if (a ==2) { // 구간합을 구하고 싶으면
            	int b = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
        		System.out.println(query(b,c));

        	}
        }
        

		bw.flush();
		bw.close();
		br.close();
        	

	}
	
	static void init() {
		//leaf에 값을 반영
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i];
		}
		//내부 노드 채움
		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree [i * 2] + tree[i * 2 + 1];
		}
	}
	
	// left, right : 실제 노드 번호
	//queryleft, queryright : 구간 합을 구하고 싶은 범위
	static long query(int queryleft, int queryright) {
		int left = S + queryleft - 1;
		int right = S + queryright - 1;
		long sum = 0;
		while (left <= right) {
			// 좌측 노드가 홀수이면 현재 노드 값 사용하고 한칸 옆으로
			if (left %2 == 1) {
				sum += tree[left++];
			}
			//우측 노드가 짝수이면 현재 노드 값 사용하고 한칸 옆으로
			if (right %2 == 0) {
				sum += tree[right--];
			}
			//좌측. 우측 노드 부모로 이동
			left /= 2;
			right /= 2;
		}
		return sum;
		
	}
	
	
	static void update(int target, long value) {
		int node = S + target - 1; // 3번째를 바꾸고 싶으면 리프시작노드 + 3 - 1;
		// 값을 변경
		tree[node] = value;
		//root 에 도달할때까지 값을 변경(Bottom up)
		node /= 2; // 부모노드로
		while (node > 0) {
			tree[node] = tree[node*2] + tree[node*2 + 1];
			node /=2;
		}
		
	}
	
}