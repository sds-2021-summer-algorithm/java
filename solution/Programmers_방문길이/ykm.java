package Programmers;
import java.io.*;

// 처음 걸어본 길의 길이
public class visitlen {
    static class Solution {
        static boolean [][][][] visit = new boolean[11][11][11][11]; 
        // 일종의 그래프 : 출발노드 -> 도착노드

        // 첫번째 방문이면 true를 리턴 
        public boolean check(int fromX, int fromY, int toX, int toY){
            if(!visit[fromX][fromY][toX][toY]){
                visit[fromX][fromY][toX][toY] = true; // 방문 표시
                visit[toX][toY][fromX][fromY] = true; // 방문 표시
                return true;
            }
            return false;

        }
        public int solution(String dirs) {
            int x = 5, y = 5 ; // 행, 열
            int answer = 0;

            for(int i = 0 ; i< dirs.length(); i++){
                char letter = dirs.charAt(i);
                if(letter =='U') {
                    if(x > 0 && check(x, y, --x, y)) answer++;
                }else if(letter =='D') {
                    if(x < 10 && check(x, y, ++x, y)) answer++;
                }
                else if(letter =='R') {
                    if(y < 10 && check(x, y, x, ++y)) answer++;
                }else{
                    if(y > 0 && check(x, y, x, --y)) answer++;
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) throws IOException{
        Solution answer = new Solution();
        String s = "ULURRDLLU";
        System.out.println(answer.solution(s));
    }
}
