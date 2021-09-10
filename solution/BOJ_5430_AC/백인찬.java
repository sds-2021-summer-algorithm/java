import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static String P;
    static Deque<Integer> deque;
    static boolean mode; // if true : 앞에서부터
    static List<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            P = br.readLine();
            N = Integer.parseInt(br.readLine());
            deque = new ArrayDeque<>();
            answer = new ArrayList<>();
            mode = true;

            String line = br.readLine();
            line = line.substring(1, line.length() - 1);
            StringTokenizer st = new StringTokenizer(line, ",");
            while(st.hasMoreTokens()) {
                deque.add(Integer.valueOf(st.nextToken()));
            }

            int len = P.length();
            int index = 0;
            try {
                while(index < len) {
                    switch (P.charAt(index++)) {
                        case 'R':
                            mode = !mode;
                            break;
                        case 'D':
                            if(mode) {
                                answer.add(deque.removeFirst());
                            } else {
                                answer.add(deque.removeLast());
                            }
                            break;
                    }
                }
                if(deque.isEmpty()) {
                    sb.append("[]\n");
                }
                else {
                    sb.append("[");
                    while(!deque.isEmpty()) {
                        if(mode) {
                            sb.append(deque.removeFirst());
                        } else {
                            sb.append(deque.removeLast());
                        }
                        if(deque.size() != 0) {
                            sb.append(",");
                        }
                    }
                    sb.append("]\n");
                }
            } catch (Exception e) {
                sb.append("error\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
