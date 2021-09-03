import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static Set[] friend;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        friend = new Set[N*N+1];
        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            friend[id] = new HashSet();
            for (int j = 0; j < 4; j++) {
                friend[id].add(Integer.parseInt(st.nextToken()));
            }
            fill(id);
        }
        bw.write(calc()+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static void fill(int id){
        int empty = 0;
        int like = 0;
        int row = 0;
        int col = 0;
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (map[i][j]==0){ // 빈자리면
                    int tmp_empty = 0;
                    int tmp_like = 0;
                    for (int w = 0; w < 4; w++) { // 4방향 돌면서
                        int nr = i + dr[w];
                        int nc = j + dc[w];
                        if(0<=nr && nr<N && 0<=nc && nc<N){ // 교실안에있으면
                            if (map[nr][nc]==0) // 빈자리 수 세고
                                tmp_empty++;
                            else if (friend[id].contains(map[nr][nc])) // 좋아하는 학생 수 세고
                                tmp_like++;
                        }
                    }
                    if ((tmp_like > like) || (tmp_like == like && tmp_empty >= empty)){ // 좋아하는 학생 수가 더 많거나, 같은데 빈자리수가 같거나 더 많으면 위치 갱신 위치 새로 갱신
                        like = tmp_like;
                        empty = tmp_empty;
                        row = i;
                        col = j;
                    }
                }
            }
        }
        map[row][col] = id;
    }

    static int calc(){
        int score = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                int id = map[i][j];
                for (int k = 0; k < 4; k++) {
                    int nr = i+dr[k];
                    int nc = j+dc[k];
                    if (0<=nr && nr<N && 0<=nc && nc<N && friend[id].contains(map[nr][nc]))
                        cnt++;
                }
                score += cnt > 0 ? Math.pow(10, cnt-1) : 0;
            }
        }
        return score;
    }
}
