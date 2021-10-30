import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<int[]> candidate = new ArrayList<>();
    static int min = 3000;
    static int area;
        

    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = N * N;
        map = new int[N][N];

        // 입력받기 1- 벽 2-바이러스 후보지 3 - 바이러스
        for(int i=0; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j]==2) candidate.add(new int[] {i,j}); // 후보지는 따로 저장하자
                else if(map[i][j]==1) area--;
            }
        }
        DFS(-1, 0);

        if(min == 3000)  System.out.println(-1);
        else System.out.println(min); 
    }

    private static void DFS(int index, int count) {
        if(count == M){
            // 바이러스를 모두 위치 시켰으면
            // 퍼트려서 확인하자
            int temp = spread();
            if(temp != -1){
                min = Math.min(min, temp);
                return;
            }
        }

        else{
            for(int i = index + 1; i<candidate.size(); i++){
                int[] current = candidate.get(i);
                map[current[0]][current[1]] = -1; // 바이러스
                DFS(i, count+1);
                map[current[0]][current[1]] = 2;
            }
        }
    }

    private static int spread() {
        int[][]temp = new int[N][N];
        Queue<int[]> nextP = new LinkedList<int[]>();
        
        for(int i = 0 ; i<N ; i++){                     // temp로 복사
            for(int j = 0 ; j<N ; j++){
                temp[i][j] = map[i][j];
                if(temp[i][j]==2) temp[i][j] = 0;
                else if(temp[i][j]==-1) {
                    nextP.add(new int[]{i,j,0});
                }
            }
        }

        int turn = 0; // 턴수를 구해야 함
        int count = M; // 바이러스가 퍼진 넓이 세기
        while(!nextP.isEmpty()){                    // 다음 위치를 queue에 저장하며 BFS
            int[] current = nextP.poll();
            int x = current[0];
            int y = current[1];
            int depth = current[2];

            if(depth!=turn) turn ++;

            for(int i = 0 ; i<4; i++){
                int nextX = x + mx[i];
                int nextY = y + my[i];

                if(nextX<0 ||nextX>=N || nextY<0 ||nextY>=N) continue;
                if(temp[nextX][nextY]==0){
                    nextP.add(new int[]{nextX,nextY,depth+1});
                    temp[nextX][nextY] = -1;
                    count++;
                }
            }
        }

        if(count<area) return 3000;
        return turn;
    }
}
