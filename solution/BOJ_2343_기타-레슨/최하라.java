import java.io.*;
import java.util.*;

public class Main {
    static int N, M, low, high;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // build arr
        arr = new int[N];
        low = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            low = Math.max(low, arr[i]);
        }
        high = total; // 최대 가능한 합 저장

        // 2. 이분탐색 활용
        binarySearch();

        System.out.println(low);
    }

    static void binarySearch() {
        while (low <= high) {
            int mid = low + high >> 1;
            int sum = 0, count = 0; // count == 블루레이 수

            for (int i = 0; i < N; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    count++;
                }
                sum += arr[i];
            }

            if (sum > 0)
                count++;
            if (count <= M)
                high = mid - 1;
            else
                low = mid + 1;
        }
    }
}