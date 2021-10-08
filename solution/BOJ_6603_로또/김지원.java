import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] arr;
    static boolean[] visit;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equals("0")) {
                System.exit(0);
            }
            StringTokenizer st = new StringTokenizer(input);
            K = Integer.parseInt(st.nextToken());
            arr = new int[K];
            visit = new boolean[K];
            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0,0);
            System.out.println();
        }


    }

    private static void dfs(int start, int depth) throws IOException {
        if(depth == 6){
            for (int i = 0; i < K; i++) {
                if(visit[i]){
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
        }
        else{
            for(int i =start; i<K; i++){
                visit[i] = true;
                dfs(i+1, depth+1);
                visit[i]= false;
            }
        }


    }
}
