import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static char[][] Map = new char[12][6];
    static boolean[][] isVisited;

    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i<12; i++){
            String line = br.readLine();
            for(int j=0; j<6; j++){
                Map[i][j] = line.charAt(j);
            }
        }
        
        int ans = 0;
        boolean flag = false;
        while(true){ // 턴 구현 필요 -> 한 턴에 여러위치의 뿌요가 터질수도 있음
            flag = false;
            for(int i = 11; i>=0; i--){
                for(int j = 0 ; j<6; j++){
                
                    if(Map[i][j]!='.'){
                        isVisited = new boolean[12][6];
                        isVisited[i][j] = true;
                        int num = count(i,j,Map[i][j],1);
    
                        if(num>=4){
                            bomb(i,j);
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) break;
            gravity();
            ans++;
        }
        System.out.println(ans);
    }

    private static int count(int x, int y, char color, int num){ // x,y와 연결된 color가 같은 뿌요 찾기

        for(int i = 0; i<4; i++){
            int nextX = x + mx[i];
            int nextY = y + my[i];
            if(nextX>=0 && nextY>=0 &&nextX<12 && nextY<6){ // 맵을 벗어나면 안됨
                if(Map[nextX][nextY]==color && !isVisited[nextX][nextY]){
                    isVisited[nextX][nextY]=true;
                    num =  count(nextX, nextY, color, num+1);
                }
            }
        }
        return num;
    }

    private static void bomb(int x, int y){
        Map[x][y] = '.';
        for(int i = 0 ; i<4; i++){
            int nextX = x+mx[i];
            int nextY = y+my[i];
            if(nextX>=0 && nextY>=0 &&nextX<12 && nextY<6){ // 맵을 벗어나면 안됨
                if(isVisited[nextX][nextY]){
                    isVisited[nextX][nextY] = false;
                    bomb(nextX, nextY);
                }
            }
        }
    }

    private static void gravity(){
        for(int i = 10;i>=0; i--){
            for(int j = 0 ; j<6; j++){
                // 현위치에 뿌요가 있고, 아래에 빈공간일 경우
                if(Map[i][j]!='.'){
                    int x = i;
                    int y = j;
                    while(x+1<=11 && Map[x+1][j]=='.'){
                        Map[x+1][y] = Map[x][y];
                        Map[x][y] = '.';
                        x = x + 1;
                    }
                }
            }
        }
    }
}
