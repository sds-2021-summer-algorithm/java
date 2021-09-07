package practice;

import java.io.*;
import java.util.*;

public class P10430 {
	public static void main(String[] args) throws Exception{
		//선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // space를 경계로 끊어야할 때 사용
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        System.out.println((A + B)%C);
        System.out.println(((A % C) + (B % C)) % C);
        System.out.println((A * B) % C);
        System.out.println(((A % C) * (B % C)) % C);
	}

}
