package P20055;

import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[] belt;
    public static boolean[] robot;
    public static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        belt = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        left = 0;
        right = N;
        
       int cnt = 0;
       while (K > 0) {
    	   cnt ++;
    	   moveBelt();
    	   moveRobot();
    	   newRobot();
       }
        

        bw.write( cnt + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
    
    public static void moveBelt() {
    	left--;
    	right--;
    	if (left == -1) {
    		left = 2 * N - 1;
    	}
    	if (right == -1) {
    		right = 2 * N - 1;
    	}
    	
    	for (int i = N -2; i >= 0; i--) {
    		if(robot[i]) {
    			robot[i] = false;
    			if ( i + 1 < N -1) {
    				robot[i + 1] = true;
    			}
    		}
    	}
    	
    }
    
    public static void moveRobot() {
        for(int i = N-2; i >= 0; i--) {
            if(robot[i]) {
                int next = left + i + 1;
                if(next >= 2 * N) {
                	next -= 2 * N;
                }
                    
                if(!robot[i+1] && belt[next] >= 1) {
                    robot[i] = false;
                    if( i+1 < N-1) {
                    	robot[i + 1] = true;
                    }
                      
                    belt[next]--;
                    if(belt[next] == 0) {
                    	K--;
                    }
                }
            }
        }
    }
    
    public static void newRobot() {
        if(!robot[0] && belt[left] > 0) {
            robot[0] = true;
            belt[left]--;
            if(belt[left] == 0) {
            	K--;
            }
        }
    }
}
