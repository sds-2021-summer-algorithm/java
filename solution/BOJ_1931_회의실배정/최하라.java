import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Build schedule
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        // Sort base on end time
        Arrays.sort(schedule,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                else
                    return o1[1] - o2[1];
            }
        });

        // 끝나는 지점에서 그 이후 방 찾기
        int end = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (end <= schedule[i][0]) {
                count++;
                end = schedule[i][1];
            }
        }
        System.out.println(count);
    }
}