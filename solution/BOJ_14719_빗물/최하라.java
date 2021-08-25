import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int W, H, totalArea, leftMax, rightMax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        // build array
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // 2. 총 물이 고이는 넓이 구하기
        totalArea = 0;
        for (int i = 0; i < W; i++) {
            // reset values
            leftMax = 0;
            rightMax = 0;

            // leftMax 구하기
            for (int j = 0; j < i; j++)
                leftMax = Math.max(arr[j], leftMax);

            // rightMax 구하기
            for (int j = i + 1; j < W; j++)
                rightMax = Math.max(arr[j], rightMax);

            // 둘 중 낮은 건물 기준으로 넓이 구하기
            int limit = Math.min(leftMax, rightMax);
            if (arr[i] < leftMax && arr[i] < rightMax)
                totalArea += limit - arr[i];

        }

        bw.write(String.valueOf(totalArea));
        bw.flush();
        bw.close();
        br.close();
    }
}