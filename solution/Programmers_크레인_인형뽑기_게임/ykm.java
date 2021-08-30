package Programmers;
import java.io.*;
import java.util.Stack;

public class Kakao {
    static class Solution {

        public Solution() {
        }

        public int solution(int[][] board, int[] moves) {
            int N = board[0].length; // 보드 폭
            int M = moves.length; // 움직인 인형수
            int []top = new int[N]; // 가장위의 인형이 위치한 row
            Stack <Integer> stack = new Stack<Integer>();
            int answer = 0;

        
            // 각 열의 top을 파악할 필요가 있다
            for(int i = 0; i<N ; i++){ // row
                top[i] = -1;
                for(int j = 0 ; j<N; j++){ // col
                    if(board[j][i]!=0) {
                        top[i] = j;
                        break;
                    }   
                }
            }

            //moves를 차례로 실행
            for(int i = 0 ; i<M ; i++){
                if(top[moves[i]-1] == N) continue;
                else{
                    int c = moves[i]-1;
                    int r = top[moves[i]-1];
                    if( !stack.isEmpty() && stack.peek() == board[r][c]) {
                        stack.pop();
                        answer+=2;
                    }
                    else stack.push(board[r][c]);
                    top[moves[i]-1]++;
                }
            }
            
            return answer;
        }


    }

    public static void main(String[]args) throws IOException{
        Solution answer = new Solution();
        int [][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int [] moves = {1,5,3,5,1,2,1,4};
        System.out.println(answer.solution(board, moves));
    }
}
