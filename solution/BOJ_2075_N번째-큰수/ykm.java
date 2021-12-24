import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[][] nums;
    static int[] pointers;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N][N];
        pointers = new int[N];

        for(int i = 0 ; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
            pointers[i] = N-1;
        }

        int col = 0;
        for(int i = 0 ; i<N-1; i++){
            col = findMaxCol(pointers);         // 현재 pointer들이 가리키는 수 중 가장 큰 수 찾기
            pointers[col]--;                    // 가장 큰수를 갖는 col의 pointer이동
        }
        col = findMaxCol(pointers);

        System.out.println(nums[pointers[col]][col]);
        br.close();
    }

    private static int findMaxCol(int[] pointers) {
        int col = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<N; i++){
            int numPointed = nums[pointers[i]][i];
            if(numPointed > max){
                max = numPointed;
                col = i;
            }
        }
        return col;
    }
}
