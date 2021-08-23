package Programmers;

import java.util.ArrayList;
import java.util.Collections;


class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        int type = languages.length;
        int [] score = new int[5]; // 분야별 점수
        String [] section = new String[5]; // 분야별 이름

        for(int i = 0 ; i<5; i++){ // 분야
            String[] row = table[i].split(" ");
            section[i] = row[0];
            for(int j = 5; j>0 ; j--){ // 점수
                for(int k = 0 ; k <type ; k++){ // 언어
                    if(row[6-j].equals(languages[k])){
                        score[i] += j*preference[k];
                    }
                }
            }
        }

        // score에 저장된 점수가 가장 높은 index의 table[index][0]
        int max = 0;
        ArrayList<String> answer = new ArrayList<>();
        for(int i=0 ; i<5 ; i++){
            if(max < score[i]){
                answer.clear();
                answer.add(section[i]);
                max = score[i];
            }else if(max == score[i]){
                answer.add(section[i]);
            }
        }

        Collections.sort(answer);
        
        return answer.get(0);
    }
}
