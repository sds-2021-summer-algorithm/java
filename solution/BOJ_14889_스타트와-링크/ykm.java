package P14889;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 사람수
    static int[][] table; // 능력치 조합
    static boolean[] check;
    static int min = Integer.MAX_VALUE; // -> arrayList로 전부 저장하니까 시간소요가 두배정도 됨
    static int C = 1;

    public static void main(String[ ] args) throws IOException{
        System.setIn(new FileInputStream("P14889/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        table  = new int[N+1][N+1];
        check = new boolean[N+1];

        // 입력받기
        for(int i = 1 ; i<= N; i++){
            check[i]=false;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=N ; j++){
                table[i][j] = Integer.parseInt(st.nextToken());
                if(i>j){ // 같은 팀이 될 능력치 합하기
                    table[i][j] += table[j][i];
                }
            }
        }

        // 팀능력치 합치기 - 모든 케이스 확인 필요
        // 모든 조합의 경우의 수 N C N/2
        combi(1,0,0);
        System.out.println(min);

    }

    // 현재 양 팀의 능력치
    // 확인할 사람
    public static void combi(int current, int count, int depth){ 
        // start팀과 link팀이 대칭인 경우는 제외해도 됨 -> 어떻게 하지..흠..

        if(count == N/2){
            int start = 0; // 팀별 능력치 합
            int link = 0;
            for(int i = 1; i<=N ; i++){ // i를 2부터?
                for(int j = 1 ; j<=i ; j++){ // i+1 진행
                    if(i==j) continue;
                    if(check[i]){// 방문한 사람 -> start
                        if(check[j]) start += table[i][j];
                        
                    }else{// 방문 전인 사람 -> link
                        if(!check[j]) link += table[i][j];
                    }
                }
            }
            if(min > (Math.abs(start-link))) min = Math.abs(start-link);    
            return;
        }

        for(int i = current; i<=N; i++){
            if(!check[i]){
                check[i] = true; // 방문 표시
                combi(i+1, count+1, depth+1); // 재귀 호출
                check[i] = false; // 재귀가 끝나면 방문하지 않음으로 표시
            }
        }
    }
}
