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
		for(int i = 0 ; i<N ; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cons[i][0] = a;
			cons[i][1] = b;
		}

		Comparator<int[]> comp = new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] - o2[1] !=0) return o1[1] - o2[1];
				else return o1[0] - o2[0]; 
			}
			
		};

		Arrays.sort(cons,comp); // 끝나는 시간 기준으로 정렬

		int prev = 0;
		int answer = 0;
		for(int i = 0 ; i<cons.length; i++){
			if(cons[i][0]>= prev){
				answer++;
				prev = cons[i][1];
			}
		}

		System.out.println(answer);
	}
}
