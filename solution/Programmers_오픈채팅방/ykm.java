import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    static String IN ="님이 들어왔습니다.";
    static String OUT = "님이 나갔습니다.";
    static HashMap<String, String> id_to_nick = new HashMap<>(); // 닉네임 표시용
    static String[][] inOut;

    public String[] solution(String[] record) {
        inOut = new String[record.length][2];
        int index = 0;
        int count = 0;

        for(int i = 0 ; i<record.length; i++){
            String r = record[i];

            StringTokenizer st = new StringTokenizer(r);
            String type = st.nextToken();
            String id = st.nextToken();

            if(type.equals("Enter")){
                String nick = st.nextToken();
                id_to_nick.put(id,nick);
                inOut[index][0] = id;
                inOut[index][1] = "in";
                index++;
                count++;

            }else if(type.equals("Change")){
                String nick = st.nextToken();
                //id_to_nick.remove(id);
                id_to_nick.put(id, nick);
            }else{ // "Leave"
                inOut[index][0] = id;
                inOut[index][1] = "out";
                index++;
                count++;
            }
        }
        String[] answer = new String[count];
        for(int i = 0 ; i<index; i++){
            String temp = "";
            String id = inOut[i][0];
            String type = inOut[i][1];
            temp += id_to_nick.get(id);
            if(type.equals("in")){
                temp += IN;
            }else{ // "out"
                temp += OUT;
            }
            answer[i] = temp;
        }
        return answer;
    }
}
