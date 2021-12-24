import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (pq.size() == N) {
                    if (pq.peek() < input) {
                        pq.add(input);
                        pq.poll();
                    }
                } else {
                    pq.add(input);
                }
            }
        }
        System.out.println(pq.poll());
    }
}