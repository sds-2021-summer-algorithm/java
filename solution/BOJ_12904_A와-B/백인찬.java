import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String start = br.readLine();
        String end = br.readLine();
        Deque<Character> endDeq = new ArrayDeque<>();
        int len = end.length();
        for (int i = 0; i < len; i++) {
            endDeq.add(end.charAt(i));
        }
        boolean reverse = false;
        while(endDeq.size() != start.length()) {
            char c;
            if(reverse) {
                c = endDeq.removeFirst();
            }
            else c = endDeq.removeLast();
            if(c == 'B') {
                reverse = !reverse;
            }
        }
        if(!reverse) {
            while(endDeq.size() > 0) sb.append(endDeq.removeFirst());
        } else {
            while(endDeq.size() > 0) sb.append(endDeq.removeLast());
        }
        if(sb.toString().equals(start)) bw.write("1\n");
        else bw.write("0\n");
        bw.flush();
        bw.close();
    }
}
