package P18428;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static char[][] map; // T: teacher, S:student , W:wall , V:visit
    static ArrayList<int[]> positionT = new ArrayList<int[]>(); // inital position of teachers
    static int[] mx = {-1,1,0,0};
    static int[] my = {0,0,-1,1};
    static boolean ansFlag = false;
  
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P18428/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0 ; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<N; j++){
                String person = st.nextToken();
                if(person.equals("S")){
                    map[i][j]='S';
                }else if(person.equals("T")){
                    map[i][j]='T';
                    positionT.add(new int[] {i,j});
                }else{
                    map[i][j]='\0';
                }
            }
        }

        // 벽세우기 (dfs)
        wall(0,0,0);
        if(!ansFlag) System.out.println("NO");
        br.close();
    }
    static void wall(int count, int x, int y){

        if(count==3){
            // 확인하기
            if(check()) {
                ansFlag = true;
                System.out.println("YES");
                System.exit(1);
            }
            return;
        }
        else{
            for(int i = x ; i< N ; i++){
                for(int j =(i==x? y:0 ); j<N ; j++){
                    if(map[i][j]=='\0'){
                        map[i][j]='W';
                        wall(count+1,i,j);
                        map[i][j]='\0';
                    }
                }
            }
        }
    }

    static boolean check(){
        for(int i = 0 ; i<positionT.size(); i++){
            int[] position = positionT.get(i);

            for(int j = 0 ; j<4 ; j++){
                int m = 0;
                while(m ++ < N){ // 벽을 만날때 까지
                    int nextX = position[0] + m*mx[j];
                    int nextY = position[1] + m*my[j];
                    if(nextX>=0 && nextY>=0 && nextX<N && nextY<N){ // 범위내
                        if(map[nextX][nextY]=='S'){
                            return false;
                        }else if(map[nextX][nextY]=='W')  break;
                    }
                    else  break;
                }
            }
        }
        return true;
    }    
}
