import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static int[] dx = {-1,0,1,0}; //왼쪽,오른쪽,위,아래
    static int[] dy = {0,1,0,-1};

    static boolean[][][][] visit; //방문기록
    static char[][] map;

    static int holeX, holeY;
    static Ball blue,red;

    static class Ball{ //공공공공
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        Ball(int rx, int ry, int bx, int by, int cnt){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M][N][M];

        for (int i=0; i< N; i++){ //맵 저장
            String str= br.readLine();
            for(int j=0; j< M; j++){
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
                }
                else if (map[i][j] == 'B'){ //공만들어주기
                    blue = new Ball(0,0,i,j,0);
                }
                else if (map[i][j] == 'R'){ //공만들어주기
                    red = new Ball(i,j,0,0,0);
                }
            }
        }

        bw.write( Integer.toString(bfs()) ); //BFS 돌려주기
        bw.flush();
        bw.close();
        br.close();

    }

    public static int bfs(){
        Queue<Ball> queue = new LinkedList<>();
        queue.add(new Ball(red.rx, red.ry,blue.bx,blue.by,1)); //모든 정보를 가진 공을 queue에 넣어줌
        visit[red.rx][red.ry][blue.rx][blue.ry] = true;

        while(!queue.isEmpty()){ //queue가 빌때까지 계속함
            Ball ball = queue.poll();

            int curRx = ball.rx;
            int curRy = ball.ry;
            int curBx = ball.bx;
            int curBy = ball.by;
            int curCnt = ball.cnt;

            if (curCnt > 10){
                return -1;
            }

            for (int i=0; i < 4; i++){ //4번하는건 동서남북!
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while(map[newRx + dx[i]][newRy + dy[i]] != '#'){ //계속 굴려준다.
                    newRx += dx[i];
                    newRy += dy[i];

                    if(newRx == holeX && newRy == holeY){//빨간공이 들어가면 바로 종료
                        isRedHole = true;
                        break;
                    }
                }

                while(map[newBx + dx[i]][newBy + dy[i]] != '#') { //파란공도 굴려준다.
                    newBx += dx[i];
                    newBy += dy[i];

                    if(newBx == holeX && newBy == holeY) { // 파란공이 들어가면 똑같이 true해주고 종료
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole){ //어떤 경우든 파란공이 들어가면, 다음 bfs를 본다
                    continue;
                }

                if(isRedHole && !isBlueHole){ //빨간공만 들어가고, 파란공은 안들어가면 종료!!!
                    return curCnt;
                }

                if(newRx == newBx && newRy == newBy){ //공의 위치가 같은 경우
                    if(i==0){
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }
                    else if(i==1){
                        if(curRy < curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                    else if(i==2){
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }
                    else if(i==3){
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                if(!visit[newRx][newRy][newBx][newBy]){ //*** 두 구슬이 이동할 위치가 처음 방문하는 경우에만 이동!! -> 큐에 추가
                    visit[newRx][newRy][newBx][newBy] = true;
                    queue.add(new Ball(newRx, newRy, newBx, newBy, curCnt+1));
                }
            }
        }
        return -1;
    }
}
