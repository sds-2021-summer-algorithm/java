package P16234; //유니온 파인드?

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 폭
    static int num; // 실제 나라수
    static int[] country;
    static boolean[][] neighbor; // 각 나라별 국경 오픈 여부
    static int max;
    static int min;

    static class Road{
        int cityNum;
        boolean isOpen = false;

        @Override
        public String toString() {
            return "Road [cityNum=" + cityNum + ", isOpen=" + isOpen + "]";
        }

        public Road(int cityNum) {
            this.cityNum = cityNum;
        }
    }


    public static void main(String[] arg) throws IOException{
        System.setIn(new FileInputStream("P16234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        num = (int)Math.pow(N,2);
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        country = new int[num+1];
        neighbor = new boolean [num+1][num+1]; // 이웃표시

        for(int i = 1 ; i<=N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=N ; j++){
                int current = (i-1) * N + j;
                country[current] = Integer.parseInt(st.nextToken());
            } 
        }


        int count = 0;
        resetOpen();
        while(true){
            if(!check()) continue;
            count++;
            makeAvg();
        }

        System.out.print(count);
    }

    // 인구 평균
    public static void makeAvg(){
        for(int i =1 ; i<=num ; i++){
            int sum = country[i];
            int count = 1;
            for(int j = 1 ; j<=num; j++){
                if(neighbor[i][j]){
                    sum += country[j];
                    count++;
                }
            }
            sum /= count;
            country[i] = sum;
            for(int j = 1 ; j<=num; j++){
                if(neighbor[i][j]){
                    country[j] = sum;
                    neighbor[i][j]=false;
                }
            }

        }

    }

    // 국경을 여는 나라가 있는지 확인
    public static boolean check(){
        boolean flag = false;
        for(int i = 1 ; i<=num ; i++){
            int people = country[i];
            int up=i-N;
            int down=i+N;
            int left=i-1;
            int right=i+1;

            if(up>0 && Math.abs(people-country[up]) >=min && Math.abs(people-country[up])<=max){
                neighbor[i][up] = true;
                neighbor[up][i] = true;
                flag = true;
            }
            if(down<=num && Math.abs(people-country[down]) >=min && Math.abs(people-country[down])<=max){
                neighbor[i][down] = true;
                neighbor[down][i] = true;
                flag = true;
            }
            if(i%N !=1 && left>0 && Math.abs(people-country[left]) >=min && Math.abs(people-country[left])<=max){
                neighbor[i][left] = true;
                neighbor[left][i] = true;
                flag = true;
            }
            if(i%N !=0 && right<=num && Math.abs(people-country[right]) >=min && Math.abs(people-country[right])<=max){
                neighbor[i][right] = true;
                neighbor[right][i] = true;
                flag = true;
            }
        }
        return flag;
    }

    public static void resetOpen(){
        for(int i =1 ; i<=num ; i++){
            for(int j=1 ; j<=num; j++){
                neighbor[i][j] = false;
            }
        }
    }
}
