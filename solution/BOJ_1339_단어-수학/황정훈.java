import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, sum = 0;
    static String[] words;
    static class Alpha{
        char c;
        int coef;
        public Alpha(char c, int coef){
            this.c = c;
            this.coef = coef;
        }
    }
    static HashMap<Character, Alpha> chars = new HashMap<>();

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine(); // 입력받고
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if(chars.containsKey(c)) // 이미 존재하면
                    chars.get(c).coef += Math.pow(10, words[i].length()-j-1); // 계수 추가
                else // 없으면
                    chars.put(c, new Alpha(c, (int) Math.pow(10, words[i].length()-j-1))); // 새로만들고
            }
        }
        ArrayList<Alpha> list = new ArrayList<>();
        for(Map.Entry<Character, Alpha> item : chars.entrySet()){
            list.add(item.getValue()); // 계수들 전부 리스트에 넣고
        }
        list.sort((a, b)-> b.coef-a.coef); // 내림차순정렬
        int num = 9;
        for(Alpha c : list){ // 하나씩 꺼내며 9부터
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].replace(c.c, (char)(num+'0')); // replace
            }
            num--;
        }
        for(String str : words){
            sum += Integer.parseInt(str); // 다 더해주기
        }

        bw.write(sum+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
