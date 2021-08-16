import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] list;
    static char[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // build array
        st = new StringTokenizer(br.readLine());
        list = new char[C];
        for (int i = 0; i < C; i++) {
            list[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(list);

        // 암호 만들기
        tmp = new char[L];
        StringBuilder sb = new StringBuilder();
        bfs(0, 0, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int at, int depth, StringBuilder sb) {
        if (depth == L) {
            int count = 0;
            String str = "";
            for (int i = 0; i < L; i++) {
                str += tmp[i];
                if (isVowel(tmp[i]))
                    count++;
            }

            // 최소 모음 1개 & 최소 자음 2개 존재해야한다
            if (count == 0)
                return;
            if (count + 2 <= L)
                sb.append(str + "\n");
            return;
        }

        for (int i = at; i < C; i++) {
            tmp[depth] = list[i];
            bfs(i + 1, depth + 1, sb);
        }
    }

    static boolean isVowel(char vowel) {
        if (vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u') {
            return true;
        }
        return false;
    }
}