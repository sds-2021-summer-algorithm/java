import java.io.*;

public class Main {
    static StringBuilder S, T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. 입력 받기
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());

        //2. 가능한 연산인지 확인
        System.out.println(check());
    }

    static int check() {
        while (true) {
            if (S.toString().equals(T.toString()))
                return 1;
            if (S.length() >= T.length())
                return 0;

            char key = T.charAt(T.length() - 1);
            T.deleteCharAt(T.length() - 1);
            if (key == 'B')
                T.reverse();
        }
    }
}
