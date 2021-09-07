package P2667; // 단지내 건물수에 따라 오름차순 출력 주의

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] home; // 입력받을 공간
	static boolean [][] visit; // 번호를 이미 붙였는지 확인
	static int complex = 1;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("P2667/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		visit = new boolean[N][N];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i< N; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for(int j = 0 ; j <N ; j ++) {
				home[i][j] = line.charAt(j)-'0';
				visit[i][j] = false;
			}
		}

		for(int i = 0 ; i< N; i ++) {
			for(int j = 0 ; j <N ; j ++) {
				if(home[i][j]!=0 && !visit[i][j]){
					int count = number(i,j,1,complex);
					list.add(count);
				}
			}
		}
		list.sort(null);
		bw.write(complex-1+"\n");
		for(int i = 0; i<complex-1; i++){
			bw.write(list.get(i)+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// 방문한적 없는 건물
	// 너비우선 탐색 BFS -> queue
	// 깊이우선으로 바꿔보기!!
	public static int number(int x, int y,int count, int num){
		int[] mx = {-1, 1, 0, 0};
		int[] my = {0, 0, -1, 1};
		Queue<int[]> q = new LinkedList<int[]>();
		int [] position = {x,y};
		q.offer(position);
		int max = num;

		while(!q.isEmpty()){
			int[] current = q.poll();

			home[current[0]][current[1]] = num;
			visit[current[0]][current[1]] = true;
			
			for(int i = 0 ; i<4; i++){
				int[] next = {current[0]+mx[i], current[1]+my[i]};

				if(next[0]>=0 && next[0]<N && next[1]>=0 && next[1]<N){
					if(home[next[0]][next[1]]!=0){
						if(current[0]==next[0] && current[1]==next[1]) continue;
						if(!visit[next[0]][next[1]]){ // 방문전
							count = number(next[0],next[1],count+1,num);
						}
					}
				}
			}
			if(max == complex) complex++;
		}
		return count;
	}
}
