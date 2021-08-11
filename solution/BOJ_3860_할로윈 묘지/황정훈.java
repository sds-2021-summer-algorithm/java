import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static class Edge{
        Point from, to;
        int cost;
        public Edge(Point from, Point to, int cost){
            this.from = new Point(from.r, from.c);
            this.to = new Point(to.r, to.c);
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int[][] state, dist;
        ArrayList<Edge> edges;
        
        while (W!=0 && H!=0){
            edges = new ArrayList<>();
            state = new int[H][W];
            dist = new int[H][W];
            for (int i = 0; i < H; i++)
                Arrays.fill(dist[i], MAX);
            dist[0][0] = 0;
            
            int G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                state[r][c] = 1;
            }
        
            int E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int c1 = Integer.parseInt(st.nextToken());
                int r1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                state[r1][c1] = -1;
                edges.add(new Edge(new Point(r1, c1), new Point(r2, c2), t));
            }

            for (int r = 0; r < H; r++)
                for (int c = 0; c < W; c++)
                    if (state[r][c]==0 && (r!=H-1 || c!=W-1))
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if ((0 <= nr && nr < H) && (0 <= nc && nc < W) && state[nr][nc] != 1)
                                edges.add(new Edge(new Point(r, c), new Point(nr, nc), 1));
                        }

            for (int i = 0; i < W*H-G-1; i++)
                for (Edge e : edges)
                    if (dist[e.from.r][e.from.c] != MAX)
                        dist[e.to.r][e.to.c] = Math.min(dist[e.to.r][e.to.c], dist[e.from.r][e.from.c]+e.cost);

            never:{
                for (Edge e : edges)
                    if (dist[e.from.r][e.from.c] != MAX && dist[e.to.r][e.to.c] > dist[e.from.r][e.from.c]+e.cost) {
                        bw.write("Never\n");
                        break never;
                    }
                if(dist[H-1][W-1]==MAX)
                    bw.write("Impossible\n");
                else
                    bw.write(dist[H-1][W-1]+"\n");
            }
            
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
