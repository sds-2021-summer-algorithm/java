import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main{
    static boolean isReverse = false;
    static int start;                   // t의 시작 index
    static int end;                     // t의 마지막 index + 1
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String S = br.readLine();
        String T = br.readLine();
        start = 0;
        end = T.length();

        System.out.println(check(S,T));
    }

    // T에서 문자를 하나씩 빼나가면서 S가 되는지 확인
    public static int check(String s, String t) {
        while(s.length()!=end-start){

            if(isReverse){
                if(t.charAt(start)=='B'){
                    isReverse = false;
                }
                start++;
            }else{
                if(t.charAt(end-1)=='B'){
                    isReverse = true;
                }
                end--;
            }
            
        }
        t = t.substring(start, end);
        if(isReverse) t = reverse(t);

        if(s.equals(t)) return 1;
        return 0;
    }

    public static String reverse(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return sb.toString();
    }

}

