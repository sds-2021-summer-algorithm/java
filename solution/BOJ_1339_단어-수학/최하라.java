import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] words;
    static int[] count = new int[26];
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int length = words[i].length();

            for (int j = length - 1; j >= 0; j--)
                count[words[i].charAt(j) - 'A'] += Math.pow(10, words[i].length() - j - 1);
        }

        // 2. 결과 저장
        pq = new PriorityQueue<>();
        for (int i : count) {
            if (i > 0)
                pq.add(i);
        }

        // 3. 총 합 구하기
        int num = 10 - pq.size(), result = 0;
        while (!pq.isEmpty())
            result += pq.poll() * num++;

        // 4. 결과 배출
        System.out.println(result);
    }
}