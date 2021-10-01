import java.io.*;

public class Main {
    
    static int N;                   // 단어의 개수 - 최대 10
    static int alphabetCount = 0;   // 총 사용된 알파벳 수
    static String[] words;
    static int[] top;               // 각 알파벳별 최고 자릿수
    static int[] check;             // 최종 매칭 저장

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        top = new int[26];
        check = new int[26];

        for(int i = 0 ; i < 26 ; i++){
            top[i] = -1;
            check[i] = -1;
        }

        for(int i = 0 ; i<N ; i++){
            String word = br.readLine();
            words[i] = word;
            for(int j = 0 ; j< word.length(); j++){

                // 사용한적 없는 알파벳이 사용되면 알파벳수 추가
                int index = word.charAt(j)-'A'; 
                int position = word.length() - j - 1;
                if(top[index]==-1) alphabetCount++;
                if(top[index]<position) top[index] = position;
            }
        }

        // 가장 높은 자릿수 위치의 알파벳 구해서 9부터 차례로 대입
        int num = 9;
        while(alphabetCount -- > 0){
            int[] temp = {0,0};
            for(int i = 0 ; i<26; i++){

                // 이미 알파벳을 배정받았으면 넘어가기
                if(check[i] != -1) continue;

                check[i] = num;
                int result = getResult();
                if(temp[0]<result){
                    temp[0] = result;
                    temp[1] = i;
                }
                check[i] = -1;
                
            }
            check[temp[1]] = num--;
        }

        System.out.println(getResult());
        br.close();
    }

    // 현재까지 설정된 알파벳의 값을 이용하여 결과 계산
    static int getResult(){
        int sum = 0;
        for(int i = 0 ; i<N ; i++){
            for(int j = 0; j<words[i].length(); j++){
                char temp = words[i].charAt(j) ;

                // 배정받은 숫자가 없으면 넘어간다
                if(check[temp- 'A']==-1) continue;
                
                int num = check[temp - 'A'];
                sum += Math.pow(10, words[i].length()-j -1)* num;
            }
        }
        return sum;
    }

}
