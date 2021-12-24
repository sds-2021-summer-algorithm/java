import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int countNumInRow;

    static class MidNumArray{

        int totalCount; // 전체 숫자수
        int midNum; // 중위값
        int count; // 전체 숫자 개수
        PriorityQueue<Integer> big = new PriorityQueue<>(); // 현재 중위값 보다 큰값 오름차순
        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());

        public MidNumArray(int M){
            sb.append((M+1)/2+"\n");
            totalCount = M;
            count = 0;
            countNumInRow = 0;
        }

        public void addNum(int num){
            if(count == 0){
                midNum = num;
            }else{
                if(num>=midNum){
                    big.add(num);
                }else{
                    small.add(num);
                }
            }
            count++;

            if(count%2 == 1){
                findMidNum();    
                printMidNum();
            }

            if(count == totalCount){
                sb.append("\n");
            }
        }

        public void findMidNum(){
            if(big.size() > small.size()){
                small.add(midNum);
                midNum = big.poll();
            }else if(big.size() < small.size()){
                big.add(midNum);
                midNum = small.poll();
            }
        }

        public void printMidNum(){
            sb.append(midNum);
            countNumInRow++;

            if(countNumInRow>=10){
                sb.append("\n");
                countNumInRow = 0;
            }else{
                if(count < totalCount) {
                    sb.append(" ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        while(N-->0){
            int M = Integer.parseInt(br.readLine());
            MidNumArray array = new MidNumArray(M);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i<= M; i++){
                int num = Integer.parseInt(st.nextToken());
                array.addNum(num);
                if(i%10 == 0){
                    st = new StringTokenizer(br.readLine());
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
