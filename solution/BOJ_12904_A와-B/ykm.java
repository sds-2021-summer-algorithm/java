import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String S = br.readLine();
        String T = br.readLine();

        System.out.println(change(S,T));
    }

    public static int change(String s, String t) {
        
        Queue<String> q = new LinkedList<String>();
        q.offer(s);
        while(!q.isEmpty()){
            String current = q.poll();
            
            if(current.equals(t)) return 1;
            if(current.length() >= t.length()) continue;

            String a = addA(current); // 마지막에 A추가
            if(portion(a,t)) q.offer(a); // target의 일부분이면 q에 추가

            String b = reverse(current); // 뒤집고 마지막에 B추가
            b = addB(b);
            if(portion(b,t)) q.offer(b); // target의 일부분이면 q에 추가
        }
        return 0;
    }

    public static String addA(String s){
        s = s+"A";
        return s;
    }

    public static String reverse(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return sb.toString();
    }
    public static String addB(String s){
        s = s+"B";
        return s;
    }

    // s1이 s2의 일부인지 확인하자
    public static boolean portion(String s1, String s2){
        if(s2.contains(s1)) return true;
        if(s2.contains(reverse(s1))) return true;
        return false;
    }

}
