class Solution {
        public int solution(int n, int[] lost, int[] reserve) {

            int answer = n - lost.length; //전체학생수 - 체육복을 잃어버린 학생
            
            boolean[] l = new boolean[n+1];
            boolean[] r = new boolean[n+1];
            //그리디
            for(int i = 0 ; i<lost.length; i++){
                l[lost[i]] = true;
            }
            for(int i = 0; i<reserve.length; i++){
                if(l[reserve[i]]){ // 여벌옷을 가져온 학생이 체육복을 분실
                    l[reserve[i]] = false;
                    answer++;
                }
                else {
                    r[reserve[i]] = true;
                }
            }

            int j = 0; // 여벌옷이 있는 학생을 찾자
            // 체육복을 빌릴수 있는 학생수를 찾자
            for(int i = 1 ; i<=n; i++){
                if(l[i]){ // i번호 학생이 체육복을 잃어버렸다면
                    if(i!=1 && r[i-1]){
                        answer++;
                        r[i-1] = false;
                        l[i] = false;
                    }else if(i!=n && r[i+1]){
                        answer++;
                        r[i+1] = false;
                        l[i] = false;
                    }
                }
            }

            return answer;
        }
    }
