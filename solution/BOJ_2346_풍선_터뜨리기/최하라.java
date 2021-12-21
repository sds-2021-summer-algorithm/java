package solution.BOJ_2346_풍선_터뜨리기;

public import java.io.*;
import java.util.*;

public class Main {
    static class Info {
        int move, idx;
        Info(int move, int idx) {
            this.move = move;
            this.idx = idx;
        }
    }

    static ArrayList<Info> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Info> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(new Info(Integer.parseInt(st.nextToken()), i));
        }


        for (int i = 0; i < N; i++) {
            sb.append(deque.peekFirst().idx + " ");
            int next = deque.pollFirst().move;
            if (deque.isEmpty()) break;
            if (next < 0) {
                for (int j = 0; j < Math.abs(next); j++) {
                    deque.addFirst(deque.pollLast());
                }
            } else {
                next -= 1;
                for (int j = 0; j < next; j++) {
                    deque.addLast(deque.pollFirst());
                }
            }
        }
        System.out.println(sb);
    }
}class 최하라 {
    
}
