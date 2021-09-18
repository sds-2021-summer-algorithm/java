package P14502;

import java.io.*;
import java.util.*;

public class Main{

    static int N;
    static int M;
    static int[][] map; // 초기 벽과 바이러스 위치 파악 -> 1 : 벽 ,2: 바이러스, 3: 퍼져나간 바이러스 
    static ArrayList<int[]> virusPosition = new ArrayList<>(); // 초기 바이러스의 위치 저장

    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};

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
            virusCount = virusPosition.size();

            // 초기 바이러스 위치 확인
            if(virusPosition.size()==0){ 
                for(int i = 1; i<=N; i++){
                    for(int j =1; j<=M ; j++){
                        if(map[i][j]==2){
                            virusPosition.add(new int[] {i,j});
                            virusCount ++;
                        }
                    }
                }
            }
            // 바이러스 퍼뜨리기
            for(int i = 0 ; i<virusPosition.size(); i++){
                int []position = virusPosition.get(i);
                spread(position[0],position[1]);
            }

            // 바이러스가 퍼지기 전으로 초기화
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=M ; j++){
                    if(map[i][j]==3) map[i][j]=0;
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
                    wall(count+1,i,j);
                    map[i][j]=0;
                }
            }
        }
    }

    public static void spread(int x, int y){

        if(N*M - virusCount - wallCount -3 == 0) return; // 바이러스가 모든 곳으로 퍼짐

        for(int i = 0 ; i<4; i++){
            int nextX = x + mx[i];
            int nextY = y + my[i];

            if(nextX>0 && nextY>0 &&nextX<=N && nextY<=M){
                if(map[nextX][nextY]==0){
                    map[nextX][nextY] =3;
                    virusCount++;
                    spread(nextX, nextY);
                }
            }
        }
    }
}