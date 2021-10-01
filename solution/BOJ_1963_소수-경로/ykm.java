import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static class Num{
        String num;
        int count;

        public Num(String num, int count) {
            this.num = num;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return "Num [count=" + count + ", num=" + num + "]";
        }
    }

    static int T;
    static boolean[] isPrime;                               // 소수가 맞다면 T, 소수가 아니라면 
    static boolean[] isChecked;                             // 소수인지 확인 했다면 T
    static boolean[] isVisited;                             // 같은 소수로 돌아가지 않기위해 사용
    static Queue<Num> q = new LinkedList<Num>();
    static boolean ansFlag;                                 // 큐에서 b를 찾으면 T

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        isPrime = new boolean[10000];
        isChecked = new boolean[10000];  
        T = Integer.parseInt(br.readLine());

        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            int int_a = Integer.parseInt(a);
            int int_b = Integer.parseInt(b);
            isPrime[int_a] = true;
            isChecked[int_a] = true;
            isPrime[int_b] = true;
            isChecked[int_b] = true;
            
            isVisited = new boolean[10000];
            isVisited[int_a] = true;
            q = new LinkedList<Num>();
            q.add(new Num(a, 0));
            
            ansFlag = false;
            while(!q.isEmpty()){
                Num current = q.poll();

                if(current.num.equals(b)) {
                    ansFlag = true;
                    bw.write(current.count+"\n");
                    break;
                }

                for(int i = 0; i<4; i++){
                    change(current.num, i, current.count);
                }
            }

            if(!ansFlag) bw.append("Impossible\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // a의 index번째 자리를 바꿔보자
    // 바꿔본 수가 소수라면 q에 저장
    public static void change(String a, int index, int count) {
        for(int i = 0;i<10; i++){

            String temp; 
            if(index==0){
                if(i==0) continue;
                temp = Integer.toString(i) + a.substring(1,4);
            }
            else temp = a.substring(0, index) + Integer.toString(i) + a.substring(index+1, 4);
            
            if(check(temp)){
                int int_temp = Integer.parseInt(temp);
                if(isVisited[int_temp]) continue; // 큐에 저장한적 있다면 다시 저장 않기
                isVisited[int_temp] = true;
                q.add(new Num(temp, count+1));
            }
        }
    }

    // a가 소수 인지 확인
    public static boolean check(String a){
        int int_a = Integer.parseInt(a);
        if(isChecked[int_a]){
            return isPrime[int_a];
        }
        else{
            isChecked[int_a] = true;
            for(int i = 2; i<Math.sqrt(int_a)+1; i++){
                if(int_a % i==0) {
                    return isPrime[int_a] = false;
                }
            }
        }
        return isPrime[int_a] = true;
    }
}
