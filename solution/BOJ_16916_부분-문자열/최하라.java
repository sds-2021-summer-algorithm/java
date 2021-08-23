import java.io.*;

public class Main {
    static String str, partial;
    static int[] pattern;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //입력 받기
        str = br.readLine();
        partial = br.readLine();

        //table 빌드 할 때 사용
        pattern = new int[str.length()];

        //KMP
        bw.write(String.valueOf(KMP()));

        bw.flush();
        bw.close();
        br.close();
    }

    static int KMP() {
        //str의 KMP에 사용할 테이블 만들기
        buildTable();

        //문자열과 부분 문자열 비교
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != partial.charAt(j))
                j = pattern[j - 1]; //KMP 주 목적. Pattern 저장해둔거 사용해서 그 위치부터 비교해나감
            
            if (str.charAt(i) == partial.charAt(j)) {
                if (j == partial.length() - 1)
                    return 1;
                else 
                    j += 1;
            }
        }
        return 0;
    }



    static void buildTable() {
        int j = 0;
        for (int i = 1; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pattern[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                pattern[i] = ++j;
            }
        }
    }
}
