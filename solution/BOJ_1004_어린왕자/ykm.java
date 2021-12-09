import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    static class planet{
        int x,y;
        int r;

        boolean isContainStartPoint = false;
        boolean isContainDestPoint = false;

        public planet(int x, int y, int r){
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public void checkContain(int newX, int newY, boolean isStartPoint){
            // 두 점사이의 거리가 반지름 이하이면 행성계에 포함
            double distance = Math.sqrt(Math.pow(x-newX,2) + Math.pow(y-newY,2));

            if(isStartPoint){
                if(distance<r){
                    isContainStartPoint = true;
                }
            }else{
                if(distance<r){
                    isContainDestPoint = true;
                }
            } 
        }

        public boolean checkBorder(){
            // 두 점이 모두 같은 행성계에 포함되면 경계밖으로 나가지 않음
            if(isContainStartPoint == isContainDestPoint) return false;
            else return true;
        }
    }
    
    public static void main(String[] args)throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        while(num-->0){           
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int destX = Integer.parseInt(st.nextToken());
            int destY = Integer.parseInt(st.nextToken());

            int planetNum = Integer.parseInt(br.readLine());
            planet[] planets = new planet[planetNum];
            int count = 0;

            for(int i = 0; i<planetNum; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                planets[i] = new planet(x, y, r);
                planets[i].checkContain(startX, startY, true);
                planets[i].checkContain(destX, destY, false);

                if(planets[i].checkBorder()) count++;
            }
            sb.append(count+"\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }
}
