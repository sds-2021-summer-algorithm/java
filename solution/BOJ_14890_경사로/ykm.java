package P14890;

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 100
    static int L; // 100
    static int[][] map; // 10
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P14890/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int [N][N];

        for(int i =0; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i<N ; i++){
            if(checkRows(map[i])) ans++;

            int[] col = new int[N];
            for(int j = 0 ; j<N ; j++){
                col[j] = map[j][i];
            }
            if(checkRows(col)) ans++;
        }
        System.out.println(ans);
        br.close();
    }

    // 경사로를 놓는것이 불가능하면 false를 리턴
    public static boolean checkRows(int[] x){
        boolean flag = false;
        int count = 0;
        boolean[] visit = new boolean[x.length];

        // 내려가는 경사로
        for(int i = 1 ; i< x.length; i++){ 
            int diff = x[i-1] - x[i];
            if(diff==0){
                if(flag){ // 경사로가 필요한 경우
                    if(count<L){
                        count++;
                        visit[i] = true;
                    }else{
                        flag = false; // 경사로를 만드는 데 필요한 평지의 개수를 충족한 경우
                    }
                }
            }else if(diff==1){
                if(L==1){
                    visit[i] = true;
                }else{
                    if(flag) return false;
                    flag = true;
                    visit[i] = true;
                    count = 1;
                }
            }else if(diff>=2){
                return false;
            }
        }
        if(flag && count<L) return false;

        // 올라가는 경사로
        for(int i = x.length-2 ; i>=0; i--){ 
            int diff = x[i+1] - x[i];
            if(diff==0){
                if(flag){
                    if(count < L){
                        if(visit[i]) return false; // 경사로가 놓인곳에 경사로를 놓으려함
                        count++;
                        visit[i] = true;
                    }else{
                        flag = false;
                    }     
                }
            }else if(diff==1){
                if(L==1){
                    if(visit[i]) return false;
                }else{
                    if(flag) return false;
                    flag = true;
                    count = 1;
                }
            }else if(diff>=2){
                return false;
            }
        }

        if(flag && count<L) return false;   
        return true;

    }
}
