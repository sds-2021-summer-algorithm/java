package practice2;

import java.io.*;
import java.util.*;

public class P14681 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        
        br.close();
        
        //1 또는 4 사분면
        if (x > 0) {
        	if (y > 0)
        		System.out.println(1);
        	else
        		System.out.println(4);
        }
        //2또는 3 사분면
        else {
        	if (y > 0)
        		System.out.println(2);
        	else
        		System.out.println(3);
        	
        }


	}

}
