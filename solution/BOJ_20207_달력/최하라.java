import java.io.*;
import java.util.*;

public class Main {
    static int N, maxDay;
    static int[][] schedule;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());

        schedule = new int[N][2];
        maxDay = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, schedule[i][1]);
        }

        // 2. sort 2차원 배열
        Arrays.sort(schedule, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });

        // 3. 1차원 배열에 정렬된 일정 입력
        // +1 for 지정날짜 & +1 for 마지막 총 넓이 저장하기위해
        memo = new int[maxDay + 2];
        for (int i = 0; i < N; i++) {
            int start = schedule[i][0];
            int end = schedule[i][1];
            for (int j = start; j <= end; j++) {
                memo[j]++;
            }
        }

        // 4. 총 넓이 구하기
        int maxRow = 0;
        int totalCol = 0;
        int totalArea = 0;
        for (int i = 1; i < memo.length; i++) {
            if (memo[i] != 0) {
                maxRow = Math.max(maxRow, memo[i]);
                totalCol++;
            } else if (totalCol > 0) {
                totalArea += totalCol * maxRow;
                maxRow = 0;
                totalCol = 0;
            }
        }

        bw.write(String.valueOf(totalArea));
        bw.flush();
        bw.close();
        br.close();
    }
}