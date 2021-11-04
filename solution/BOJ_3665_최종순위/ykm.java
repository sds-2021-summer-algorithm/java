import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[] team; // 작년 순위
    static int[] rank; // index보다 순위가 높은 팀의 수


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<N; i++){
            int a = Integer.parseInt(br.readLine());
            team = new int[a + 1];
            rank = new int[a + 1];

            // 작년 순위 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<a; j++){
                rank[Integer.parseInt(st.nextToken())] = j;
            }

            // 바뀐 순위 입력받기
            int b = Integer.parseInt(br.readLine());
            for(int j = 0 ; j<b; j++){
                st = new StringTokenizer(br.readLine());
                int up = Integer.parseInt(st.nextToken());
                int down = Integer.parseInt(st.nextToken());

                rank[up]--;
                rank[down]++;
            }

            if(checkRank()){
                printRank();
            }
            
        }
    }


    private static boolean checkRank() {
        for(int i = 1 ; i<rank.length ; i++){
            if(rank[i]<0 || rank[i]>=rank.length-1) {
                System.out.println("IMPOSIBBLE");
                return false;
            }else{
                team[rank[i]] = i;
            }
        }
        return true;
    }


    private static void printRank() {
        boolean[] check = new boolean[rank.length];
        boolean flag = true;
        for(int i = 1; i< rank.length; i++){
            int current = rank[i];

            if(check[current]){
                flag = false;
            }else{
                check[current] = true;
            }
        
        }
        if(!flag) {
            System.out.println("?");
            return;
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j<team.length-1; j++){
                sb.append(team[j]);
                if(j!=team.length-2) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
