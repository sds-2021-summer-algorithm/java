import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main{
    static final int LIMIT = 1000000000;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true){
            String line = br.readLine();
            ArrayList<String> commands = new ArrayList<>();

            if(line.equals("QUIT")){
                break;
            }

            while(!line.equals("END")){
                commands.add(line);
                line = br.readLine();
            }

            int N = Integer.parseInt(br.readLine());
            for(int i = 0 ; i<N ; i++){
                int input = Integer.parseInt(br.readLine());

                String result = execute(commands, input);
                sb.append(result+"\n");
            }
            sb.append("\n");
            br.readLine();
        }

        bw.write(sb.toString().strip());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String execute(ArrayList<String> commands, int input) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(input);

        for(int i = 0 ; i<commands.size(); i++){
            String[] command = commands.get(i).split(" ");

            if(command[0].equals("NUM")){
                s.push(Integer.parseInt(command[1]));
            }else if(command[0].equals("POP")){
                if(s.size()>=1){
                    s.pop();
                }else{
                    return "ERROR";
                } 
            }else if(command[0].equals("INV")){
                if(s.size()>=1){
                    int temp = s.pop();
                    s.push(-temp);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("DUP")){
                s.push(s.peek());
            }else if(command[0].equals("SWP")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();

                    s.push(temp1);
                    s.push(temp2);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("ADD")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();

                    int result = temp1+temp2;
                    if(result>LIMIT || result<-LIMIT){
                        return "ERROR";
                    }
                    s.push(result);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("SUB")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();

                    int result = temp2-temp1;
                    if(result>LIMIT || result<-LIMIT){
                        return "ERROR";
                    }
                    s.push(result);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("MUL")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();

                    long result = temp1*temp2;
                    if(result>LIMIT || result<-LIMIT){
                        return "ERROR";
                    }
                    s.push((int)result);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("DIV")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();
                    if(temp1 == 0){
                        return "ERROR";
                    }

                    int minusCount = 0;
                    if(temp1<0) minusCount++;
                    if(temp2<0) minusCount++;

                    int result = Math.abs(temp2)/Math.abs(temp1);
                    if(minusCount==1) result *= -1;
                    s.push(result);
                }else{
                    return "ERROR";
                }
            }else if(command[0].equals("MOD")){
                if(s.size()>=2){
                    int temp1 = s.pop();
                    int temp2 = s.pop();
                    if(temp1 == 0){
                        return "ERROR";
                    }
                    int result = Math.abs(temp2)%Math.abs(temp1);
                    if(temp2<0) result *= -1;
                    s.push(result);
                }else{
                    return "ERROR";
                }
            }else{
                // 이곳으로 들어오면 안됨.
                return "ERROR";
            }
        }

        if(s.size()!=1) return "ERROR";
        return Integer.toString(s.pop());
    }
}
