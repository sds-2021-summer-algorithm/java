package P1931;

import java.io.*;
import java.util.*;

public class Main {

	static class conference implements Comparable<conference>{
		int start;
		int end;
		int time;
		boolean dup = false;

		public conference(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}

		@Override
		public String toString() {
			return "conference [end=" + end + ", start=" + start + ", time=" + time + "]";
		}	

		@Override
		public int compareTo(conference o){
			return this.time - o.time;
		}
	}
	static int N; // 회의 수
	static ArrayList<conference> cons;

	
	public static void main(String[] args)throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cons = new ArrayList<conference>();

		// 회의 저장
		int max = 0;
		for(int i = 0 ; i<N ; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = b-a;
			cons.add(new conference(a, b, c));

			max = Math.max(max, b);
		}

		// 회의 진행시간이 짧은 순서로 정렬
		Collections.sort(cons);

		// 겹치는 회의 확인
		conference prev = cons.get(0);
		for(int i = 1 ; i< cons.size(); i++){
			conference current = cons.get(i);
			if(prev.start==current.start && prev.end == current.end){
				current.dup = true;
			}else{
				prev = cons.get(i);
			}
		}

		boolean[] check = new boolean[max+1];
		int ans = 0;

		// 회의시간이 짧은 회의부터 예약하기
		for(int i = 0 ; i<N ; i++){
			conference current = cons.get(i);
			if(current.dup) continue;
			boolean flag = true; // 현재 회의가 예약 가능한지 확인

			for(int j = current.start+1 ; j<current.end; j++){
				if(check[j]) {
					flag = false;
					break;
				}
			}

			if(flag){
				for(int j = current.start ; j<=current.end; j++){
					check[j] = true;
				}	
				ans++;
			}
		}
		System.out.println(ans);
	}

}
