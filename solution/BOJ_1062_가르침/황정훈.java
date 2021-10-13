import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K, cnt = 0;
    static boolean[] alpha = new boolean[26];
    static char[][] words;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K<5) sb.append(0);
        else{
            words = new char[N][];
            for (int i = 0; i < N; i++) {
                words[i] = br.readLine().toCharArray();
            }
            learn(0, K);
            sb.append(cnt);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void learn(int s, int remain){
        if (remain==0){
            cnt = Math.max(cnt, count());
            return;
        }
        for (int i = s; i < 26; i++) {
            if(!alpha[i]) {
                alpha[i] = true;
                learn(i+1, remain - 1);
                alpha[i] = false;
            }
        }
    }
    static int count(){
        int tmp = N;
        for (int i = 0; i < N; i++) {
            for(char c : words[i]){
                if (!alpha[c-'a']){
                    tmp--;
                    break;
                }
            }
        }
        return tmp;
    }
}
