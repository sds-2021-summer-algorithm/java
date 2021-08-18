import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int x,y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        //1 10 12 32 56 120 232 472 936 1880
        //dp 문제인데 점화식 1시간 생각해도 안나옴 + 풀이 없음... 내일 꼭풀고만다!!
    }
}
