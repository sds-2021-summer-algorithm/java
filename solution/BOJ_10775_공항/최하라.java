import java.io.*;
import java.util.*;

public class Main {
    static int G, P;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        // 2. 부모 설정
        parent = new int[G + 1];
        for (int i = 1; i <= G; i++)
            parent[i] = i;

        // 3. union & find 적용
        int maxP = 0;
        for (int i = 1; i <= P; i++) {
            int gate = Integer.parseInt(br.readLine());
            int place = find(gate);
            if (place == 0)
                break;
            maxP++;
            union(place, place - 1);
        }
        System.out.println(maxP);
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static int find(int id) {
        if (parent[id] == id)
            return id;
        return parent[id] = find(parent[id]);
    }
}