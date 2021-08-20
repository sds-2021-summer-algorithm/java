import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	static int[] arr;
	static ArrayList<Integer> left, right;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        	arr[i] = Integer.parseInt(st.nextToken());

        
        left = new ArrayList<>();
        combi(0, 0, N/2, left); // 왼쪽 절반 조합
        left.sort(null);
        right = new ArrayList<>();
        combi(0, N/2, N, right); //오른쪽 절반 호잡
        right.sort(null);
        
        int l = 0;
        int r = right.size()-1;
        long cnt = 0;
        
        while(l<left.size() && r>=0) {
        	int lv = left.get(l);
        	int rv = right.get(r);
        	if(lv+rv == S) { // S랑 같으면
        		long lc = 0;
        		do{
        			lc++;
        			l++;
        		} while(l<left.size() && left.get(l)==lv);
        		long rc = 0;
        		do{
        			rc++;
        			r--;
        		}while(r>=0 && right.get(r)==rv);
        		cnt += lc*rc;
        	}
        	else if(lv+rv < S) // S보다 작으면
        		l++;
        	else // S보다 크면
        		r--;
        }
        if(S==0)
        	cnt--;
        bw.write(cnt+"\n");
                
        bw.flush();
        bw.close();
        br.close();
    }
    static void combi(int sum, int s, int e, ArrayList<Integer> list) {
    	if(s==e) {
    		list.add(sum);
    		return;
    	}
    	combi(sum, s+1, e, list); // s번째를 선택 X
    	combi(sum + arr[s], s+1, e, list); // s번째를 선택 O
    }
}
