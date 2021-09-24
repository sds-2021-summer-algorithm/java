package P17144;

import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int T; // t초후 미세먼지의 양을 구해야함.
    static int[][]map;
    static int[][]dust; // 동시에 먼지가 퍼지는 것을 구현 하기 위해
    static int airPurifier;
    static int sum = 0;

    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};

    public static void main(String[] args)throws IOException{
        System.setIn(new FileInputStream("P17144/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dust = new int[R][C];
        for(int i = 0 ; i<R ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<C ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1) airPurifier = i;      // 공기청정기의 아래열의 값이 저장됨.
                else sum += map[i][j];                  // 전체 미세먼지 양 - 남은 미세먼지양 계산시 사용
            }
        }

        while(T-->0){
            spread();
            circle();
        }

        System.out.println(sum);
        br.close();
    }

    private static void spread() {
        // 사방으로 퍼질 먼지양 구하기
        for(int i = 0 ; i<R ; i++){
            for(int j = 0 ; j<C ; j++){
                if(map[i][j]==0) continue;
                int portion = map[i][j]/5;
                if(portion == 0) continue;
                
                for(int k = 0 ; k <4; k++){
                    int nextX = i+mx[k];
                    int nextY = j+my[k];

                    // 벽으로는 퍼지지 않음
                    if(nextX>=0 && nextX<R && nextY>=0 && nextY<C){
                        // 공기청정기 방향으로는 퍼지지 않음.
                        if(map[nextX][nextY]==-1) continue;

                        map[i][j] -= portion;
                        dust[nextX][nextY] += portion;
                    }
                }
            }
        }
        // 칸별 먼지양 합치기
        for(int i = 0 ; i<R ; i++){
            for(int j = 0 ; j<C ; j++){
                map[i][j]+=dust[i][j];
            }
        }
        // 초기화
        dust = new int[R][C];
    }

    private static void circle(){

        // 공기청정기 위치
        int up = airPurifier-1;
        int down = airPurifier;

        // 반시계 방향 회전
        sum -= map[up-1][0];
        for(int i = up-1 ; i>0; i--){
            map[i][0] = map[i-1][0];
        }
        for(int j = 0; j<C-1 ; j++){
            map[0][j] = map[0][j+1]; 
        }
        for(int i = 0; i<up; i++){
            map[i][C-1] = map[i+1][C-1];
        }
        for(int j = C-1; j>0 ; j--){
            map[up][j] = map[up][j-1];
        }
        map[up][1] = 0;

        // 시계 방향 회전
        sum -= map[down+1][0];
        for(int i = down+1 ; i<R-1; i++){
            map[i][0] = map[i+1][0];
        }
        for(int j = 0; j<C-1 ; j++){
            map[R-1][j] = map[R-1][j+1]; 
        }
        for(int i = R-1; i>down; i--){
            map[i][C-1] = map[i-1][C-1];
        }
        for(int j = C-1; j>0 ; j--){
            map[down][j] = map[down][j-1];
        }
        map[down][1] = 0;
    }
}
