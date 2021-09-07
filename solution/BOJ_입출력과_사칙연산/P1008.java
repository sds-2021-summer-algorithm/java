package practice;

import java.io.*;
import java.util.*;

public class P1008 {
	public static void main(String[] args) throws Exception{
		//선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // space를 경계로 끊어야할 때 사용
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        double A = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());
        
        System.out.println(A / B);
	}

}
