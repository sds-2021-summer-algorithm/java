import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    static int V,E;
    static ArrayList<ArrayList<Integer>> line;
    static int order = 1; // 방문순서 1부터 시작
    static int[] discover;
    static boolean[] isCutVertax; // 단절점 여부
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken()); //정점 갯수
        E = Integer.parseInt(st.nextToken()); //간선 갯수

        line = new ArrayList<>();
        
        for (int i=0; i <= V; i++){
            line.add(new ArrayList<>()); //정점 추가해주기
        }
        
        for (int i=0; i< E; i++){ // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            line.get(A).add(B);
            line.get(B).add(A); // 양방향 간선
        }

        discover = new int[V+1]; //방문여부 파악
        isCutVertax = new boolean[V+1];

        for (int i=1; i<= V; i++){
            if(discover[i] == 0) { //아직 방문하지 않았다면, dfs로 돌려주기
                dfs(i,true,line); //dfs 실행 --> root
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i=1; i <= V; i++){
            if(isCutVertax[i]){ //단절점이면
                cnt++; //카운트 추가
            }
        }

        sb.append(cnt+"\n");

        for (int i=1; i<=V; i++){
            if(isCutVertax[i]){ //단절점이면
                sb.append(i+ " "); //단절점 번호 추가
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dfs(int vertex, boolean isRoot, ArrayList<ArrayList<Integer>> line){
        discover[vertex] = order++; //방문순서 추가해주기
        int nums = discover[vertex]; //현재 점의 방문순서 저장
        int child = 0; //child 갯수

        for (int now : line.get(vertex)){ //선택한 점의 모든 연결점들 확인
            if (discover[now] == 0){ //자식 정점이 방문되지 않았을때
                child++;

                //자식 정점 중 방문수서가 가장 빠른값
                int low = dfs(now,false,line);

                //루트 정점이 아님 && 가장 작은 방문순서가 vertex의 방문순서보다 크거나 같으면
                if(!isRoot && low >= discover[vertex]){
                    isCutVertax[vertex] = true; // 단절점임
                }
                nums = Math.min(nums, low);
            }
            else{ //자식정점이 방문되었을때
                nums = Math.min(nums, discover[now]);
            }


        }

        //루트정점 && 자식수가 2개 이상이면 단절점임
        if (isRoot && child >=2){
            isCutVertax[vertex] = true;
        }

        return nums;
    }
}
