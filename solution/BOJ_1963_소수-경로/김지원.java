import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int A, B;
    static boolean[] prime;

    static class counting{
        int number;
        int cnt;
        public counting(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }
        public int get1(){
            return number%10;
        }
        public int get2(){
            return (number%100)/10;
        }
        public int get3(){
            return (number%1000)/100;
        }
        public int get4(){
            return (number)/1000;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        prime = new boolean[10000];
        makePrimeNumber(); //소수체 만들어주기

        while(T-- > 0){ // 입력값들 계산
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            prime_route(A,B);
        }
    }

    private static void prime_route(int A, int B) {
        boolean[] visit = new boolean[10000];
        visit[A] = true;
        Queue<counting> q = new LinkedList<>();
        q.add( new counting(A,0) );

        while(!q.isEmpty()){
            counting p = q.poll();
            if(p.number == B){
                System.out.println(p.cnt);
                return;
            }
            for(int i=0; i<=9; i++){
                if(i != p.get4()){
                    int next = i * 1000 + p.get3() * 100 + p.get2() * 10 + p.get1();
                    if (!visit[next] && check(next)){
                        visit[next] = true;
                        q.add(new counting(next,p.cnt+1));
                    }
                }

                if(i != p.get3()){
                    int next = p.get4() * 1000 + i * 100 + p.get2() * 10 + p.get1();
                    if (!visit[next] && check(next)){
                        visit[next] = true;
                        q.add(new counting(next,p.cnt+1));
                    }
                }

                if(i != p.get2()){
                    int next = p.get4() * 1000 + p.get3() * 100 + i * 10 + p.get1();
                    if (!visit[next] && check(next)){
                        visit[next] = true;
                        q.add(new counting(next,p.cnt+1));
                    }
                }

                if(i != p.get1()){
                    int next = p.get4() * 1000 + p.get3() * 100 + p.get2() * 10 + i;
                    if (!visit[next] && check(next)){
                        visit[next] = true;
                        q.add(new counting(next,p.cnt+1));
                    }
                }
            }
        }

    }

    private static boolean check(int V){ //조건을 만족하는지 확인하는 함수
        // 소수여야되고, 1000미만은 되지 않는다.
        if(V >= 1000 && !prime[V]){
            return true;
        }
        return false;
    }

    public static void makePrimeNumber(){ //소수를 미리 저장해두기 prime이 false면 소수임
        for(int i=2;i<=9999;i++){
            if(prime[i] == false) {
                for (int j = 2*i; j <= 9999; j += i ) {
                    prime[j] = true;
                }
            }
        }
    }
}
