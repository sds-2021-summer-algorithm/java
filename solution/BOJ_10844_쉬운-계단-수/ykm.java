import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int N;
    static int mod = 1000000000;
    static int[][] stair; 
    // stair[a][b] 길이가 a이고, 숫자 b로 시작하는 계단수의 개수
    //             길이가 a이고, 숫자 b로 끝나는 계단수의 개수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stair = new int[N+1][10];
        for(int i = 1; i<10; i++){
            stair[1][i] = 1;
        }
        stair[1][0] = 0;
        
        countStair(N);

        long answer = 0;
        for(int i = 0; i<10; i++){
            answer+=stair[N][i];
            answer %= mod;
        }
        System.out.println(answer);
    }

    private static void countStair(int N) { // N번째 인덱스까지 모두 채워야함.
        for(int i = 2; i<=N; i++){
            for(int j = 1; j<9; j++) stair[i][j] = (stair[i-1][j-1] + stair[i-1][j+1]) % mod;
            stair[i][9] = stair[i-1][8] % mod; 
            stair[i][0] = stair[i-1][1] % mod;
        }
    }
}
