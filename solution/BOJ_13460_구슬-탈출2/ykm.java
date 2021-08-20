package P13460;
// 지도와 공을 따로 구분하여 생각하기 -> 레이어를 두개 사용한다고 생각하면됨.
// 공의 위치가 겹치는 경우를 처리해줄 필요가 있음.

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 세로 10
    static int M; // 가로 10

    // 상, 하, 좌, 우
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};

    static char[][] map;
    static boolean[][][][] check;
    static Queue<ball> q = new LinkedList<>();
    static int ans = 11;

    public static class ball{
        int rx;
        int ry;
        int bx;
        int by;
        int count;

        public ball(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }

        @Override
        public String toString() {
            return "ball [bx=" + bx + ", by=" + by + ", count=" + count + ", rx=" + rx + ", ry=" + ry + "]";
        }        
    }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P13460/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new boolean[N][M][N][M];

        int rx = -1, ry = -1, bx = -1, by = -1;
        for(int i = 0 ; i<N; i++){ // map 입력받기
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0 ; j< M; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j]=='R'){
                    map[i][j] = '.';
                    rx = i;
                    ry = j;
                }else if(map[i][j]=='B'){
                    map[i][j] = '.';
                    bx = i;
                    by = j;
                }
            }
        }

        check[rx][ry][bx][by] = true;
        q.offer(new ball(rx, ry, bx, by, 0));
        BFS();

        if(ans==11) System.out.println(-1);
        else System.out.println(ans+1);
        br.close();
    }

    public static void BFS(){
        // 1. 큐에서 꺼내옴
        // 2. 목적지 인가
        // 3. 갈수있는곳 순회
        // 4. 조건 확인
        // 5. 체크인
        // 6. 큐에 넣음
        while(!q.isEmpty()){
            ball current = q.poll();    
            if(current.count>=10) return;
            if(current.count>=ans) return;
    
            for(int i = 0 ; i<4 ; i++){ // 상,하,좌,우
                int nextRX = current.rx;
                int nextRY = current.ry;
                int nextBX = current.bx;
                int nextBY = current.by;
                int count = current.count;

                boolean red = false, blue = false; // 구멍으로 빠졌는지 확인
    
                //이동 후 확인(코드가 길지만 빠른듯)vs 확인 후 이동(코드가 깔끔하지만 약간 느림)
                while(map[nextRX + mx[i]][nextRY + my[i]] != '#'){ // i방향으로 빨간구슬 이동 
                    nextRX = nextRX + mx[i];
                    nextRY = nextRY + my[i];
    
                    if(map[nextRX][nextRY]=='O'){
                        red = true;
                        break;
                    }
                }
    
                while(map[nextBX + mx[i]][nextBY + my[i]] != '#'){ // i방향으로 파란구슬 이동
                    nextBX = nextBX + mx[i];
                    nextBY = nextBY + my[i];
    
                    if(map[nextBX][nextBY]=='O'){ // 구멍에 빠짐
                        blue = true;
                        break;
                    }
                }
                
                // 두 구슬의 위치가 같아진 경우
                if(nextRX == nextBX && nextRY == nextBY){
                    switch(i){
                        case 0: // 상
                            if(current.rx < current.bx) nextBX = nextBX - mx[i];
                                else nextRX = nextRX - mx[i];
                            break;
                        case 1:// 하
                            if(current.rx > current.bx)  nextBX = nextBX - mx[i];
                            else nextRX = nextRX - mx[i];
                            break;
                        case 2: // 좌
                            if(current.ry < current.by) nextBY = nextBY - my[i];
                            else nextRY = nextRY - my[i];
                            break;
                        case 3: // 우
                            if(current.ry > current.by) nextBY = nextBY - my[i];
                            else nextRY = nextRY - my[i];
                            break;
                    }
                }
    
                if(blue) continue;
                if(red) {
                    ans = Math.min(count, ans);
                    break;
                }
    
                if(check[nextRX][nextRY][nextBX][nextBY]) continue; 
                check[nextRX][nextRY][nextBX][nextBY] = true; // 체크인
                q.offer(new ball(nextRX, nextRY, nextBX, nextBY, count +1)); // 큐에 넣기
            }
        }
    }
}
