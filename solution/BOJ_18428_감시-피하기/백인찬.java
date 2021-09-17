import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static List<Coord> candidates;
    static List<Coord> teachers;

    static boolean flag = false;
    static int N;
    static class Coord {
        int i, j;

        public Coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        candidates = new ArrayList<>();
        teachers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') candidates.add(new Coord(i, j));
                else if(map[i][j] == 'T') teachers.add(new Coord(i, j));
            }
        }

        dfs(0, 0);
        if(flag) bw.write("YES");
        else bw.write("NO");
        bw.flush();
        bw.close();
    }
    static void dfs(int i, int count) {
        if(flag) return;
        if(count == 3) {
            // search
            if(check()) flag = true;
            return;
        }
        int len = candidates.size();
        for (int j = i; j < len; j++) {
            Coord cur = candidates.get(j);
            map[cur.i][cur.j] = 'O';
            dfs(j + 1, count + 1);
            if(flag) return;
            map[cur.i][cur.j] = 'X';
        }
    }

    static boolean check() {
        for (Coord teacher : teachers) {
            for (int i = 0; i < 4; i++) {
                int curI = teacher.i;
                int curJ = teacher.j;
                while (true) {
                    curI += my[i];
                    curJ += mx[i];
                    if(!(curI >= 0 && curI < N && curJ >= 0 && curJ < N)) break;
                    if(map[curI][curJ] == 'O') break;
                    if(map[curI][curJ] == 'S') return false;
                }
            }
        }
        return true;
    }
}

