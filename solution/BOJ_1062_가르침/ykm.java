package DAY01.P1062;

import java.util.*;
import java.io.*;

public class Main {
    static int N; // 단어수
    static int K; // 알파벳수
    static String[] list; // anta, tica를 제외한 단어 부분
    static int answer = 0;
    static boolean []learned = new boolean[26]; // 배운 알파벳

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("DAY01/P1062/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new String[N];        

        for(int i = 0; i<N ; i++){
            String word = br.readLine();
            list[i]=(word.substring(4, word.length()-4));
        }

        if(K<5){
            System.out.println(0);
        }else{
            K = K - 5;
            learned[0] =true; // a
            learned[13] =true; // n
            learned[19] = true; // t
            learned[8] = true; // i
            learned[2] = true; // c
            DFS(0);
            System.out.println(answer);
        }
    }

    // index이후에 존재하는 알파벳만 선택 가능
    public static void DFS(int index){
        for(int i = index ; i< 26 ; i++){        
            // K개의 알파벳을 더 배울수 있음.
            if(K==0) {
                answer = Math.max(answer,check());
                break;
            }else{
                if(!learned[i]){
                    K--;
                    learned[i]=true;
                    DFS(i);
                    learned[i]=false;
                    K++;
                }
            }
        }
    }

    // antic이외에 배운 알파벳을 받아서 읽을수 있는 단어의 개수 출력
    public static int check(){
        int count = N;
        for(int i = 0 ; i<N ; i++){
            String word = list[i];
            for(int j = 0 ; j<word.length(); j++){
                if(!learned[word.charAt(j) - 'a']){
                    count--;
                    break;
                }
            }
        }
        return count;
    }
}
