import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static Stack<Integer> stack;
    static ArrayList<String> Task;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true){
            String input = br.readLine();
            if (input.equals("QUIT"))
                break;

            Task = new ArrayList<>();
            while (!input.equals("END")){
                Task.add(input);
                input = br.readLine();
            }

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                stack = new Stack<>();
                stack.push(Integer.parseInt(br.readLine()));
                if (run())
                    bw.write(stack.pop()+"\n");
                else
                    bw.write("ERROR\n");
            }
            bw.write("\n");
            br.readLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static boolean run(){
        for (String line : Task) {
            st = new StringTokenizer(line);
            String cmd = st.nextToken();

            if (cmd.equals("NUM")){
                stack.push(Integer.parseInt(st.nextToken()));
            }
            else if (cmd.equals("POP")){
                if (stack.empty())
                    return false;
                stack.pop();
            }
            else if (cmd.equals("INV")){
                if (stack.empty())
                    return false;
                stack.push(-stack.pop());
            }
            else if (cmd.equals("DUP")){
                if (stack.empty())
                    return false;
                stack.push(stack.peek());
            }
            else if (cmd.equals("SWP")){
                if (stack.empty())
                    return false;
                int A = stack.pop();
                if (stack.empty())
                    return false;
                int B = stack.pop();
                stack.push(A);
                stack.push(B);
            }
            else if (cmd.equals("ADD")){
                if (stack.empty())
                    return false;
                int A = stack.pop();
                if (stack.empty())
                    return false;
                int B = stack.pop();
                if (Math.abs(A+B)>1000000000)
                    return false;
                stack.push(A+B);
            }
            else if (cmd.equals("SUB")){
                if (stack.empty())
                    return false;
                int A = stack.pop();
                if (stack.empty())
                    return false;
                int B = stack.pop();
                if (Math.abs(B-A)>1000000000)
                    return false;
                stack.push(B-A);
            }
            else if (cmd.equals("MUL")){
                if (stack.empty())
                    return false;
                long A = stack.pop();
                if (stack.empty())
                    return false;
                long B = stack.pop();
                if (Math.abs(A*B)>1000000000)
                    return false;
                stack.push((int) (A*B));
            }
            else if (cmd.equals("DIV")){
                if (stack.empty())
                    return false;
                int A = stack.pop();
                if (stack.empty() || A==0)
                    return false;
                int B = stack.pop();
                if (((A>0 && B<0) || (A<0 && B>0)))
                    stack.push(-(Math.abs(B)/Math.abs(A)));
                else
                    stack.push(Math.abs(B)/Math.abs(A));
            }
            else if (cmd.equals("MOD")){
                if (stack.empty())
                    return false;
                int A = stack.pop();
                if (stack.empty() || A==0)
                    return false;
                int B = stack.pop();
                if (B<0)
                    stack.push(-(Math.abs(B)%Math.abs(A)));
                else
                    stack.push(Math.abs(B)%Math.abs(A));
            }
        }
        if (stack.size()!=1)
            return false;
        return true;
    }
}
