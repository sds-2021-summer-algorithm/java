import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer=-1;
    static char[][] board;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int[] init = new int[6]; // cnt, way, Rr, Rc, Br, Bc
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++)
                if (board[i][j]=='R') { // R의 위치를 저장
                    init[2] = i;
                    init[3] = j;
                    board[i][j]='.';
                }
                else if (board[i][j]=='B') { // B의 위치를 저장
                    init[4] = i;
                    init[5] = j;
                    board[i][j]='.';
                }
        }

        queue = new LinkedList();
        for (int i = 0; i < 4; i++)
            queue.add(new int[]{1, i, init[2], init[3], init[4], init[5]}); // 각자의 위치에서 4방향으로 기울이는경우를 Q에 추가

        while (!queue.isEmpty()){
            int[] cur = queue.poll(); // 큐에서 하나씩 뽑아오며
            if (tilt(cur[0], cur[1], cur[2], cur[3], cur[4], cur[5])) // 기울이고, true가 나오면
                break;
        }
        bw.write(answer+"\n"); // 출력

        bw.flush();
        bw.close();
        br.close();
    }
    static boolean tilt(int cnt, int way, int Rr, int Rc, int Br, int Bc){ // 각 위치에서 way방향으로 cnt번째 출발.
        if(cnt>10) // 10번 넘으면 X
            return false;
        // 이동 시작.
        int[] p;
        if((way==0 && Rr<Br) || (way==1 && Rc>Bc) || (way==2 && Rr>Br) || (way==3 && Rc<Bc)){// 빨강 먼저 출발
            p = move(way, Rr, Rc); // 이동한 빨강 좌표
            Rr = p[0];
            Rc = p[1];
            p = move(way, Br, Bc); // 이동한 파랑 좌표
            Br = p[0];
            Bc = p[1];
            if(board[Br][Bc]=='O') // 파랑이 목적지 도착? -> 실패.
                return false;
            if(Rr==Br && Rc==Bc) { // 빨강 파랑이 겹치면, 빨강이 먼저 출발했으니 파랑이 뒤로가기.
                Br-=dr[way];
                Bc-=dc[way];
            }
        }
        else{ // 파랑 먼저 출발
            p = move(way, Br, Bc); // 이동한 파랑 좌표
            Br = p[0];
            Bc = p[1];
            p = move(way, Rr, Rc); // 이동한 빨강 좌표
            Rr = p[0];
            Rc = p[1];
            if(board[Br][Bc]=='O') // 파랑이 목적지 도착? -> 실패.
                return false;
            if(Rr==Br && Rc==Bc) { // 빨강 파랑이 겹치면, 파랑이 먼저 출발했으니 빨강이 뒤로가기.
                Rr-=dr[way];
                Rc-=dc[way];
            }
        }
        if(board[Rr][Rc]=='O') { // 빨강이 목적지 도착? -> O
            answer = cnt;
            return true;
        }
        for (int i = 0; i < 4; i++)
            if (i!=way)
                queue.add(new int[]{cnt+1, i, Rr, Rc, Br, Bc}); // 이동한 지점으로부터 온 방향을 제외하고 3가지 방향으로 다시 출발
        return false;
    }
    static int[] move(int way, int r, int c){
        int y = dr[way], x = dc[way];
        while (board[ r+y ][ c+x ]!='#'){ // 다음 목적지가 #가 아니라면 계속 전진.
            r += y;
            c += x;
            if (board[r][c]=='O') // 목적지에 도착하면 멈춤
                break;
        }
        return new int[]{r, c}; // 도착한 곳의 좌표 반환
    }
}
