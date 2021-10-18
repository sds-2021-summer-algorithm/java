class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {

        int match = 0;
        int unknown = 0;
        for(int lotto : lottos){
            if(lotto == 0){
                unknown++;
            }else {
                for(int num : win_nums){
                    if(num == lotto){
                        match++;
                    }
                }
            }
        }

        int[] answer = {0, 0};

        answer[1] = 7-match; // 최저 순위
        if(answer[1]==7) answer[1] = 6;
        

        answer[0] = answer[1] - unknown; // 최고 순위
        if(answer[0]==7) answer[0] = 6;
        if(answer[0]==0) answer[0] = 1;
        

        return answer;
    }
}
