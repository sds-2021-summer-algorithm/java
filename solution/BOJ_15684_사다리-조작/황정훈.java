import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H, cnt=0;
    static int[][] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[N][H]; // ladder[i][j] : i번째 세로선의 j번째 가로선을 지나면 위치하게되는 세로선의 번호

        for (int i = 1; i < N; i++) {
            Arrays.fill(ladder[i], i); // i번째 세로선에서 갈 수 있는 모든 세로선 번호는 모두 i
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            ladder[b][a] = b+1; // b번째 세로선의 a번째 가로선 위치를 지나면 b+1번째 세로선으로 이동
            if (b<N-1)
                ladder[b+1][a] = b; // 양방향
        }

        while(cnt<4){ // 4개를 고르기 전까지
            if (pick(0, 0, cnt)) // 골라서 만족하면
                break; // 멈추고
            cnt++; // 아니면 고르는 갯수 증가
        }
        bw.write(cnt<4 ? cnt+"\n" : "-1\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static boolean pick(int line, int level, int remain){
        if (remain==0)
            return chk();

        for (int i = line; i < N-1; i++) {
            for (int j = line==i ? level : 0; j < H; j++) {
                if (ladder[i][j] != i || (i<N-1 && ladder[i+1][j]!=i+1)) // 이미 가로선이 놓여져있거나, 옆세로선에 가로선이 놓여져있으면
                    continue; // 패스
                ladder[i][j] = i+1; // 옆으로 가는 가로선 표시하고
                if (i<N-1)
                    ladder[i+1][j] = i; // 옆선도 표시
                if (pick(i, j, remain-1)) // 조건 만족하면
                    return true; // 바로 종료
                ladder[i][j] = i;
                if (i<N-1)
                    ladder[i+1][j] = i+1;
            }
        }
        return false;
    }
    static boolean chk(){
        for (int start = 0; start < N; start++) {
            int nextline = start;
            for (int step = 0; step < H; step++) {
                nextline = ladder[nextline][step];
            }
            if (nextline!=start)
                return false;
        }
        return true;
    }
}
