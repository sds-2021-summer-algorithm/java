import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. sort array
        Arrays.sort(arr);

        // 3. find min val
        int minVal = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > minVal + 1)
                break;
            minVal += arr[i];
        }

        bw.write(String.valueOf(minVal + 1));
        bw.flush();
        bw.close();
        br.close();
    }
}