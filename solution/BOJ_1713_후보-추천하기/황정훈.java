import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, R;
    static boolean[] candidates;
    static class Poster{
        int order, voted;
        public Poster(int order, int voted){
            this.order = order;
            this.voted = voted;
        }
    }
    static Map<Integer, Poster> posters = new HashMap<>();

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        candidates = new boolean[R+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int id = Integer.parseInt(st.nextToken());

            if (candidates[id]) // 이미 있으면
                posters.get(id).voted++; // 추천수 증가
            else if (posters.size()<N) { // 빈 액자 있으면
                posters.put(id, new Poster(i, 1)); // 새로운 후보 추가
                candidates[id] = true;
            }
            else{ // 액자에 없는데 빈 액자도 없으면
                int poster_id = 0;
                int order = Integer.MAX_VALUE;
                int voted = Integer.MAX_VALUE;
                for(Map.Entry<Integer, Poster> E : posters.entrySet()){ // 포스터 중에서
                    int K = E.getKey();
                    Poster V = E.getValue();
                    if (V.voted < voted || (voted==V.voted && V.order < order)) { // 추천수 제일 낮거나, 오래전에 등록된 후보
                        poster_id = K;
                        voted = V.voted;
                        order = V.order;
                    }
                }
                posters.remove(poster_id); // 제거
                candidates[poster_id] = false;
                posters.put(id, new Poster(i, 1)); // 새로운 후보 추가
                candidates[id] = true;
            }
        }
        for (int i = 1; i <= R; i++) {
            if (candidates[i])
                sb.append(i+" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
