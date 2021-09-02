import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] S;
    static boolean[] isUsed;
    static int minGap = Integer.MAX_VALUE, N;
    static List<Integer> start = new ArrayList<>(), link = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            int count = 1;
            isUsed = new boolean[N];
            isUsed[i] = true;
            start.add(i);
            backtracking(i, count);
            start.clear();
        }
        bw.write(minGap + "\n");
        bw.flush();
        bw.close();
    }

    static void backtracking(int a, int count) {
        if(count >= N/2) { // 한 팀 다 골랐어
            int totalS = 0, totalL = 0;
            for (int i = 0; i < N; i++) {
                if(!isUsed[i]) link.add(i);
            }
            for (int i = 0; i < N; i++) {
                if(isUsed[i]) { // startTeam
                    for (int j = 0; j < N / 2; j++) {
                        if(start.get(j) == i) continue;
                        totalS += S[i][start.get(j)];
                    }
                }
                else {
                    for (int j = 0; j < N / 2; j++) { // linkTeam
                        if(link.get(j) == i) continue;
                        totalL += S[i][link.get(j)];
                    }
                }
            }
            minGap = Math.min(minGap, Math.abs(totalS - totalL));
            link.clear();
            return;
        }

        for (int i = a; i < N; i++) {
            if(isUsed[i]) continue;
            isUsed[i] = true;
            start.add(i);
            backtracking(i, count + 1);
            isUsed[i] = false;
            start.remove(start.size() - 1);
        }
    }
}
