import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Integer.parseInt(st.nextToken());// 집 위치 x좌표
        long y = Integer.parseInt(st.nextToken());// 집 위치 y좌표
        long w = Integer.parseInt(st.nextToken());// 평행 이동
        long s = Integer.parseInt(st.nextToken());// 대각선 이동

        // 평행 이동
        long move1 = (x + y) * w;

        // 짝수, 홀수에 따른 이동
        long move2;
        if ((x + y) % 2 == 0) { // x + y가 짝수면
            move2 = Math.max(x, y) * s;
        } else { // x + y가 홀수면
            move2 = (Math.max(x, y) - 1) * s + w;
        }

        // 대각선 + 평행 이동
        long move3 = (Math.min(x, y) * s) + (Math.abs(x - y) * w);

        // 가장 짧은 동선
        System.out.println(Math.min(Math.min(move1, move2), move3));
    }
}
