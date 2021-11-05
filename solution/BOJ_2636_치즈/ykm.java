package P2636;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;         // 치즈 위치
    static int area;            // 치즈의 넓이

    public static void main(String[] args) throws IOException {
	// write your code here
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader( System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        area = height*width;

        for(int i = 0 ; i<height; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<width; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) area--;
            }
        }

        int t = 0;
        int answer = area;
        while(area>0){
            answer = area;
            turn(height, width);
            t ++;
        }
        System.out.println(answer);
        System.out.println(t);

    }

    // 치즈가 줄어든후 넒이
    private static void turn(int height, int width) {
        
        // 가장 자리에 있는 치즈의 위치를 저장
        Queue<int[]> q = new LinkedList<int[]>();
        
        for(int i = 0 ; i<height-1; i++){
            for(int j = 0 ; j<width-1; j++){
                if(map[i][j]-map[i+1][j]!=0) {
                    if(map[i][j]==1){
                        q.add(new int[]{i,j});
                    }else if(map[i+1][j]==1){
                        q.add(new int[]{i+1, j});
                    }
                }else if(map[i][j]-map[i][j+1]!=0){
                    if(map[i][j]==1){
                        q.add(new int[]{i,j});
                    }else if(map[i][j+1]==1){
                        q.add(new int[]{i, j+1});
                    }
                }

            }
        }

        // 저장된 위치의 치즈를 제거
        while(!q.isEmpty()){
            int[] current = q.poll();
            if(map[current[0]][current[1]] == 1){
                map[current[0]][current[1]] = 0;
                area--;
            }

        }
    }
}
