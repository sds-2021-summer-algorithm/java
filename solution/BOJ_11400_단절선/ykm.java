package P11400; // 단절선 -> 단절점이 되는 원인을 찾으면 될듯?
import java.io.*;
import java.util.*;

public class Main {
    static int V; // 최대 십만
    static int E; // 최대 백만

    static int o = 1; // 방문 순서 세기
    static int[] visit; // 방문순서 기록용

    static ArrayList<Integer>[] Edge; // 인접리스트
    static ArrayList<edge> ans; // 단절선 저장용
    
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P11400/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Edge = new ArrayList[V+1];
        ans = new ArrayList<>();
        visit = new int[V+1];

        for(int i = 1 ; i<=V ; i++) {
            Edge[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i<E; i++){
            // 간선 입력받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Edge[a].add(b);
            Edge[b].add(a);
        }

        // 모든 노드가 연결 - 문제 조건
        DFS(1, 0);

        // 출력 준비
        bw.write(ans.size()+"\n");
        Collections.sort(ans);
        for(int i = 0 ; i<ans.size(); i++){
            bw.write(ans.get(i)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int DFS(int current, int parent){
        // 1. 체크인
        // 2. 목적지 확인
        // 3. 방문 할 수 있는곳
        // 4. 조건확인
        // 5. 방문
        // 6. 체크아웃
        
        int order = o;
        int low = o; // 현재 방문 순서로 초기화
        visit[current] = order;
        o++;

        for(int i = 0 ; i< Edge[current].size(); i++){
            int next = Edge[current].get(i); // 다음으로 방문할 노드

            if(next != parent){
                if(visit[next]==0){
                    int ret = DFS(next, current);
                    low = Math.min(low, ret);
                    if(ret > order){
                        ans.add(new edge(next, current));
                    }

                }else{
                    low = Math.min(low, visit[next]);
                }
            }
        }
        return low;
    }

    public static class edge implements Comparable<edge>{
        int a;
        int b;

        public edge(int a, int b) {
            // a가 작도록 유지
            if(a>b){
                this.a = b;
                this.b = a;
            }else{
                this.a = a;
                this.b = b;
            }
        }

        @Override
        public String toString() {
            return this.a+" "+this.b;
        }

        @Override
        public int compareTo(edge o) { // 정렬용
            if(this.a != o.a) return this.a - o.a;
            else return this.b - o.b;
        }       
    }
}
