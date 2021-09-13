package Programmers;
import java.io.*;
import java.util.*;

public class weekly7 {
    static class Solution {
        public int[] solution(int[] enter, int[] leave) {
            int people = enter.length;
            ArrayList<Integer> room = new ArrayList<Integer>();
            HashMap<Integer,Integer> map = new HashMap<>();
            int[] answer = new int[people+1];


            int k = 0; 
            for(int i = 0 ; i<people; i++){ 
                room.add(enter[i]);                     // 들어감
                answer[enter[i]] += room.size()-1;      // 들어가자마자 만나는 사람수
                for(int j = 0 ; j<room.size(); j++){
                    if(room.get(j)==leave[k]){
                        room.remove(j);
                        k++;
                    }
                    else{
                        if(room.get(j)!= enter[i]){
                            answer[room.get(j)]++;      // 기존에 방에 있던 사람은 한사람을 더 만나게 된것
                        }
                    }
                }
                
            }

            return answer;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution answer = new Solution();
        int[] enter = {1,4,2,3};
        int[] leave = {2,1,3,4};
        System.out.println(answer.solution(enter, leave));
    }
}
