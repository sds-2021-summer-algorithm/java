import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] teamStatus = new int[N];

        //손상 팀 저장
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++)
            teamStatus[Integer.parseInt(st.nextToken()) - 1] = -1;

        //여유분 팀 저장
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < R; j++)
            teamStatus[Integer.parseInt(st.nextToken()) - 1]++;

        //카약 배분
        for(int i = 0; i < N; i++) {
            if(teamStatus[i] == -1) { //부서진 경우
                if(i > 0 && teamStatus[i-1] == 1) { //왼쪽 체크
                    teamStatus[i] = 0;
                    teamStatus[i-1] = 0;
                } else if(i < N-1 && teamStatus[i+1] == 1) { //오른쪽 체크
                    teamStatus[i] = 0;
                    teamStatus[i+1] = 0;
                }
            }
        }

        //카약 없는 팀 수 확인
        int total = 0;
        for(int e : teamStatus)
            if(e == -1) total++;

        System.out.println(total);
    }
}