package P1753;
// 최단경로 -> 다익스트라?
// priorityqueue을 도입하여 시간개선

import java.io.*;
import java.util.*;

public class Main {
	static int V; // 정점의 수. 최대 2만
	static int E; // 간선의 수. 최대 30만
	static int start; // 시작정점
	static ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>> ();
	
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	static StringBuilder sb = new StringBuilder();
	static int[] result;
	static boolean[] check;
	static int INF = 10000000; // integer.MaxValue가 아니라도 충분히 큼
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("P1753/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		result = new int[V+1];
		check = new boolean[V+1];
		for(int i = 0; i<=V; i ++) {
			result[i] = INF;
			check[i] = false;
			edges.add(new ArrayList<Edge>());
			// 사용전 환경세팅
		}
		// edge 입력받기
		for(int i = 1 ; i<=E ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
					
			Edge n = new Edge(b,c);
			edges.get(a).add(n);
		}
		// 다익스트라
		Dij();
		
		for(int i = 1 ; i<=V ; i++) {
			if(result[i]==INF) sb.append("INF\n");
			else sb.append(result[i]+"\n");
		}
		System.out.println(sb);
		br.close();
	}
	public static void Dij() {
		// 가지치기의 중요성 기억하기!!
		// 1. 도착지에 온경우 break
		// 2. 더큰 가중치로 도착한 경우 continue;
		// 3. 인접 리스트 확인, 최소값 갱신, pq에 넣기

		result[start] = 0; // 시작정점 초기화
		check[start]=true;
		pq.add(new Edge(start,0));

		while(!pq.isEmpty()) { // 종료조건 포함.

			Edge current = pq.poll(); // pq에 저장된 node중 cost가 가장 작은것
			
			// 절대 업데이트가 안되는 경우 가지치기
			if(current.cost > result[current.to]) continue;
			check[current.to] = true;
			
			// current와 연결된 간선 탐색
			// 기존경로와 current경로를 거치는 경로를 비교하자
			for(int i = 0; i <edges.get(current.to).size(); i++) {
				// current를 지나 도착한 node와 연결된 edges들
				// update가 되면 pq에추가
				Edge next = edges.get(current.to).get(i);
				if(result[next.to] > current.cost + next.cost) {
					result[next.to] = current.cost + next.cost;
					pq.add(new Edge(next.to ,result[next.to]));
				}
			}
		}
	}
	
	public static class Edge implements Comparable<Edge>{
		// 시작위치는 start로 고정이라 표시할 필요 없음.
		int to;
		int cost;
		
		public Edge(int to, int weight) {

			this.to = to;
			this.cost = weight;
		}
		
		@Override
		public int compareTo(Edge other) {
			return this.cost - other.cost; // 오름차순
		}
	}
}
