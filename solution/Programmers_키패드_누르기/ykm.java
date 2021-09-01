package Programmers;
import java.io.*;

public class keypad {
    static class Solution {
        public String solution(int[] numbers, String hand) {
            //              0   1   2   3   4   5   6   7   8   9    *   0   #
            char[] which = {'B','L','B','R','L','B','R','L','B','R','L','B','R'};

            int L = 10;
            int R = 12;
            StringBuilder sb = new StringBuilder();
            for(int number : numbers){
                if(number == 0) number=11;
                if(which[number]=='R'){
                    sb.append(which[number]);
                    R = number;
                }
                else if(which[number]=='L'){
                    sb.append(which[number]);
                    L = number;
                }
                else{
                    int distR = Math.abs(R - number)/3 + Math.abs(R-number)% 3 ;
                    int distL = Math.abs(L - number)/3 + Math.abs(L-number)% 3 ;

                    if(distR > distL){
                        sb.append('L');
                        L = number;
                    }else if(distR == distL){
                        char letter = (char) (hand.charAt(0)-'a'+'A');
                        sb.append(letter);
                        if(letter=='L') L = number;
                        else R = number;
                    }else{
                        sb.append('R');
                        R = number;
                    }
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException{
        Solution answer = new Solution();
        int [] numbers= {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left"; 
        System.out.println(answer.solution(numbers, hand));
    }
}
