import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        int changeCount = 0;
        for (String s : record) {
            String[] ss = s.split(" ");
            String id = ss[1];
            String name;
            if(!ss[0].equals("Leave")) {
                name = ss[2];
                map.put(id, name);
                if(ss[0].equals("Change")) changeCount++;
            }
        }

        String[] answer = new String[record.length - changeCount];
        int i = 0;
        for (String s : record) {
            String[] ss = s.split(" ");
            if(ss[0].equals("Change")) continue;
            String id = ss[1];
            String name = map.get(id);
            answer[i++] = name+"님이 " + (ss[0].equals("Enter") ? "들어왔습니다." : "나갔습니다.");
        }
        return answer;
    }
}
