import java.io.*;
import java.util.*;

public class Main {
    public static class Info {
        int height, idx;

        Info(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Info> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int current = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                sb.append("0 ");
                stack.add(new Info(current, i));
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        sb.append("0 ");
                        stack.add(new Info(current, i));
                        break;
                    }
                    if (stack.peek().height < current) {
                        stack.pop();
                    } else {
                        sb.append(stack.peek().idx + " ");
                        stack.add(new Info(current, i));
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}