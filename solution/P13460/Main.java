package P13460;

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 세로 10
    static int M; // 가로 10

    // 상, 하, 좌, 우
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};

    static char[][] map;
    static int ans = 11;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P13460/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int rx = -1, ry = -1, bx = -1, by = -1;

        for(int i = 0 ; i<N; i++){ // map 입력받기
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0 ; j< M; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j]=='R'){
                    rx = i;
                    ry = j;
                }else if(map[i][j]=='B'){
                    bx = i;
                    by = j;
                }
            }
        }
        DFS(-1, rx, ry, bx, by, 1);
        if(ans == 11) System.out.println(-1);
        else System.out.println(ans);
        br.close();
    }

    // 이전에 움직인 방향
    // 0상 1하 2좌 3우
    public static void DFS(int prev, int rx, int ry, int bx, int by, int count){

        // 종료조건
        if(count >= ans) return;
        if(count == 11) return;

        int [] rPosition = {rx, ry};
        int [] bPosition = {bx, by};

        for(int i = 0; i<4; i++){
            if(i == prev ) continue;

            if(i==0 && prev != 1){
                if(rx < bx){
                    rPosition = move(rx, ry, i, true); // 빨강먼저 이동
                    bPosition = move(bx, by, i, false);
                }else{
                    bPosition = move(bx, by, i, false); // 파랑먼저 이동
                    rPosition = move(rx, ry, i, true);
                }
            }else if(i==1 && prev != 0){
                if(rx > bx){
                    rPosition = move(rx, ry, i, true); // 빨강먼저 이동
                    bPosition = move(bx, by, i, false);
                }else{
                    bPosition = move(bx, by, i, false); // 파랑먼저 이동
                    rPosition = move(rx, ry, i, true);
                }
            }else if(i==2 && prev!= 3){
                if(ry < by){
                    rPosition = move(rx, ry, i, true); // 빨강먼저 이동
                    bPosition = move(bx, by, i, false);
                }else{
                    bPosition = move(bx, by, i, false); // 파랑먼저 이동
                    rPosition = move(rx, ry, i, true);
                }
            }else if(i==3 && prev != 2){
                if(ry > by){
                    rPosition = move(rx, ry, i, true); // 빨강먼저 이동
                    bPosition = move(bx, by, i, false);
                }else{
                    bPosition = move(bx, by, i, false); // 파랑먼저 이동
                    rPosition = move(rx, ry, i, true);
                }
            }
            
            // 위치변화가 없는 경우
            if(rPosition[0]==rx && rPosition[1]==ry){
                continue;
            } 
            // 파란 구슬이 나온경우
            if(bPosition[0]==-1 && bPosition[1]==-1) {
                map[rx][ry]='R';
                map[bx][by]='B';
                continue;
            }
            // 빨간 구슬이 나온경우
            if(rPosition[0]==-1 && rPosition[1]==-1) {
                ans = Math.min(ans, count);
                return;
            }
            DFS(i, rPosition[0], rPosition[1], bPosition[0], bPosition[1], count+1);
        } 
    }

    // 현재위치를 받아서 방향m으로 끝까지 이동
    public static int[] move(int x, int y, int m, boolean type){
        
        map[x][y] = '.';
        while(true){
            if(x==-1 && y==-1) break;

            x = x+ mx[m];
            y = y+ my[m];

            if(map[x][y]=='.'){
                // 벽에 부딪칠때까지 계속 이동
                continue;
            }else if(map[x][y] =='O'){
                x = -1; // 밖으로 나왔음을 표시
                y = -1;
                break;
            }else{ // 벽, 혹은 다른 구슬에 부딪침 - 한칸 전으로 이동필요
                x = x -mx[m];
                y = y -my[m];
                if(type) map[x][y]='R';
                else map[x][y] = 'B';
                break;
            } 
        }
        int [] position = {x,y};
        return position;
    }
}
