import java.io.*;
import java.util.*;

public class Main {
    static int T, start, end;
    static boolean[] isPrime;
    static Queue<Integer> queue;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        makePrime();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            count = new int[10000];
            Arrays.fill(count, -1);
            count[start] = 0;
            queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                int now = queue.remove();
                if(now == end) break;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(i == 0 && j == 0) continue;
                        int next = changeDigit(now, i, j);
                        if(isPrime[next] && count[next] == -1) {
                            count[next] = count[now] + 1;
                            queue.add(next);
                        }
                    }
                }
            }
            if(count[end] == -1) sb.append("Impossible\n");
            else sb.append(count[end]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static int changeDigit(int now, int digit, int to) {
        StringBuilder sb = new StringBuilder();
        sb.append(now);
        sb.replace(digit, digit + 1, Integer.toString(to));
        return Integer.parseInt(sb.toString());
    }
    static void makePrime() {
        isPrime = new boolean[10000];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < 10000; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j < 10000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

}
