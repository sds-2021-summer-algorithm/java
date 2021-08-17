import java.io.*;

public class Main {
    static String S, T;
    static boolean able;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        S = br.readLine();
        T = br.readLine();

        // 재귀를 활용해 S로 T를 만들 수 있는지 체크
        able = false;
        makeStr(T);

        int result = able ? 1 : 0;
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    static void makeStr(String tmp) {
        // match 없음
        if (tmp.length() < S.length())
            return;

        // S 도달 체크
        if (tmp.length() == S.length()) {
            if (tmp.compareTo(S) == 0) {
                able = true;
                return;
            }
        }

        // A로 시작하며 B로 끝난다면 불가능한 문자열
        // B를 넣고 뒤집기 때문에
        if (tmp.charAt(0) == 'A' && tmp.charAt(tmp.length() - 1) == 'B')
            return;

        // A로 끝난다면 A를 문자열에서 제외한다
        if (tmp.charAt(tmp.length() - 1) == 'A')
            makeStr(tmp.substring(0, tmp.length() - 1));

        // B로 시작한다면 뒤집고 마지막 문자 제이
        if (tmp.charAt(0) == 'B') {
            tmp = new StringBuilder(tmp).reverse().toString();
            makeStr(tmp.substring(0, tmp.length() - 1));
        }
    }
}
