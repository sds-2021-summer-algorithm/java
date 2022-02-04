import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static class Lecture {
        int code, start, end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                } else {
                    return o1.start - o2.start;
                }
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (pq.isEmpty()) {
                pq.add(lectures[i].end);
                count++;
            } else if (pq.peek() <= lectures[i].start) {
                pq.poll();
                pq.add(lectures[i].end);
            } else {
                pq.add(lectures[i].end);
                count++;
            }
        }

        System.out.println(count);
    }
}