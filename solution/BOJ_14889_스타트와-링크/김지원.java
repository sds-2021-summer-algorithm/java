import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[] visit;
    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0,0);

        System.out.println(Min);
        br.close();
    }

    public static void backtracking(int idx, int count){
        if (count == N/2){
            //각각 팀에 N/2만큼 팀원이 들어가면 두 팀의 전력차이를 분석한다.
            find_diff();
            return;
        }

        //visit한팀이 스타트 / false인 팀이 링크 팀
        for(int i =idx; i<N; i++){
            if(!visit[i]){
                visit[i] = true;
                backtracking(i+1,count+1);
                visit[i] = false; //재귀가 끝나면 다시 false로 해줘야됨!!!!!!!!
            }
        }
    }

    //숙련도 차이 계산
    private static void find_diff() {
        int team_start = 0;
        int team_link = 0;

        for(int i =0; i< N-1; i++){
            for (int j=i+1; j< N; j++){
                if(visit[i] == true && visit[j] == true){
                    team_start += map[i][j];
                    team_start += map[j][i];
                }
                else if(visit[i] == false && visit[j] == false){
                    team_link += map[i][j];
                    team_link += map[j][i];
                }
            }
        }

        int val = Math.abs(team_start - team_link);

        //그냥 다돌아도 되는데 0일때는 강제종료해주기
        if(val == 0){
            System.out.println(val);
            System.exit(0);
        }

        Min = Math.min(val, Min);
    }
}
