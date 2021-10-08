import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {

        String S = br.readLine();
        String T = br.readLine();
        bw.write(can(S, T) ? "1\n" : "0\n");
        
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean can(String A, String B){
        if (A.equals(B)) return true;
        else if (A.length()==B.length()) return false;
        return can(A, B.charAt(B.length()-1)=='B'? new StringBuilder(B.substring(0, B.length()-1)).reverse().toString() : B.substring(0, B.length()-1));
    }
}
