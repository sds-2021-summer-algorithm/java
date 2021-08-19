package DFSBFS.P15649;

import java.util.*;
import java.io.*;
// 연속적인 출력시 string builder를 사용하면 훨씬 빠름.
// sb에 출력할 모든 글자를 넣은후 마지막에 출력하자

public class Main {
    static int N;
    static int M;

    static boolean[] check;
    static int[] answer;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        check = new boolean[N+1]; // 최대 길이 8
        answer = new int[N+1];
        sb = new StringBuilder();
        DFS(0);
        System.out.println(sb);
    }
    
    static public void DFS(int len){
        for(int i = 1; i<N+1 ; i++){
            if(len == M ){
                for(int j = 0; j<len;j++){
                    sb.append(answer[j]).append(' ');
                }
                sb.append('\n');
                return;
            }
            if(!check[i]){
                answer[len]=i;
                check[i]=true;
                DFS(len+1);
                check[i]=false;
            }
        }
            // 1. 체크인
            // 2. 목적지인가
            // 3. 갈 수 있는 곳을 순회
            // 4. 갈수 있는가?
            // 5. 간다
            // 6. 체크아웃
    }

}
