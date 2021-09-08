package practice2;

import java.io.*;
import java.util.*;

public class P2753 {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int year = Integer.parseInt(br.readLine());
        
        br.close();
        
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
        	System.out.println(1);
        else
        	System.out.println(0);

	}

}
