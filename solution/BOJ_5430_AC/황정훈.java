import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int T, n;
    static char[] p;
    static int[] X;

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        for (int i = 0; i < T; i++) {
            p = br.readLine().toCharArray(); // 함수들 수
            n = Integer.parseInt(br.readLine());
            X = new int[n];

            String input = br.readLine(); // 입력받고
            if(input.length()>2){ // []보다 길면
                String[] nums = input.substring(1, input.length()-1).split(","); // 구분자를 , 로 하여
                for (int j = 0; j < n; j++) {
                    X[j] = Integer.parseInt(nums[j]); // 정수형 배열로 바꿔주고
                }
            }

            boolean error = false; // 에러발생여부
            boolean right = true; // 진행방향
            int l = 0;
            int r = n-1;
            for (char cmd : p){ // 함수하나씩 보면서
                if(cmd=='R') // reverse면
                    right = !right; // right 뒤집어줌
                else{ // D일때
                    if (l>r){ // 빈배열이라면
                        error = true; // 에러발생
                        sb.append("error\n");
                        break; // 즉시종료
                    }
                    if(right)
                        l++;
                    else
                        r--; // 아니면 right여부에 따라 포인터이동.
                }
            }
            if(!error) {
                sb.append("[");
                while(l<=r){
                    sb.append(X[right? l++ : r--]);
                    if(l<=r)
                        sb.append(",");
                }
                sb.append("]\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
