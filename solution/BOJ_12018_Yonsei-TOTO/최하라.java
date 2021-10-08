import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //2. 각 과목 최소 마일리지 possibilities 큐에 저장
        PriorityQueue<Integer> possibilities = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            //신청 수가 수강 인원보다 적을 때
            if (P < L) {
                possibilities.add(1);
                br.readLine();
                continue;
            }

            //신청 한 사람들을 pq에 넣기
            PriorityQueue<Integer> people = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < P; j++) {
                people.add(Integer.parseInt(st.nextToken()));
            }

            //현재 과목에서 최소로 가져야할 마일리지 저장
            while (people.size() > L) people.poll();
            possibilities.add(people.poll());
        }

        //3. 들을 수 있는 최대 과목 수 구하기
        int count = 0, sum = 0;
        while (!possibilities.isEmpty()) {
            if (sum + possibilities.peek() <= M) {
                sum += possibilities.poll();
                count++;
            } else break;
        }

        //4. 결과 출력
        System.out.println(count);
    }
}
