import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<int[]> candidate = new ArrayList<>();
    static int min = 3000;
    static int area = 0;

    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 입력받기 1- 벽 2-바이러스 후보지 3 - 바이러스
        for(int i=0; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) area ++;                           // 바이러스가 퍼질수 있는 넓이
                else if(map[i][j]==2) candidate.add(new int[] {i,j}); // 후보지는 따로 저장하자
                
            }
        }
        DFS(-1, 0);

        if(min == 3000)  System.out.println(-1);
        else System.out.println(min); 
    }

    private static void DFS(int index, int count) {
        if(count == 3){
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
                map[current[0]][current[1]] = 3; // 바이러스
                DFS(i, count+1);
                map[current[0]][current[1]] = 2;
            }
        }
    }

    private static int spread() {
        int[][]temp = new int[N][M];
        int[][] combi = new int[3][2];
        int index = 0;
        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<M ; j++){
                temp[i][j] = map[i][j];
                if(temp[i][j]==2) temp[i][j] = 0;
                else if(temp[i][j]==3) {
                    combi[index][0] = i;
                    combi[index++][1] = j;
                }
            }
        }

        // 턴수를 구해야 함
        for(int i = 0 ; i<3 ; i++){
            int x = combi[i][0];
            int y = combi[i][1];
        }
        return 0;
    }
}
