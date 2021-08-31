import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. Data 입력받기
        N = Integer.parseInt(br.readLine());
        bt("");

//        System.out.println(result);
    }

    private static void bt(String st) {
        if(st.length() == N){ //N개의 숫자를 만들면, 출력해주기 --> 제일 처음 나오는 Backtracking이 가장 작은 값임
            System.out.println(st);
            System.exit(0);
            return;
        }
        else{//아니라면 백트래킹
            for(int i=1; i<=3; i++){
                if(isOk(st+i)){
                    bt(st+i);
                }
            }
        }
    }

    private static boolean isOk(String s) {
        int len = s.length();
        //글자 비교를 여기서 해줘야 한다.

        for(int i =1; i<=len/2; i++){ //최고로 긴 반복수열도 길이는 절반이다.
            String front_s = s.substring(len-2*i,len-i);
            String back_s = s.substring(len-i,len);

            if(front_s.equals(back_s)){ //같은게 있다는 뜻임
                return false;
            }
        }


        return true;
    }

}

//마지막 1개와 그 앞의 1개의 수가 동일한지
//마지막 2개와 그 앞의 2개의 수가 동일한지
//마지막 3개와 그 앞에 3개의 수가 동일한지
//....
//마지막 N/2개와 그 앞에 N/2개의 수가 동일한지 비교를 해
//
//한 번이라도 동일한 경우가 생긴다면 그 수열은 나쁜 수열로 판단할 수 있다.
