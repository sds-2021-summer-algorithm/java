import java.io.*;
import java.util.*;
public class Main {
    static List[] favors;
    static int N;
    static Seat[] seats;
    static Seat[][] classroom;
    static int[][] condition1;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        favors = new List[N * N + 1];
        seats = new Seat[N * N + 1];
        classroom = new Seat[N][N];
        for (int i = 1; i <= N * N; i++) {
            favors[i] = new ArrayList<Integer>();

        }
        init();
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            q.add(a);
            for (int j = 0; j < 4; j++) {
                favors[a].add(Integer.parseInt(st.nextToken()));
            }
        }
        while(!q.isEmpty()) {
            int curStudent = q.remove();
            PriorityQueue<p> pq = new PriorityQueue<>((o1, o2) -> {
                if(o1.condition1 == o2.condition1) {
                    if (o1.seat.emptyAdj == o2.seat.emptyAdj) {
                        if (o1.seat.i == o2.seat.i) {
                            return o1.seat.j - o2.seat.j;
                        }
                        return o1.seat.i - o2.seat.i;
                    }
                    return o2.seat.emptyAdj - o1.seat.emptyAdj;
                }
                return o2.condition1 - o1.condition1;
            });
            condition1 = new int[N][N];
            for (int i = 0; i < 4; i++) {
                int friend = (int) favors[curStudent].get(i);
                Seat seat = seats[friend];
                if (seat != null) {
                    for (int j = 0; j < 4; j++) {
                        int ty = seat.i + my[j];
                        int tx = seat.j + mx[j];
                        if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                            if(classroom[ty][tx].isEmpty) {
                                condition1[ty][tx]++;
                            }
                        }
                    }
                }
            }
            addCandidate(pq);

            seats[curStudent] = pq.remove().seat;
            seats[curStudent].isEmpty = false;
            seats[curStudent].who = curStudent;
            for (int i = 0; i < 4; i++) {
                int ty = seats[curStudent].i + my[i];
                int tx = seats[curStudent].j + mx[i];
                if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                    classroom[ty][tx].emptyAdj--;
                }
            }
        }

        bw.write(checkSatisfaction() + "\n");
        bw.flush();
        bw.close();
    }
    static boolean isInFavor(int cur, int compare) {
        for (int i = 0; i < 4; i++) {
            int next = (int) favors[cur].get(i);
            if(next == compare) return true;
        }
        return false;
    }
    static int checkSatisfaction() {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cal = 0;
                int curStudent = classroom[i][j].who;
                for (int k = 0; k < 4; k++) {
                    int ty = i + my[k];
                    int tx = j + mx[k];

                    if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
                        if (isInFavor(curStudent, classroom[ty][tx].who)) {
                            cal++;
                        }
                    }
                }
                ret += (int)Math.pow(10, cal) / 10;

            }
        }
        return ret;
    }
    static void addCandidate(PriorityQueue<p> pq) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(classroom[i][j].isEmpty) {
                    if(condition1[i][j] != 0) {
                        pq.add(new p(condition1[i][j], classroom[i][j]));
                    }
                    else {
                        pq.add(new p(0, classroom[i][j]));
                    }
                }
            }
        }
    }
    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || i == N - 1) {
                    if (j == 0 || j == N - 1) {
                        classroom[i][j] = new Seat(i, j, 2);
                    }
                    else {
                        classroom[i][j] = new Seat(i, j, 3);
                    }
                }
                else {
                    if (j == 0 || j == N - 1) {
                        classroom[i][j] = new Seat(i, j, 3);
                    }
                    else {
                        classroom[i][j] = new Seat(i, j, 4);
                    }
                }
            }
        }
    }
}

class p {
    int condition1;
    Seat seat;

    public p(int condition1, Seat seat) {
        this.condition1 = condition1;
        this.seat = seat;
    }
}
class Seat {
    int i, j;
    int emptyAdj;
    boolean isEmpty;
    int who;

    public Seat(int i, int j, int emptyAdj) {
        this.i = i;
        this.j = j;
        this.emptyAdj = emptyAdj;
        this.isEmpty = true;
    }

    public int getEmptyAdj() {
        return emptyAdj;
    }
}
