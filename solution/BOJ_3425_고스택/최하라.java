import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> cmdList;
    static Stack<Long> stack;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String cmd = "";
        max = (int) Math.pow(10, 9);

        while (true) {
            cmd = br.readLine();
            if (cmd.equals("QUIT")) break;
            if (cmd.length() == 0) continue;
            if (sb.length() != 0) sb.append("\n");

            //store commands
            cmdList = new ArrayList<>();
            while (!cmd.equals("END")) {
                cmdList.add(cmd);
                cmd = br.readLine();
            }

            //test cases
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                stack = new Stack<>();
                stack.push(Long.parseLong(br.readLine()));
                if (search(stack)) {
                    sb.append(stack.size() == 1 ? stack.pop() : "ERROR").append("\n");
                } else sb.append("ERROR").append("\n");
            }
        }
        System.out.print(sb);
    }

    public static boolean search(Stack<Long> stack) {
        for (int i = 0; i < cmdList.size(); i++) {
            StringTokenizer st = new StringTokenizer(cmdList.get(i));
            String cmd = st.nextToken();
            switch (cmd) {
                case "NUM":
                    stack.push(Long.parseLong(st.nextToken()));
                    break;
                case "POP":
                    if (stack.size() == 0) return false;
                    stack.pop();
                    break;
                case "INV":
                    if (stack.size() == 0) return false;
                    stack.push(stack.pop() * -1);
                    break;
                case "DUP":
                    if (stack.size() == 0) return false;
                    stack.push(stack.peek());
                    break;
                case "SWP":
                    if (stack.size() < 2) return false;
                    swap();
                    break;
                case "ADD" :
                    if (stack.size() < 2) return false;
                    add();
                    break;
                case "SUB" :
                    if (stack.size() < 2) return false;
                    sub();
                    break;
                case "MUL" :
                    if (stack.size() < 2) return false;
                    mul();
                    break;
                case "DIV" :
                    if (stack.size() < 2) return false;
                    else div();
                    break;
                case "MOD" :
                    if (stack.size() < 2) return false;
                    else mod();
                    break;
            }
            if (!stack.isEmpty()) {
                if (stack.peek() > max) return false;
            }
        }
        return true;
    }

    public static void swap() {
        long a = stack.pop();
        long b = stack.pop();
        stack.push(a);
        stack.push(b);
    }

    public static void add() {
        long a = stack.pop();
        long b = stack.pop();
        long sum = a + b;
        if (sum > max || sum < max * -1) {
            stack.push((long) (max + 1));
        } else stack.push(a + b);
    }

    public static void sub() {
        long a = stack.pop();
        long b = stack.pop();
        long sub = b - a;
        if (sub > max || sub < max * -1) {
            stack.push((long) (max + 1));
        } else stack.push(sub);
    }

    public static void mul() {
        long a = stack.pop();
        long b = stack.pop();
        long mul = a * b;
        if (mul > max || mul < max * -1) {
            stack.push((long) (max + 1));
        } else stack.push(a * b);
    }

    public static void div() {
        int negCount = 0;
        long a = stack.pop();
        long b = stack.pop();
        if (a < 0) negCount++;
        if (b < 0) negCount++;
        if (a == 0) {
            stack.push((long) (max + 1));
            return;
        }
        long div = Math.abs(b) / Math.abs(a);
        if (negCount == 1)
            stack.push(div * -1);
        else stack.push(div);
    }

    public static void mod() {
        long a = stack.pop();
        long b = stack.pop();
        if (a == 0) {
            stack.push((long) (max + 1));
            return;
        }
        long mod = Math.abs(b) % Math.abs(a);
        if (b < 0) mod *= -1;
        stack.push(mod);
    }
}public class 최하라 {
    
}
