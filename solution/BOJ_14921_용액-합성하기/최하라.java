import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());

        // 배열 저장
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // 2. 투 포인터 사용해서 0에 가까운 용액 찾기
        // 음수가 없을 때 첫번 째와 두번째 합이 제일 작다
        if (arr[0] > 0 && arr[1] > 0) {
            System.out.println(arr[0] + arr[1]);
            return;
        }

        // 음수 있을 때
        int left = 0, right = arr.length - 1;
        int minDiff = Integer.MAX_VALUE; // 최소 차이 값
        while (left < right) {
            int sum = arr[left] + arr[right]; // 두 용액의 합

            if (sum < 0) {
                minDiff = Math.abs(sum) < Math.abs(minDiff) ? sum : minDiff; // 최소 차이 값 저장
                left++;
            } else if (sum > 0) {
                minDiff = Math.abs(sum) < Math.abs(minDiff) ? sum : minDiff; // 최소 차이 값 저장
                right--;
            } else {
                System.out.println(0);
                return;
            }
        }
        System.out.println(minDiff);
    }
}