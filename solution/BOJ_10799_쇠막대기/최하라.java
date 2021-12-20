import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String given = br.readLine();
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int totalCut = 0;

        for (int i = 0; i < given.length(); i++) {
            if (given.charAt(i) == '(') {
                stack.add(0);
            } else {
                if (stack.peek() == 0) {
                    stack.pop();
                    count = 0;
                    if (!stack.isEmpty()) {
                        if (stack.peek() != 0) {
                            count = stack.pop();
                            stack.add(count + 1);
                        } else
                            stack.add(1);
                    } else
                        stack.add(1);
                } else {
                    count = stack.pop();
                    totalCut += count + 1;
                    stack.pop();

                    if (!stack.isEmpty()) {
                        if (stack.peek() != 0) {
                            count += stack.pop();
                        }
                    }

                    stack.add(count);
                }
            }
        }
        System.out.println(totalCut);
    }
}