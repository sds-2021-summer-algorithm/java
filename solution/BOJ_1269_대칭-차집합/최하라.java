import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // Build set A
        HashSet<Integer> setA = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++)
            setA.add(Integer.parseInt(st.nextToken()));

        // Check with A while reading B
        st = new StringTokenizer(br.readLine());
        int count = 0;
        for (int i = 0; i < B; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (setA.contains(tmp)) {
                setA.remove(tmp);
            } else {
                count++;
            }
        }

        // Result
        System.out.println(setA.size() + count);
    }
}