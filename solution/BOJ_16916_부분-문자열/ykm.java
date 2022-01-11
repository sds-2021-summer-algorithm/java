import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static String P,S; // S가 P의 부분 문자열 인지 확인 
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        P = br.readLine();

        int[] pattern = preprocess(P);

        int result = 0;
        // P가 S보다 길면 부분 문자열이 될수 없음.
        if(P.length()<=S.length()){
            int j = 0;
            for(int i = 0 ; i<S.length(); i++){
                if(S.charAt(i)==P.charAt(j)){
                    j++;
                }else{
                    if(j != 0){
                        j = pattern[j];
                    }
                }
                if(j == P.length()){
                    result = 1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
    private static int[] preprocess(String P){
        int[] pattern = new int[P.length()];

        int i = 1, j = 0;
        while(i<P.length()){
            if(P.charAt(i)==P.charAt(j)){
                j++;
                pattern[i] = j;
                i++;
            }else{
                if(j != 0){
                    j = pattern[j-1];
                }else{
                    pattern[i] = 0;
                    i++;
                }
            }
        }
        return pattern;
    }
}
