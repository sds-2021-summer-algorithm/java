import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] order = new int[N];
        for (int i = 0; i < N; i++)
            order[i] = Integer.parseInt(st.nextToken());
        Deque<Integer> deque = new ArrayDeque<>();

        int current = 0;
        for (int i = N - 1; i >= 0; i--) {
            current++;
            switch (order[i]) {
                case 1:
                    deque.addFirst(current);
                    break;
                case 2:
                    int tmp = deque.pollFirst();
                    deque.addFirst(current);
                    deque.addFirst(tmp);
                    break;
                case 3:
                    deque.addLast(current);
                    break;
            }
        }

        // Result
        while (!deque.isEmpty())
            sb.append(deque.removeFirst() + " ");
        System.out.println(sb);
    }
}