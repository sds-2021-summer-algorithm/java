import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static ArrayList<Integer>[] subjects;
    static int[] limit;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        subjects = new ArrayList[N];
        limit = new int[N];

        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            limit[i] = Integer.parseInt(st.nextToken());
            subjects[i] = new ArrayList<Integer>();
    
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<p; j++){
                subjects[i].add( - Integer.parseInt(st.nextToken()) );
            }
            Collections.sort(subjects[i]);

            if(subjects[i].size()>= limit[i]) limit[i] = - subjects[i].get(limit[i]-1);
            else limit[i] = 1;
        }

        Arrays.sort(limit);
        int ans = 0;
        boolean flag = false;
        for(int i = 0 ; i<N; i++){
            M-=limit[i];
            ans ++;
            if(M<0) {
                flag = true;
                break;
            }
        }
        if(flag) System.out.println(ans-1);
        else System.out.println(ans);
    }
}