import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[] lastYear; // 작년 순위
    static boolean[][] thisYear;
    static int[] indegree;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i<N; i++){
            int a = Integer.parseInt(br.readLine());
            lastYear = new int[a + 1];          // 작년 순위
            thisYear = new boolean[a+1][a+1];   // 올해 순위 저장용 그래프
            indegree = new int[a + 1];          // 그래프의 진입차수 표시

            boolean isPossible = true;          // IMPOSSIBLE판별저장 (사이클)
            boolean isCertain = true;           // 위상정렬의 경우의 수가 하나인지 

            // 작년 순위 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<a; j++){
                lastYear[j+1] = Integer.parseInt(st.nextToken());
                indegree[lastYear[j+1]] = j;
            }
            
            relectLast();

            int b = Integer.parseInt(br.readLine());
            // 바뀐 순위 입력받기
            for(int j = 0 ; j<b; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                change(x,y);           
            }
            
            StringBuilder sb = new StringBuilder();
            find(0); // 진입차수가 0인 노드 큐에 넣기
            for(int j = 1; j<indegree.length; j++){
                if(q.size() == 0){
                    isPossible = false;
                    break;
                }else if(q.size() > 1){
                    isCertain = false;
                    break;
                }else{
                    int current = q.poll();
                    sb.append(current);
                    if(j != indegree.length-1) sb.append(" ");

                    for(int k = 1; k<indegree.length; k++){
                        if(thisYear[current][k]){
                            thisYear[current][k] = false;
                            if(--indegree[k]==0) q.add(k);
                        }
                    }
                }
            }

            if(!isPossible) {
                bw.write("IMPOSSIBLE");
            }else if(!isCertain){
                bw.write("?");
            }else{
                bw.write(sb.toString());
            }

            if(i!=N-1) bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void change(int x, int y) {
        if(thisYear[x][y]){
            thisYear[x][y] = false;
            thisYear[y][x] = true;
            indegree[y] --;
            indegree[x] ++;
        }else{
            thisYear[y][x] = false;
            thisYear[x][y] = true;
            indegree[x] --;
            indegree[y] ++;
        }
    }

    private static void find(int target) {
        for(int i = 1; i<indegree.length; i++){
            if(indegree[i]==target) {
                q.add(i);
            }
        }
    }

    private static void relectLast() { 
        //  작년 순위 반영
        for(int i = 1; i<lastYear.length; i++){
            int up = lastYear[i];

            for(int j = i+1; j<lastYear.length; j++){
                int down = lastYear[j];
                
                thisYear[up][down] = true;
            }
        }
    }
}
