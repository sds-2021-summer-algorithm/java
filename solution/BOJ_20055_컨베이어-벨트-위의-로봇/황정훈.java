import java.io.*;
import java.util.*;

public class Main {

    static int N, K, step;
    static class Conv{
        int hp = 0;
        boolean exist = false;
        public Conv(int hp){
            this.hp = hp;
        }
    }
    static Conv[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new Conv[2*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            A[i] = new Conv(Integer.parseInt(st.nextToken()));
        }

        step = 1;
        while (true){
            rotate(); // 회전
            move(); // 이동
            if(A[0].hp>0){ // 로봇 올리기
                A[0].exist = true;
                A[0].hp--;
            }
            if(done()) // 조건체크
                break;
            step++;
        }
        bw.write(step+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static boolean done(){
        int cnt = 0;
        for (int i = 0; i < 2*N; i++) {
            if (A[i].hp==0) // 내구도 0 갯수 파악
                cnt++;
        }
        if (cnt>=K)
            return true;
        return false;
    }
    static void rotate(){
        Conv tmp = A[2*N-1];
        for (int i = 2*N-1; i > 0; i--) { // 끝에서부터 하나씩 앞으로 땡기기
            A[i] = A[i-1];
        }
        if (A[N-1].exist) // 내리는 위치 내리기
            A[N-1].exist = false;
        A[0] = tmp;
    }
    static void move(){
        if (A[N-2].exist && A[N-1].hp>0){ // 내리는 위치로 이동하는 경우에 내리기
            A[N-1].hp--;
            A[N-2].exist = false;
        }
        for (int i = N-3; i >= 0; i--) {
            if (A[i].exist && !A[i+1].exist && A[i+1].hp>0){ // 이동가능하면 이동
                A[i].exist = false;
                A[i+1].exist = true;
                A[i+1].hp--;
            }
        }
    }
}
