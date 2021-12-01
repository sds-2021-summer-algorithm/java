
import java.util.*;

class Solution {
    static String[][] table;
    static int columns;
    static boolean[] uniqueness;

    static boolean[] selected;
    static class Pair {
        List<Integer> keys;

        public Pair() {
            keys = new ArrayList<>();
        }
    }
    static List<List<Integer>> compositeKeys;

    public int solution(String[][] relation) {
        table = relation;
        columns = table[0].length;
        compositeKeys = new ArrayList<>();
        uniqueness = new boolean[columns];

        int answer = 0;

        // uniqueness 체크
        for (int i = 0; i < columns; i++) {
            if(checkUniquenessByOne(i)) {
                uniqueness[i] = true;

                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                compositeKeys.add(temp);
                answer++;
            }
        }
        for (int i = 2; i <= columns; i++) {
            selected = new boolean[columns];
            for (int j = 0; j < columns; j++) {
                if(uniqueness[j]) continue;
                makeCompositeKey(j, 0, i);
            }
        }


        return compositeKeys.size();
    }
    public static void makeCompositeKey(int now, int count, final int max) {
        if(count == max) {
            Pair pair = new Pair();
            for (int i = 0; i < columns; i++) {
                if(selected[i]) {
                    pair.keys.add(i);
                }
            }
            if(checkMinimality(pair)) {
                if(checkUniquenessByCompositeKey(pair)) {
                    compositeKeys.add(new ArrayList<>(pair.keys));
                }
            }
        }
        for (int i = now; i < columns; i++) {
            if(selected[i]) continue;
            if(uniqueness[i]) continue;
            selected[i] = true;
            makeCompositeKey(now, count + 1, max);
            selected[i] = false;
        }
    }

    public static boolean checkMinimality(Pair pair) {
        for (List<Integer> key : compositeKeys) {
            if(pair.keys.containsAll(key)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUniquenessByCompositeKey(Pair pair) {
        Set<List<String>> set = new HashSet<>();
        for (String[] row: table) {
            List<String> temp = new ArrayList<>();
            for (int column : pair.keys) {
                temp.add(row[column]);
            }
            set.add(temp);
        }
        return set.size() == table.length;
    }
    public static boolean checkUniquenessByOne(int column) {
        Set<String> set = new HashSet<>();
        for (String[] row : table) {
            set.add(row[column]);
        }
        return set.size() == table.length;
    }
}
