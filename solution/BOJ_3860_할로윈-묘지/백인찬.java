import java.io.*;
import java.util.*;

public class Main {
    static int W ,H, G, E;
    static int V;
    static int[][] dist; // 0 -> [i][j] 로 가는 최단 거리 저장
    static List<E> edges;
    static int[][] map;
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if(W == 0 && H == 0) break;
            V = W * H;
            dist = new int[W][H];
            map = new int[W][H];
            edges = new ArrayList<>();
            // dist 초기화
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    if(i == 0 && j == 0) continue;
                    dist[i][j] = MAX;
                }
            }
            // 묘비 위치 입력
            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[y][x] = -1;
            }

            // 귀신 구멍 입력
            E = Integer.parseInt(br.readLine());
            E[] holes = new E[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int sY = Integer.parseInt(st.nextToken());
                int sX = Integer.parseInt(st.nextToken());
                int eY = Integer.parseInt(st.nextToken());
                int eX = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                map[sY][sX] = 1;
                holes[i] = new E(sY, sX, eY, eX, t);
            }
            constructGraph();
            edges.addAll(Arrays.asList(holes));

            if (!bellmanFord()) {
                sb.append("Never\n");
            } else if (dist[W - 1][H - 1] == MAX) {
                sb.append("Impossible\n");
            } else {
                sb.append(dist[W - 1][H - 1]).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
    static void constructGraph() {
        // 상 하 좌 우
        int[] mx = {0, 0, -1, 1};
        int[] my = {-1, 1, 0, 0};

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if(map[i][j] != 0) continue; // 묘비나 구멍이면
                if(i == W-1 && j == H-1) continue;
                for (int k = 0; k < 4; k++) {
                    int ty = i + my[k];
                    int tx = j + mx[k];
                    if (ty >= 0 && ty < W && tx >= 0 && tx < H) {
                        if(map[ty][tx] >= 0) { // 묘비로는 못 가는데 귀신구멍으로는 갈 수 있지
                            edges.add(new E(i, j, ty, tx, 1));
                        }
                    }
                }
            }
        }
    }
    static boolean bellmanFord() {
        for (int i = 1; i < V; i++) {
            for (E e : edges) {
                if (dist[e.startI][e.startJ] == MAX)
                    continue;
                dist[e.endI][e.endJ] = Math.min(dist[e.startI][e.startJ] + e.weight, dist[e.endI][e.endJ]);
            }
        }

        for (E e : edges) {
            if (dist[e.startI][e.startJ] == MAX)
                continue;
            if(dist[e.startI][e.startJ] + e.weight < dist[e.endI][e.endJ]) { // 갱신 발생
                return false;
            }
        }
        return true;
    }
}

class E {
    int startI, startJ;
    int endI, endJ;
    int weight;

    public E(int startI, int startJ, int endI, int endJ, int weight) {
        this.startI = startI;
        this.startJ = startJ;
        this.endI = endI;
        this.endJ = endJ;
        this.weight = weight;
    }
}


