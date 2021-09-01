import java.io.*;
import java.util.*;

public class Main {

    static int N, L, cnt = 0;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            if (chk(map[i])) // 행 체크
                cnt++;

            int[] vertical = new int[N];
            for (int j = 0; j < N; j++) {
                vertical[j] = map[j][i];
            }
            if (chk(vertical)) // 열 체크
                cnt++;
        }
        bw.write(cnt+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static boolean chk(int[] road){
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N-1; i++) {
            if(Math.abs(road[i] - road[i+1]) > 1) // 높이 차이 많이나면 못지나감
                return false;

            if(road[i] > road[i+1]) // 하강
                for (int j = i+1; j <= i+L; j++) { // 현재위치 다음부터, L만큼 순회하면서
                    if(j==N || visit[j] || road[j]!=road[i+1]) // 순회도중 범위를 벗어나거나, 이미 발판이 놓였거나, 높이가 일정하지 않으면,
                        return false; // 못지나감
                    visit[j] = true; // 발판놓기
                }

            if(road[i] < road[i+1]) // 상승
                for (int j = i; j > i-L; j--) { // 현재위치부터, L만큼 전으로 돌아가며
                    if(j<0 || visit[j] || road[j]!=road[i]) // 범위를 벗어나거나, 이미 발판이 놓였거나, 높이가 일정하지 않으면,
                        return false; // 못지나감
                    visit[j] = true; // 발판놓기
                }
        }
        return true; // 통과
    }
}
