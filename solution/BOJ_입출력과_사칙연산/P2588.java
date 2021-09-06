package practice;

import java.io.*;
import java.util.*;

public class P2588 {
	public static void main(String[] args) throws Exception{
		//선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //한줄 전체 입력 받을 때
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        

        br.close();
        
        System.out.println(A * (B%10));
        System.out.println(A * ((B%100)/10));
        System.out.println(A * (B/100));
        System.out.println(A * B);
	}

}
