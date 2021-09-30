import java.io.*;
import java.util.*;

public class Main {
    static long[] temperature;
    static int N, K, S;
    static List<Integer> input = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = 1;
        while(S < 65536) {
            S *= 2;
        }
        temperature = new long[S * 2];
        long answer = 0;
        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(br.readLine());
            input.add(a);
            update(0, 65535, 1, a, 1);
            if(i >= K) {
                // 중간값
                answer += query(0, 65535, 1, (K + 1) / 2);
                // 빼기
                update(0, 65535, 1, input.get(i - K), -1);
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    static int query(int left, int right, int node, int target) {
        if(left == right) return left;

        int mid = (left + right) / 2;

        // 왼쪽에 있는 경우
        if(temperature[2*node] >= target) {
            return query(left, mid, 2*node, target);
        }
        target -= temperature[2 * node];
        return query(mid + 1, right, 2 * node + 1, target);
    }
    static void update(int left, int right, int node, int target, int value) {
        if(target >= left && target <= right) {
            temperature[node] += value;
            if (2 * node < temperature.length) {
                int mid = (left + right) / 2;
                update(left, mid, 2 * node, target, value);
                update(mid + 1, right, 2 * node + 1, target, value);
            }
        }
    }
}

//4
//3
//9
//5
//3
//6
//0
//2
//8
//4
//7
//9
//3
//8
//5
//7
//5
//9
//8
//3
//1
//1