import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static HashMap<String, ArrayList<String>> tree = new HashMap<>();
    static class Node{
        String str;
        int cnt;
        public Node(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {

        makePrimes();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            sb.append(calc(A, B)+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean isPrime(int n){ // 소수 확인 함수
        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    static void makePrimes(){
        Set<String> primes = new HashSet<>();
        for (int i = 1001; i < 10000; i+=2) { // 1001~9999까지의 모든 홀수를
            primes.add(Integer.toString(i)); // primes 셋에 넣고
        }
        for (int i = 3; i < 100; i+=2) { // 3부터 100까지의 모든 홀수중
            if (isPrime(i)){ // 소수가 있으면,
                Set<String> tmp = new HashSet<>();
                for (int j = i+i; j < 10000; j+=i) { // 그 소수를 제외한 모든 배수를
                    tmp.add(Integer.toString(j));
                }
                primes.removeAll(tmp); // primes에서 빼고
            }
        }
        for(String num : primes){ // 모든 소수를
            tree.put(num, new ArrayList<>()); // 루트로 하는 트리를 만들고
        }
        ArrayList<String> primelist = new ArrayList<>(primes);
        for (int i = 0; i < primelist.size()-1; i++) {
            String A = primelist.get(i);
            for (int j = i+1; j < primelist.size(); j++) {
                String B = primelist.get(j);
                if(compare(A, B)==3){ // 두 수에서 일치하는 수가 3개이면 양방향 노드 추가
                    tree.get(A).add(B);
                    tree.get(B).add(A);
                }
            }
        }
    }
    static int compare(String A, String B){
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if(A.charAt(i)==B.charAt(i))
                cnt++;
        }
        return cnt;
    }
    static String calc(String S, String goal){ // bfs 함수
        if (S.equals(goal))
            return "0";

        Set<String> chk = new HashSet<>();
        Queue<Node> Q = new ArrayDeque<>();
        Q.add(new Node(S, 0));

        while(!Q.isEmpty()){
            Node cur = Q.poll();
            if (cur.str.equals(goal))
                return Integer.toString(cur.cnt);
            chk.add(cur.str);
            for(String next : tree.get(cur.str)){
                if(!chk.contains(next))
                    Q.add(new Node(next, cur.cnt+1));
            }
        }
        return "Impossible";
    }
}
