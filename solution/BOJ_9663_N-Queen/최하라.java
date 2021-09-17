import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static int[] col;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        count = 0;
        for (int i = 1; i <= N; i++) {
            col = new int[N + 1];

            col[1] = i;
            dfs(1);
        }
        System.out.println(count);
    }

    static void dfs(int depth) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            col[depth + 1] = i;
            if (check(depth + 1))
                dfs(depth + 1);
        }
    }

    static boolean check(int row) {
        for (int i = 1; i < row; i++) {
            if (col[i] == col[row])
                return false;
            if (Math.abs(i - row) == Math.abs(col[i] - col[row]))
                return false;
        }
        return true;
    }
}