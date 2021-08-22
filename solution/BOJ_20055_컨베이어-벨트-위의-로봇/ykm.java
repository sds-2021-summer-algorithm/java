package P20055;

import java.io.*;
import java.util.*;

public class Main {
    static int N; //최대100 컨베이어 벨트 길이
    static int K; //최대200 내구도 제한
    static ArrayList<belt> conveyer = new ArrayList<belt>();
    static int countZero = 0;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P20055/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i<2*N ; i++){
            int durabilty = Integer.parseInt(st.nextToken());
            conveyer.add(new belt(i+1, durabilty));
            if(durabilty==0) countZero++;
        }

        int turn = 0;
        if(countZero>=K) System.out.println(0);
        else{
            while(countZero<K){
                turn++;
                // 이동
                move();
                
                // 1번째 칸에 로봇적재
                belt first = conveyer.get(0);
                if(first.durabilty!=0){
                    first.durabilty--;
                    first.robot = true;
                    if(first.durabilty == 0) countZero++;
                }
            }
        }
        System.out.println(turn);
    }

    public static class belt implements Comparable<belt>{
        int position;
        int durabilty;
        boolean robot = false;

        public belt(int position, int durabilty) {
            this.position = position;
            this.durabilty = durabilty;
        }

        @Override
        public String toString() {
            return "belt [durabilty=" + durabilty + ", position=" + position + ", robot=" + robot + "]";
        }

        @Override
        public int compareTo(belt o) {
            return this.position - o.position; // 오름차순
        }
    }

    public static void move(){
        for(int i = 0 ; i<2*N ; i++){
            belt current = conveyer.get(i);
            // 컨베이어 벨트 한칸 이동
            current.position++;
            // 끝위치에 도착하면 위치가 1로 바뀜
            if(current.position > 2*N) current.position = 1;
        }

        Collections.sort(conveyer);
        
        for(int i = N-1 ; i>0 ; i--){
            belt current = conveyer.get(i);
            // 로봇이 올려져 있다면 
            if(current.robot){
                // 마지막 칸에 도착하면 로봇 내림
                if(current.position==N) current.robot =false;

                else{
                    // 전진 가능하면 전진
                    belt next = conveyer.get(i+1);
                    if(next.durabilty !=0 && !next.robot){
                        current.robot = false;
                        next.robot = true;
                        next.durabilty--;
                        if(next.durabilty==0) countZero++;
                    }
                    // 마지막칸에 도착하면 로봇내림
                    if(next.position == N) next.robot = false;
                }
            }
        }
    }
}
