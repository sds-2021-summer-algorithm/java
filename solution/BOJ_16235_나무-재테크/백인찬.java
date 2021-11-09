import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static PriorityQueue[][] trees;
    static int[][] land, A;
    static int numTree = 0;
    // 상 하 좌 우 상왼, 상오, 하왼, 하오
    static final int[] mx = {0, 0, -1, 1, -1, 1, -1, 1};
    static final int[] my = {-1, 1, 0, 0, -1, -1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        init();

        for (int i = 0; i < K; i++) {
            if(numTree == 0) break;
            springAndSummer();
            autumn();
            winter();
        }
        bw.write(numTree + "\n");
        bw.flush();
        bw.close();
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        trees = new PriorityQueue[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                trees[i][j] = new PriorityQueue<Integer>();
            }
        }
        A = new int[N + 1][N + 1];
        land = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(land[i], 5);
            land[i][0] = 0;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(z);
            numTree++;
        }
    }

    static void springAndSummer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(trees[i][j].size() == 0) continue;

                // Spring Part
                boolean flag = false;
                Queue<Integer> subQ = new LinkedList<>();
                while(!trees[i][j].isEmpty()) {
                    int curAge = (int) trees[i][j].peek();
                    if(curAge > land[i][j]) {
                        flag = true;
                        break;
                    }
                    land[i][j] -= curAge;
                    subQ.add(curAge + 1);
                    trees[i][j].remove();
                }

                // Summer Part
                if(flag) {
                    while(!trees[i][j].isEmpty()) {
                        int curAge = (int) trees[i][j].remove();
                        land[i][j] += curAge / 2;
                        numTree--;
                    }
                }

                trees[i][j].addAll(subQ);
            }
        }
    }

    static void autumn(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for(Object tree :trees[i][j]) {
                    int age = (int) tree;
                    if(age % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int ty = i + my[k];
                            int tx = j + mx[k];

                            if(ty > 0 && ty <= N && tx > 0 && tx <= N) {
                                trees[ty][tx].add(1);
                                numTree++;
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                land[i][j] += A[i][j];
            }
        }
    }
}
