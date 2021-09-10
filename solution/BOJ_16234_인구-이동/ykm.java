package P16234; //구현 + dfs + 많은 배열 활용  -> 은서님 코드 참고

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 폭
    static int num; // 실제 나라수
    static int[][] country; // 나라별 인구수
    static boolean[][] visited; // 방문 여부
    static boolean[][] isUnion; // 연합국 표시
    static boolean flag = false;

    // 국경열때 count
    static int max;
    static int min;

    public static void main(String[] arg) throws IOException{
        System.setIn(new FileInputStream("P16234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        num = (int)Math.pow(N,2);
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        country = new int[N+1][N+1];

        for(int i = 1 ; i<=N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=N ; j++){
                country[i][j] = Integer.parseInt(st.nextToken()); // 나라별 인구수
            } 
        }


        int count = 0;
        

        while(true){
            flag = false;                               // 국경을 여는 나라가 있는지 확인
            visited = new boolean[N+1][N+1];            // 방문여부 표시
            
                     
            for(int i = 1 ; i<=N ; i++){
                for(int j = 1; j<=N; j++){
    
                    if(visited[i][j]) continue; // 아직 연합국에 속하지 않으면 
    
                    isUnion = new boolean[N+1][N+1];             // i,j와 연합을 이루는지 확인

                    int [] ans = visit(i,j,0,0);                 //dfs
                    Union(ans[0],ans[1]);
                }
            }
            if(!flag) break;
            count++;
        }

        System.out.println(count);
        br.close();
    }

    // 연합인 나라수와, 인구합을 출력
    static int[] visit(int i, int j, int union, int sum){ //i,j와 연합인 나라수, 연합국 인구합
        int [] ans = {union, sum};             // 리턴용
        int [] mx = {-1,1,0,0}; // 상, 하, 좌, 우
        int [] my = {0,0,-1,1};
        sum += country[i][j];
        union++;
        visited[i][j] = true;
        isUnion[i][j] = true;
        for(int k = 0 ; k<4; k++){

            int nextX = i+mx[k];
            int nextY = j+my[k];

            if(nextX<1 || nextY<1 ||nextX>N ||nextY>N) continue;    // 범위밖


            if(!visited[nextX][nextY] && !isUnion[nextX][nextY]){ // 아직 어느 연합에 포함되지 않은 나라를 대상으로
                int diff = Math.abs(country[i][j] - country[nextX][nextY]);

                if(diff>=min && diff<= max){
                    // (i,j)와 (nextX,nextY)는 같은 연합
                    // sum += country[nextX][nextY];
                    // union++;
                    visited[nextX][nextY] = true;
                    int[] ret = visit(nextX,nextY,union,sum);
                    if(union!=ret[0]){
                        isUnion[nextX][nextY] = true;
                    }
                    union = ret[0];
                    sum = ret[1];
                    flag = true;                    
                }
            }

        }
        
        ans[0] = union;
        ans[1] = sum;
        return ans;
    }

    // 연합국의 인구를 평균으로 바꿔줌
    public static void  Union(int union, int sum){
        if(union == 1) return;
        int avg = sum/union;
        for(int i =1 ; i<=N ; i++){
            for(int j = 1; j<=N ; j++){
                if(isUnion[i][j]){
                    country[i][j]=avg;
                }
            }
        }
    }

}
