import java.io.*;
import java.util.*;

public class Main {
    static int[] durability;
    static boolean[] belt;
    static int N, K, zeros = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        durability = new int[2 * N + 1];
        belt = new boolean[2 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int steps = 1;
        while(true) {
            rotate();
            move();
            put();
            if(zeros >= K) break;
            else steps++;
        }
        bw.write(steps + "\n");
        bw.flush();
        bw.close();
    }

    static void rotate() {
        int dur2N = durability[2 * N];
        for (int i = 2 * N; i > 1; i--) {
            durability[i] = durability[i - 1];
        }
        durability[1] = dur2N;

        boolean belt2N = belt[2 * N];
        for (int i = 2 * N; i > 1; i--) {
            belt[i] = belt[i - 1];
        }
        belt[1] = belt2N;
        if(belt[N]) {
            belt[N] = false;
        }
    }

    static void move() {
        for (int i = N - 1; i >= 1; i--) {
            if (durability[i + 1] > 0 && !belt[i + 1]) {
                if (belt[i]) {
                    durability[i + 1]--;
                    if (durability[i + 1] == 0) {
                        zeros++;
                    }
                    belt[i + 1] = belt[i];
                    belt[i] = false;
                }
            }
        }
        if(belt[N]){
            belt[N] = false;
        }
    }

    static void put() {
        if(durability[1] > 0) {
            belt[1] = true;
            durability[1]--;
            if(durability[1] == 0) zeros++;
        }
    }
}