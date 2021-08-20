import java.io.*;
import java.util.*;

public class Main {
    static int N, S, count;
    static int[] nums;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //2. 모든 부분 합 확인하기
        count = 0;
        visit = new boolean[N];
        dfs(0, 0);

        if (S == 0) count--;

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();

    }

    static void dfs(int depth, int sum) {
        if (depth == N) {
            //sum이 S와 일치 하다면 count에 1 추가하기
            if (sum == S) {
                count++;
            }
            return;
        }

        dfs(depth + 1, sum + nums[depth]);
        dfs(depth + 1, sum);
    }
}