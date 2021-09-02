import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        int min = Integer.MAX_VALUE;
        while (left < right && right < N) {
            int value = A[left] + A[right];
            int absValue = Math.abs(value);
            if(Math.abs(min) > absValue) {
                min = value;
            }
            if (value < 0) {
                left++;
            } else {
                right--;
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}
