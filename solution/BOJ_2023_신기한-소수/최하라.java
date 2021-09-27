import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<String> list;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());

        // 2. permutation을 사용해 가능한 조합 찾기
        list = new ArrayList<>();
        backtracking(0, "");

        // 3. 결과 배출
        System.out.print(sb);
    }

    static void backtracking(int depth, String num) {
        if (depth == N) {
            if (isPrime(Integer.parseInt(num)))
                sb.append(num).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (num.length() == 0)
                backtracking(depth + 1, num + i); // string is empty

            else if (isPrime(Integer.parseInt(num)))
                backtracking(depth + 1, num + i);

            else
                return;
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1)
            return false;

        int sqrt = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrt; i++)
            if (num % i == 0)
                return false;

        return true;
    }
}