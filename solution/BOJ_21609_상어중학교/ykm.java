package P21609; 
// 상어 중학교
import java.io.*;
import java.util.*;

public class Main {
    static int N; // 판의 폭
    static int M; // 색의 종류

    static int[][] board;
    static int answer = 0 ;
    static final int None = 10;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P21609/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){ 
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean flag = true;
        while(flag){
            flag = choose();
        }

        System.out.println(answer);
        br.close();
    }

    // 현재 보드상황에서 최적의 선택을 해야함.
    // 블록의 개수는 2개 이상
    // 일반 블록이 적어도 하나
    // 기준 블록은 무지개 블록이 아닌 블록 중에서 행, 열의 크기가 작은 블록
    public static boolean choose(){ // O(N^2)
        class position implements Comparable<position>{
            int x, y;
            int color;
            int totalBlock;
            int rainbow;

            public position(int x, int y, int color, int block, int rainbow) {
                this.x = x;
                this.y = y;
                this.color = color;
                this.totalBlock = block;
                this.rainbow = rainbow;
            }

            @Override
            public int compareTo(position o) {
                if(this.totalBlock == o.totalBlock){
                    if(this.rainbow == o.rainbow){
                        if(this.x == o.x){
                            return this.y - o.y;
                        }return this.x - o.x; // 오름차순
                    }return o.rainbow - this.rainbow;
                }return o.totalBlock - this.totalBlock; // 내림차순
            }

            @Override
            public String toString() {
                return "position [block=" + totalBlock + ", color=" + color + ", x=" + x + ", y=" + y + "]";
            }
        }

        PriorityQueue<position> pq = new PriorityQueue<position>();
        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<N; j++){ 
                // i,j를 기준블록으로 했을때 같은 색의 블록 수를 계산하자
                if(board[i][j]==-1 || board[i][j]==0 || board[i][j] == None) continue; 
                int color = board[i][j]; // 기준색

                int []block = count(i, j, color);
                if(block[0] < 2) continue;
                pq.offer( new position(i, j, color, block[0], block[1]) );
            }
        }

        if(pq.isEmpty()) return false;


        position top = pq.poll();
        int tx = top.x;
        int ty = top.y;
        answer += Math.pow(top.totalBlock, 2);

        delete(tx, ty, top.color);
        pq.clear();
        return true;
    }

    // x,y를 기준으로 삭제가능한 블록수 세기
    // return {전체 블록수, 무지개 블록수}
    public static int[] count(int x, int y, int color){
        int [] block = {0,0};
        boolean [][] check = new boolean[N][N];
        Queue<int[]> q = new LinkedList<int []>(); // 위치
        int []mx = {-1, 1, 0, 0};
        int []my = {0, 0, -1, 1};

        int [] start = {x,y}; 
        block[0] ++;
        if(board[x][y]==0) block[1]++;
        q.offer(start);
        check[x][y] = true; 

        while(!q.isEmpty()){
            int [] current = q.poll();
            for(int m = 0 ; m <4 ; m++){ // 상, 하, 좌, 우
                int nextX= current[0] + mx[m];
                int nextY = current[1] + my[m];
                int [] next = {nextX, nextY};
                if(nextX>=0 && nextX<N && nextY>=0 && nextY<N){
                    if( !check[nextX][nextY] ){
                        if(board[nextX][nextY]== color){
                            block[0]++;
                            q.offer(next);
                            check[nextX][nextY] = true; 
                        }else if(board[nextX][nextY] == 0 ){
                            block[1]++;
                            block[0]++;
                            q.offer(next);
                            check[nextX][nextY] = true; 
                        }
                    } 
                }
            }
        }
        return block;
    }

    // 기분블록, 제거할 색
    // 제거되는 블록의 개수를 세어 점수계산도 필요함.
    public static void delete(int x, int y, int color){
        boolean [][] check = new boolean[N][N];
        Queue<int[]> q = new LinkedList<int []>();
        int []mx = {-1, 1, 0, 0};
        int []my = {0, 0, -1, 1};

        int [] start = {x,y}; 
        q.offer(start);
        board[x][y] = None; // 삭제
        check[x][y] = true;

        while(!q.isEmpty()){
            int [] current = q.poll();
            for(int m = 0 ; m <4 ; m++){ // 상, 하, 좌, 우
                int nextX= current[0] + mx[m];
                int nextY = current[1] + my[m];
                int [] next = {nextX, nextY};
                if(nextX>=0 && nextX<N && nextY>=0 && nextY<N){
                    if(board[nextX][nextY]== color || board[nextX][nextY] == 0){
                        if( !check[nextX][nextY] ){
                            q.offer(next);
                            board[nextX][nextY] = None; // 삭제
                        }
                    } 
                }
            }
        }
        gravity();
        rotate();
        gravity();
    }

    public static void gravity(){ // O(N^2)
        for(int i = N-2 ; i >=0 ; i--){
            for(int j = N-1; j>=0 ; j--){
                int x = i; // 위아래
                if(board[i][j]==-1) continue; // 검은 블록
                if(board[i][j]==None) continue; // 빈칸

                while(board[x+1][j]==None){ // 아래쪽이 비어있으면
                    board[x+1][j] = board[x][j];
                    board[x][j] = None;
                    x ++;
                    if(x==N-1) break;
                }
            }
        }
    }

    // counter-clock wise
    public static void rotate(){ // O(2N^2)
        int[][] temp = new int[N][N];
        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<N; j++){ 
                temp[i][j]=board[i][j]; // copy
            }
        }

        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<N; j++){ 
                board[i][j] = temp[j][N-i-1];
            }
        }
    }
}
