import java.io.*;
import java.util.*;

public class Main{
    
    static int switchForNums = 0;
    static int M;

    static void add(int n){
        switchForNums = switchForNums | (1<<n);
    }

    static void remove(int n){
        switchForNums = switchForNums & ~(1<<n);
    }

    static void toggle(int n){
        switchForNums= switchForNums ^ (1<<n);
    }

    static int check(int n){
        return (switchForNums & (1<<n)) >> n;
    }

    static void empty(){
        switchForNums = 0;
    }

    static void all(){
        switchForNums = ~0;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(br.readLine());
        while(M --> 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();

            int num;
            switch(query) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    add(num);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    remove(num);
                    break;
                case "check":    
                    num = Integer.parseInt(st.nextToken());
                    sb.append(check(num)+"\n");
                    break;
                case "toggle":    
                    num = Integer.parseInt(st.nextToken());
                    toggle(num);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
                default: 
                    System.out.println("should not be reached here");
                    break;
            }
        }   
        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }
}
