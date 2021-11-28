import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main{

    public static class Solution {
        static int answerCount = 0;
        static Queue<String> q = new ArrayDeque<String>();           // BFS용
        static Queue<String> answerCombi = new ArrayDeque<String>(); // 디버깅용 - 지워도됨

        public static int solution(String[][] relation) {
            int colNum = relation[0].length;

            for(int i = 0 ; i<colNum; i++){
                q.add(String.valueOf(i));
            }
            permutation(colNum, relation); // BFS
            return answerCount;
        }

        private static void permutation(int colNum, String[][] relation){
            
            while(!q.isEmpty()){
                String current = q.poll();
                StringTokenizer st = new StringTokenizer(current);
                boolean[] candidate = new boolean[colNum];
                int candidateNum = 0;
                int last = 0;
                candidateNum = st.countTokens();

                while(st.hasMoreTokens()){
                    int index = Integer.parseInt(st.nextToken());
                    candidate[index] = true;
                    last = index;
                }

                // 이번 조합이 유니크 하다면
                if(isUnique(candidate, relation)){
                    
                    // 하나면 최소성을 확인할 필요가 없음.
                    if(candidateNum == 1 || minimalTest(candidate, relation)){
                        answerCombi.add(current);
                        answerCount++;
                    }  
                      
                // 이번 조합이 유니크 하지 않다면
                }else{
                    
                    // 새로운 col을 추가해보자 -> 현재 column 다음부터 추가
                    for(int i = last + 1; i<colNum; i++){    
                        String next = current;
                        next += " ";
                        next += i;
                        q.add(next);
                    }
                }
            }
        }

        private static boolean minimalTest(boolean[] candidate, String[][] relation) {
            // 최소성을 만족하는지 확인

            for(int i = 0; i<candidate.length; i++){
                if(candidate[i]){
                    candidate[i] = false;               // 현재 조합에서 하나를 지워도 되는지 확인
                    if(isUnique(candidate, relation)){
                        candidate[i] = true;
                        return false;
                    }
                    candidate[i] = true;
                }
            }
            return true;
        }

        private static boolean isUnique(boolean[] candidate, String[][] relation) {
        
            Set<String> set = new HashSet<String>();
            for(int i = 0 ; i<relation.length ; i++){       // 사람별
                String row = "";                            // candidate가 true인 값만 골라 포함시키기
                for(int j = 0 ; j<candidate.length; j++){   // column별
                    if(candidate[j]){
                        row += relation[i][j];
                        row += " ";
                    }
                }
                set.add(row.substring(0,row.length()-1));
            }

            if(set.size() == relation.length) return true;
            else return false;

        }
    }

    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"}
        ,{"200","apeach","math","2"}
        ,{"300","tube","computer","3"}
        ,{"400","con","computer","4"}
        ,{"500","muzi","music","3"},
        {"600","apeach","music","2"}};

        Solution answer = new Solution();
        System.out.println(answer.solution(relation));
    }
}
