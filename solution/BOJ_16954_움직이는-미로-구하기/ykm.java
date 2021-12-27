import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class point implements Comparable<point>{
        int x, y;
        int turn;

        public point(int x, int y, int turn){
            this.x = x;
            this.y = y;
            this.turn = turn;
        }

        @Override
        public int compareTo(Main.point o) {
            if(this.turn==o.turn){
                return o.y - this.y;
            }
            return this.turn - o.turn;
        }

        @Override
        public String toString() {
            return "point [turn=" + turn + ", x=" + x + ", y=" + y + "]";
        }  
    }

    static String[] map = new String[8];
    static int[] mx = {0,0,0,-1,-1,-1,1,1,1};
    static int[] my = {0,-1,1,0,-1, 1,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0 ; i<8; i++){
            map[i] = br.readLine();
        }

        Queue<point> q = new PriorityQueue<point>();
        boolean isArrived = false;
        q.add(new point(7,0,0));

        int turn = 0;
        while(!q.isEmpty()){
            point current = q.poll();

            if(current.x==0 && current.y==7){
                isArrived = true;
                break;
            }

            if(current.turn != turn){
                turn++;
                if(turn==8){    // 벽이 다 내려온 상태
                    isArrived=true;
                    break;
                }
                moveMap();
            }

            if(!isValidPosition(current)){
                continue;
            }

            for(int i = 0; i<9; i++){
                int nextX = current.x + mx[i];
                int nextY = current.y + my[i];

                if(nextX<0 || nextY<0 || nextX>=8 || nextY>=8){ // 맵 범위 밖
                    continue;
                }

                if(map[nextX].charAt(nextY)=='#'){ // 현재벽이 있는 곳으로는 이동불가
                    continue;
                }

                point next = new point(nextX,nextY,current.turn+1);
                q.add(next); // 같은 위치가 추가 되는 경우가 많음.
            }
        }

        if(isArrived){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static void moveMap(){ // 매초 map이 아래로 움직임 
        for(int i = 7; i>0; i--){
            map[i] = map[i-1];
        }
        map[0] = "........";
    }

    private static boolean isValidPosition(point current){ // 벽이 curPosition에 위치하게 되었는지 확인
        if(map[current.x].charAt(current.y)=='#'){
            return false;
        }
        return true;
    }
}
