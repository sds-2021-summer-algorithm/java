import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static boolean[] arrow, visit;
    static boolean found;
    static int[] arr;
    static StringBuilder sb;
    static long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
    static String minStr, maxStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 입력 받기
        K = Integer.parseInt(br.readLine());
        arrow = new boolean[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            if (st.nextToken().equals(">"))
                arrow[i] = true;
        }

        // 2. 최대 조합 찾기
        arr = new int[K + 1];
        visit = new boolean[11];
        found = false;
        for (int i = 9; i >= 0; i--) { // 시작점; 9에서 시작
            if (found)
                break;
            visit[i] = true;
            arr[0] = i;
            maxDFS(1);
            visit[i] = false;
        }

        // 3. 최소 조합 찾기
        visit = new boolean[11];
        found = false;
        for (int i = 0; i <= 9; i++) { // 시작점; 0에서 시작
            if (found)
                break;
            visit[i] = true;
            arr[0] = i;
            minDFS(1);
            visit[i] = false;
        }

        // 4. 결과 출력
        System.out.println(maxStr + "\n" + minStr);
    }

    static void minDFS(int depth) {
        if (depth == K + 1) {
            String s = "";
            for (int i : arr)
                s += i;

            if (min > Long.parseLong(s)) {
                min = Long.parseLong(s);
                minStr = s;
            }
            found = true;
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i;
                if (compare(depth))
                    minDFS(depth + 1);
                visit[i] = false;
            }
        }
    }

    static void maxDFS(int depth) {
        if (depth == K + 1) {
            String s = "";
            for (int i : arr)
                s += i;

            if (max < Long.parseLong(s)) {
                max = Long.parseLong(s);
                maxStr = s;
            }
            found = true;
            return;
        }

        for (int i = 9; i >= 0; i--) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i;
                if (compare(depth))
                    maxDFS(depth + 1);
                visit[i] = false;
            }
        }
    }

    static boolean compare(int depth) {
        int a = arr[depth - 1];
        int b = arr[depth];

        if (arrow[depth - 1]) // >
            return a > b ? true : false;
        else // <
            return a < b ? true : false;
    }
}