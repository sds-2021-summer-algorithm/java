import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int M;           
    static int [] data;
    static int start ; // 데이터의 시작
    static int end ; // 다음데이터가 들어갈 자리

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("P5430/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int i = 0 ; i<N; i++){
            String command = br.readLine();      // R과 D 조합
            M = Integer.parseInt(br.readLine()); // 데이터 길이

            String line = br.readLine();
            line = line.substring(1, line.length()-1);
            String[] nums = line.split(",");

            start = 0;
            end = M;
            boolean error = false;
            boolean flag = false;
            for(int j = 0 ; j<command.length(); j++){
                char c = command.charAt(j);
                if(c=='D'){
                    if(start==end) {
                        error = true;
                        break;
                    }else if(!flag){
                        start++;
                    }else{
                        end--;
                    }
                }else if(c=='R'){
                    if(j!=command.length()-1 && command.charAt(j+1)=='R'){
                        j++;
                        continue;
                    }
                    if(flag) flag = false;
                    else flag = true;
                }
            }

            if(error) bw.write("error\n");
            else{
                bw.write("[");
                if(!flag){
                    for(int j = start; j<end; j++){
                        bw.write(nums[j]);
                        if(j!=end-1) bw.write(",");
                    }
                }else{
                    for(int j = end-1; j>start-1; j--){
                        bw.write(nums[j]);
                        if(j!=start) bw.write(",");
                    }
                }
                bw.write("]\n");
            }  
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
