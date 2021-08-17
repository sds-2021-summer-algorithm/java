import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        String str = br.readLine();
        int endtag = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                // 공백은 앞에 단어 reverse 해주고 임시 스트링 초기화 해주기
                sb.append(tmp.reverse() + " ");
                tmp.setLength(0);
            } else if (str.charAt(i) == '<') {
                // tag 전 단어 reverse 해준 후 다음 tag(>) 까지 저장해주고 i를 마지막 태그 index로 업데이트 해주기
                sb.append(tmp.reverse());
                tmp.setLength(0);
                endtag = str.indexOf(">", endtag + 1);

                sb.append(str.substring(i, endtag + 1));
                i = endtag;
            } else {
                // 임시 스트링에 현재 캐릭터 저장
                tmp.append(str.charAt(i));
            }
        }
        // 마지막 남은 단어 reverse 해주기
        sb.append(tmp.reverse());

        // 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}