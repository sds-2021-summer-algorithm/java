import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String instruction = br.readLine();

            // 배열 추려내기
            int N = Integer.parseInt(br.readLine());
            String strArr = br.readLine();
            String[] toArr = strArr.substring(1, strArr.length() - 1).split(",");

            // 배열 수들을 디큐에 저장
            Deque<String> arrDequeue = new ArrayDeque<>();
            for (int i = 0; i < N; i++)
                arrDequeue.add(toArr[i]);

            // 수행함수
            boolean right = false;
            boolean possible = true;
            for (int i = 0; i < instruction.length(); i++) {
                if (!possible)
                    break;
                switch (instruction.charAt(i)) {
                    case 'R':
                        right = !right;
                        break;
                    case 'D':
                        if (arrDequeue.isEmpty()) { // 디큐가 비었을 때
                            possible = false;
                            break;
                        }
                        // 숫자 존재
                        if (right)
                            arrDequeue.pollLast();
                        else
                            arrDequeue.pollFirst();
                }
            }

            // 결과 출력
            if (!possible)
                sb.append("error\n");
            else {
                sb.append("[");
                if (arrDequeue.isEmpty()) {
                    sb.append("]\n");
                    continue;
                }
                while (!arrDequeue.isEmpty()) {
                    if (right)
                        sb.append(arrDequeue.pollLast()); // flip
                    else
                        sb.append(arrDequeue.pollFirst());
                    sb.append(",");
                }
                sb.replace(sb.length() - 1, sb.length(), "]");
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}