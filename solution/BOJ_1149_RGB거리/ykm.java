import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[][] RGB;
    static int[][] cost; // index번째 까지 드는 최소 비용

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];
        cost = new int[N][3];
        
        // rgb입력받기
        for(int i = 0 ; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j< 3 ;j++){
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cost[0][0] = RGB[0][0];
        cost[0][1] = RGB[0][1];
        cost[0][2] = RGB[0][2];

        for(int i = 1 ; i<N; i++){
            cost[i][0] = RGB[i][0] + Math.min(cost[i-1][1],cost[i-1][2]);
            cost[i][1] = RGB[i][1] + Math.min(cost[i-1][0],cost[i-1][2]);
            cost[i][2] = RGB[i][2] + Math.min(cost[i-1][0],cost[i-1][1]);
        }

        System.out.println(Math.min(Math.min(cost[N-1][0], cost[N-1][1]),cost[N-1][2]));
    }
}
