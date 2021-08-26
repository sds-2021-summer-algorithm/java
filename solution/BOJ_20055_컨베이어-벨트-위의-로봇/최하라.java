import java.io.*;
import java.util.*;

public class Main {
    static int N, K, countZero, runTotal, limit;
    static int[] arr;
    static boolean[] robotP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 2. 배열 만들기
        limit = 2 * N;
        arr = new int[limit + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= arr.length - 1; i++)
            arr[i] = Integer.parseInt(st.nextToken()); // 올리는 위치

        // 3. 벨트 회전하기
        runTotal = 1;
        robotP = new boolean[limit + 1];
        runBelt();

        bw.write(String.valueOf(runTotal));
        bw.flush();
        bw.close();
        br.close();
    }

    static void runBelt() {
        int upPoint = 1;
        int downPoint = N;

        while (true) {
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            upPoint--;
            if (upPoint < 1)
                upPoint = limit;
            downPoint--;
            if (downPoint < 1)
                downPoint = limit;
            // 내릴위치 로봇 내리기
            robotP[downPoint] = false;

            // 2. 로봇 이동
            for (int i = N - 1; i >= 0; i--) {
                // 현재 위치 조정
                int current = upPoint + i;
                if (current > limit)
                    current -= limit;

                // 다음 위치 조정
                int moveTo = current + 1;
                if (moveTo > limit)
                    moveTo = 1;

                // 로봇이 없다
                if (!robotP[current])
                    continue;

                // 로봇 이동 가능
                if (arr[moveTo] > 0 && !robotP[moveTo]) {
                    arr[moveTo]--; // 내구도 -1

                    // 옮긴곳이 내리는곳인지 확인
                    if (moveTo != downPoint)
                        robotP[moveTo] = true;
                    // 로봇 다음칸으로 옮겨감
                    robotP[current] = false;

                    // 내구도 0안자 확인
                    if (arr[moveTo] == 0)
                        countZero++;
                }
            }

            // 3. upPoint에 로봇 올리기
            if (arr[upPoint] > 0 && !robotP[upPoint]) {
                robotP[upPoint] = true;
                arr[upPoint]--;

                // 내구도가 0인지 확인
                if (arr[upPoint] == 0)
                    countZero++;
            }

            // 4. 내구도가 0인 칸이 K개 이상이라면 종료
            if (countZero >= K)
                return;

            // 5. 단계 1 증가
            runTotal++;
        }
    }
}