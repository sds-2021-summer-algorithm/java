package P14502;

import java.io.*;
import java.util.*;

public class Main{

    static int N;
    static int M;
    static int[][] map; // 초기 벽과 바이러스 위치 파악 -> 1 : 벽 ,2: 바이러스 
    static boolean [][] visit; // 방문여부 확인 -> 퍼져나가는 바이러스 표현

    static int wallCount = 0;
    static int virusCount = 0;
    static int remain = 0;
    public static void main(String[] args)throws IOException{
        System.setIn(new FileInputStream("P14502/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];

        for(int i = 1; i<=N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) wallCount++;
            }
        }

        wall(0,1,1);

        System.out.println(remain);
    }

    public static void wall(int count, int prevX, int prevY){
        if(count>3) return;
        
        // 바이러스가 퍼지는 모습 확인
        if(count==3){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=M ; j++){
                    visit[i][j]=false;
                    virusCount = 0;
                }
            }
            for(int i = 1; i<=N; i++){
                for(int j =1; j<=M ; j++){
                    if(map[i][j]==2 && !visit[i][j]){
                        visit[i][j]=true;
                        virusCount++;
                        spread(i,j);
                    }
                }
            }
            remain = Math.max(remain, N*M - virusCount - wallCount -3);
            return;
        }

        // 벽 3개를 추가로 세우기
        for(int i = prevX; i <=N; i++){
            for(int j =(i==prevX?prevY:1); j<=M; j++){
                if(map[i][j]==0){
                    map[i][j]=1;
                    count++;
                    wall(count,i,j);
                    map[i][j]=0;
                    count--;
                }
            }
        }
    }

    public static void spread(int x, int y){

        if(N*M - virusCount - wallCount -3 == 0) return; // 바이러스가 모든 곳으로 퍼짐

        int[] mx = {-1, 1, 0, 0};
        int[] my = {0, 0, -1, 1};

        for(int i = 0 ; i<4; i++){
            int nextX = x + mx[i];
            int nextY = y + my[i];

            if(nextX>0 && nextY>0 &&nextX<=N && nextY<=M){
                if(!visit[nextX][nextY] && map[nextX][nextY]==0){
                    visit[nextX][nextY] =true;
                    virusCount++;
                    spread(nextX, nextY);
                }
            }
        }
    }

}