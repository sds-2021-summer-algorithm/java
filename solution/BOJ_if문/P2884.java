package practice2;

import java.io.*;
import java.util.*;

public class P2884 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        br.close();
        
        if (M >= 45) {
        	System.out.print(H + " ");
        	System.out.println(M-45);
        }
        else {
        	H--;
        	if (H < 0) {
        		H = 23;
        	}
        	System.out.print(H + " ");
        	System.out.println(M+15); //M+60-45 ÀÌ´Ï±î
        }

	}

}
