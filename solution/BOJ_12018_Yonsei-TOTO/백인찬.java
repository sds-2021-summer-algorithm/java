import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static PriorityQueue<PriorityQueue<Integer>> pqs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pqs = new PriorityQueue<>(Comparator.comparingInt(PriorityQueue::peek));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

            for (int j = 0; j < p; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
            if(pq.size() >= q) {
                for (int j = 0; j < q - 1; j++) {
                    pq.remove();
                }
            }
            else {
                pq.clear();
            }
            if(pq.size() == 0) {
                pq.add(0);
            }
            pqs.add(pq);
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            int a = pqs.remove().peek();
            if(a == 0) a = 1;
            if(M >= a && a <= 36) {
                M -= a;
                total++;
            }
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
//
//// 배열 버전
//import java.io.*;
//import java.util.*;
//
//public class Main {
//    static int N, M;
//    static PriorityQueue[] pqs;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        pqs = new PriorityQueue[N];
//        for (int i = 0; i < N; i++) {
//            pqs[i] = new PriorityQueue<Integer>(Comparator.reverseOrder());
//        }
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int p = Integer.parseInt(st.nextToken());
//            int q = Integer.parseInt(st.nextToken());
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < p; j++) {
//                pqs[i].add(Integer.parseInt(st.nextToken()));
//            }
//            if(pqs[i].size() >= q) {
//                for (int j = 0; j < q - 1; j++) {
//                    pqs[i].remove();
//                }
//            }
//            else {
//                pqs[i].clear();
//            }
//            if(pqs[i].size() == 0) {
//                pqs[i].add(0);
//            }
//        }
//        Arrays.sort(pqs, Comparator.comparingInt(o -> (int) o.peek()));
//        int total = 0;
//        for (int i = 0; i < N; i++) {
//            int a = (int) pqs[i].peek();
//            if(a == 0) a = 1;
//            if(M >= a && a <= 36) {
//                M -= a;
//                total++;
//            }
//        }
//        bw.write(total + "\n");
//        bw.flush();
//        bw.close();
//    }
//}
