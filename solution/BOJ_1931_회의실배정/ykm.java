package P1931;

import java.io.*;
import java.util.*;

public class Main {
	static int N; // 회의 수
	static int[][] cons;

	public static void main(String[] args)throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cons = new int[N][2];

		// 회의 저장
		int max = 0;
		for(int i = 0 ; i<N ; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cons[i][0] = a;
			cons[i][1] = b;

			max = Math.max(max, b);
		}

		// 끝나는 시간 순서로 정렬
		Arrays.sort(cons, Comparator.comparingInt(a -> a[1]));

		int[] count = new int[max+1];		// index 시간까지 가능한 최대 회의수

		// 시간순서대로 현재 회의를 넣는것이 좋을지 넣지 않는 것이 좋을지 판단
		// 약간 dp..?
		int j = 0;
		for(int i = 0 ; i<= max ; i++){     // 시간의 흐름
			while(j<N && cons[j][1]<=i){
				int start = cons[j][0];
				int end = cons[j][1];
				if( end > i) {
					if(i-1 >= 0) count[i] = count[i-1];
					continue;
				}
				else{
					if(start == end){
						count[i] = count[i-1]+1;
					}
					else if(start-1 >= 0) count[i] = Math.max( count[start] + 1, Math.max(count[i-1],count[i]));
					else {
						if(i-1 >= 0) count[i] = Math.max(1, count[i-1]);
						else count[i] = 1;
					}
					j++;
				}
			}
		}
		System.out.println(count[max]);
	}
}
