import java.io.*;
import java.util.*;

public class Main {
    static int[] alphabetToNum = new int[26];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int len = s.length();
            for (int j = 0; j < len; j++) {
                char c = s.charAt(j);
                int coef = (int) Math.pow(10, len - (j + 1));
                alphabetToNum[c - 'A'] += coef;
            }
        }
        Arrays.sort(alphabetToNum);
        int answer = 0;
        int value = 9;
        for (int i = 25; i >= 0; i--) {
            if(alphabetToNum[i] == 0) break;
            answer += alphabetToNum[i] * value--;
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
