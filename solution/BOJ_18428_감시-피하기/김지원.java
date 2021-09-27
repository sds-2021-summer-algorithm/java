import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, cnt;
    static char map[][];
    static ArrayList<Integer> teacher;
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    static boolean flag;

    public static void main(String[] args) throws IOException {
        //1. 데이터 입력받기
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.valueOf(st.nextToken());
        teacher = new ArrayList<>();
        cnt = 0;
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') { //선생님 추가해주기
                    teacher.add(cnt);
                }
                cnt++;
            }
        }
        flag = false; //한번이라도 되면 바로 YES로 출력
        dfs(0, 0);
        if(flag)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static void dfs(int idx, int pick) { //장애물 한개씩 두면서 Backtracking
        if (flag)
            return;
        if (idx == N * N) {
            return;
        }
        if (pick == 3) { //3개 고르면, 학생들 찾기를 시작함
            if (find_stu()) {
                flag = true; //못찾으면 YES
            }
            return;
        }
        int r = idx / N;
        int c = idx % N;
        if (map[r][c] == 'X') { //장애물 넣었다 뺐다 해주기
            map[r][c] = 'O';
            dfs(idx + 1, pick + 1);
            map[r][c] = 'X';
        }
        dfs(idx + 1, pick);
    }

    static boolean find_stu() { //학생 찾기 ㅎ마수
        for (int k : teacher) {
            for (int i = 0; i < 4; i++) {
                int cr = k / N;
                int cc = k % N;
                while (true) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 'O') //끌까지 다 찾아보기
                        break;
                    if (map[nr][nc] == 'S') { //상하좌우 끝까지 가서 한번이라도 학생을 찾으면 False
                        return false;
                    }
                    cr = nr;
                    cc = nc;
                }
            }
        }
        return true;
    }
}
