import java.io.*;
import java.util.*;
public class Main {
    static int T, N;
    static String P;
    static Deque<Integer> deque;
    static boolean mode; // if true : 앞에서부터
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            P = br.readLine();
            N = Integer.parseInt(br.readLine());
            deque = new ArrayDeque<>();
            mode = true;

            String line = br.readLine();
            line = line.substring(1, line.length() - 1);
            StringTokenizer st = new StringTokenizer(line, ",");
            for (int i = 0; i < N; i++) {
                deque.add(Integer.valueOf(st.nextToken()));
            }

            int len = P.length();

            boolean error = false;
            for (int i = 0; i < len; i++) {
                switch (P.charAt(i)) {
                    case 'R':
                        mode = !mode;
                        break;
                    case 'D':
                        if(deque.size() == 0) {
                            error = true;
                            break;
                        }
                        if(mode) {
                            deque.removeFirst();
                        } else {
                            deque.removeLast();
                        }
                        break;
                }
            }
            if(error) {
                sb.append("error\n");
            } else {
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
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
