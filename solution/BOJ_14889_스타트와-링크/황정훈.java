import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int answer = Integer.MAX_VALUE;
    static int allstat;
    static int[] statsum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 입력부분
        int[][] stat = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
                allstat += stat[i][j]; // 배열의 전체 합
            }
        }

        statsum = new int[N]; // i번째 행과 열의 합을 저장하는 배열
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            for (int j = 0; j < N; j++)
                if(i!=j)
                    tmp += stat[i][j] + stat[j][i];
            statsum[i] = tmp;
        }

        combination(new boolean[N], 0, N/2); // n/2개 만큼을 뽑는 경우의 수
        bw.write(answer+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static void combination(boolean[] chk, int start, int remain){
        if (remain==0){ // n/2개 선택하면
            int sum = 0;
            for (int i = 0; i < N; i++)
                if(chk[i])
                    sum += statsum[i]; // 선택된것들의 합을 구하여

            answer = Math.min(answer, Math.abs(allstat - sum)); // 최솟값 갱신
            return;
        }

        for (int i = start; i < N-remain; i++) { // 재귀호출로 조합구현
            chk[i] = true;
            combination(chk, i+1, remain-1);
            chk[i] = false;
        }
    }
}
