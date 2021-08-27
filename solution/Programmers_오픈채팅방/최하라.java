public import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> uidName = new HashMap<>(); 
        for (String text : record) { 
            String[] tempList = text.split(" "); 
            if (tempList.length > 2) 
                uidName.put(tempList[1], tempList[2]); 
        } 
        ArrayList<String> answer = new ArrayList <String>(); 
        
        for (String text : record) { 
            String[] tempList = text.split(" "); 
            if (tempList[0].equals("Enter")) 
                answer.add(uidName.get(tempList[1]) + "님이 들어왔습니다."); 
            else if (tempList[0].equals("Leave")) 
                answer.add(uidName.get(tempList[1]) + "님이 나갔습니다."); 
        } 
        return answer.toArray(new String[answer.size()]); 
    } 
}class 최하라 {
    
}
