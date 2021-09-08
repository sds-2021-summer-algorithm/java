package practice2;

import java.io.*;
import java.util.*;

public class P1330 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        br.close();
        
        //A가 B보다 큰 경우
        if (A > B)
        	System.out.println(">");
        //A가 B보다 작은 경우
        else if (A < B)
        	System.out.println("<");
        //A가 B보다 같은 경우
        else
        	System.out.println("==");
	}

}
