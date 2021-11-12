import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static long[][] nums;
    static long cnt = 0;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        nums = new long[N][10];

        for (int i = 1; i <= 9; i++) nums[0][i] = 1; // init
        for (int row = 1; row < N; row++) {
            nums[row][0] = nums[row-1][1];
            for (int i = 1; i <= 8; i++)
                nums[row][i] = (nums[row-1][i-1] + nums[row-1][i+1]) % 1000000000;
            nums[row][9] = nums[row-1][8];
        }
        for (long i : nums[N-1])
            cnt += i;

        bw.write(cnt%1000000000+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
