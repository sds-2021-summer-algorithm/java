import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map; // 각 국의 인구를 나타내는 배여
    static int[][] ally; // 각 국의 동맹 상태를 나타내는 배열
    static boolean[][] visited;
    static Queue<Country> q;
    static List<Ally> allyList; // 연합 리스트
    static List<Integer> peoplePerAlly; // 각 연합마다 인구 수 (해당 인덱스의 연합은 그 값으로 맞춰주기)
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while (true) {
            if(!makeAllies()) break; // 생성된 연합이 없으면 break
            peoplePerAlly = new ArrayList<>();

            // 각 연합의 인구 수를 계산한다. (인구이동)
            for (Ally a : allyList) {
                int people = a.totalPopularity / a.countries;
                peoplePerAlly.add(people);
            }

            // 각 나라의 인구 수를 인구이동한 결과로 만들어준다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = peoplePerAlly.get(ally[i][j]);
                }
            }
            days++;
        }

        bw.write(days + "\n");
        bw.flush();
        bw.close();
    }

    // 연합을 만들어주는 함수
    static boolean makeAllies() {
        visited = new boolean[N][N];
        boolean needToMove = false;
        q = new LinkedList<>();
        ally = new int[N][N];
        allyList = new ArrayList<>();

        int allies = 0;

        // map의 모든 칸을 돌면서 연합을 만들어준다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                // 방문한 적 없는 노드 == 아직 어느 연합에 속하지 않은 노드
                q.add(new Country(i, j));
                visited[i][j] = true;
                ally[i][j] = allies++; // 새로운 연합으로 지정
                int countries = 1;
                int totalPopularity = map[i][j];

                // BFS
                while (!q.isEmpty()) {
                    Country cur = q.remove();
                    int curAlly = ally[cur.i][cur.j]; // 현재 연합 넘버를 기억해둠
                    for (int k = 0; k < 4; k++) {
                        int ty = cur.i + my[k];
                        int tx = cur.j + mx[k];

                        if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                            if (!visited[ty][tx]) {
                                int gap = Math.abs(map[ty][tx] - map[cur.i][cur.j]);
                                if (L <= gap && gap <= R) {
                                    // 같은 연합으로 지정
                                    ally[ty][tx] = curAlly;
                                    q.add(new Country(ty, tx));
                                    visited[ty][tx] = true;
                                    countries++;
                                    needToMove = true;
                                    totalPopularity += map[ty][tx];
                                }
                            }
                        }
                    }
                }
                // 새로운 연합이 생기면 연합리스트에 넣기
                allyList.add(new Ally(countries, totalPopularity));
            }
        }
        return needToMove;
    }

}
class Country {
    int i, j;

    public Country(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
class Ally {
    int countries;
    int totalPopularity;

    public Ally(int countries, int totalPopularity) {
        this.countries = countries;
        this.totalPopularity = totalPopularity;
    }
}
