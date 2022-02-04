import java.io.*;
import java.util.*;

public class Main {
    public static class Work implements Comparable<Work> {
        int work, limit;

        Work(int work, int limit) {
            this.work = work;
            this.limit = limit;
        }

        @Override
        public int compareTo(Work o) {
            return o.limit - this.limit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Work> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int end = pq.peek().limit - pq.poll().work;
        while (!pq.isEmpty()) {
            if (end < 0)
                break;
            if (pq.peek().limit <= end) {
                end = pq.peek().limit - pq.poll().work;
            } else {
                end -= pq.poll().work;
            }
        }

        System.out.println(end < 0 ? -1 : end);
    }
}