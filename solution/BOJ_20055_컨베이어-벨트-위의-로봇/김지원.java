import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/20055
public class Main {
    static int N,K;
    static int[] container;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        container = new int[2*N];
        robot = new boolean[N];
        int stage = 1;
        int count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i=0; i < 2*N; i++){
            container[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(start(0));

    }

    private static int start(int cnt) {
        while(isOK()){
            //1.벨트 한칸 회전!
            int temp = container[container.length -1]; //마지막 칸을 저장
            for(int i = container.length-1; i>0; i--){
                container[i] = container[i-1];
            }
            container[0] = temp; //첫번째 칸에 마지막 것을 저장하기

            //2. 로봇도 회전
            for(int i= robot.length-1; i>0; i--){
                robot[i] = robot[i-1];
            }
            robot[0] = false; //첫번째 칸은 비워주기

            //3. 로봇 이동
            robot[N-1] = false; //제일 마지막 칸은 떨어뜨리기
            for(int i=N-1; i>0; i--){
                if(robot[i-1] && !robot[i] &&container[i] >= 1){ //내구도가 남아있고, 로봇이 있고, 다음칸이 비어있어야함
                    robot[i] = true;
                    robot[i-1] = false;
                    container[i]--;
                }
            }

            //4. 처음위치에 로봇 올리기
            if(container[0] > 0){
                robot[0] = true;
                container[0]--; //내구도 줄어듦
            }

            cnt++;
        }

        return cnt;
    }

    private static boolean isOK(){// 계속 진행해도 되는지 체크
        int cnt = 0;

        for (int i=0; i < container.length; i++){
            if(container[i] == 0){ //컨테이너가 비어 있으면!!
                cnt++;
            }

            if(cnt >= K){ //컨테이너 위에 K이상 로봇이 있으면 종료!!
                return false;
            }
        }
        return true;
    }
}
