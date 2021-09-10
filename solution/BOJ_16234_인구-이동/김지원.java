import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int N,L,R;
    static int[][] map;
    static int result = 0;
    static boolean[][] visit;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

//    개요
//    1. 데이터 입력받기
//    2. 연합국을 모두 계산하기 - BFS
//    3. 인구를 모두 이동시키기 -> 계속해서 반복

    public static void main(String[] args) throws IOException {
        //1. 데이터 입력받기!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());


        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean isOK = false;
            visit = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!visit[i][j]){
                        if(bfs(i,j)){ //인구이동이 있으면
                            isOK = true;
                        }
                    }
                }
            }

            if(!isOK){ //만약 false라면 -> 인구 이동 X
                break;
            }
            else{ //인구 이동이 한번이라도 있다면
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean bfs(int x, int y) {
        boolean ismove = false; //false면 단 한번도 이동이 되지 않은 것

        ArrayList<Point> union = new ArrayList<>();
        union.add(new Point(x,y)); //처음 연합국에 넣어주기
        int count = 1; //연합나라 카운팅
        int sum = map[x][y]; //연합 나라 숫자 더하기
        
        //BFS를 위한 Queue
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));
        visit[x][y] = true; //초기값 세팅 및 root 값 넣어주기

        while(!queue.isEmpty()){
            int curX = queue.peek().x;
            int curY = queue.poll().y;

            for(int i=0; i<4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N){ //범위 벗어남
                    continue;
                }
                if(visit[nx][ny]){ //이미 방문한 곳이면 패스
                    continue;
                }
                // 두 나라의 인구 차이가 L명 이상, R명 이하라면 연합 가능
                int val = Math.abs(map[curX][curY] - map[nx][ny]);

                if(val >= L && val <= R && !visit[nx][ny]) {
                    union.add(new Point(nx, ny));
                    count++;
                    sum += map[nx][ny];

                    visit[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }

        }

        if(union.size() > 1){ //연합이 1개라도 이루어진다면?
            ismove = true;
            int change = sum / count; //바뀌는 값 계산
            for(int i =0; i<union.size(); i++){
                Point p = union.get(i); //인구수 바뀐 값으로 업데이트
                map[p.x][p.y] = change;
            }
        }

        return ismove;
    }
}
