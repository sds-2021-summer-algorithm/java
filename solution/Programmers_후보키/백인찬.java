package DAILY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution2 {
    static int columns;
    static boolean[] uniqueness;
    static List[] minimalWith;
    static boolean[] minimality;
    static boolean[] selected;
    static class Pair {
        List<Integer> keys;

        public Pair() {
            keys = new ArrayList<>();
        }
    }
    public int solution(String[][] relation) {
        columns = relation[0].length;
        uniqueness = new boolean[columns];
        minimality = new boolean[columns];
        minimalWith = new List[columns];
        for (int i = 0; i < columns; i++) {
            minimalWith[i] = new ArrayList<Integer>();
        }
        int answer = 0;
        for (int i = 0; i < columns; i++) {
            if(checkUniqueness(relation, i)) {
                uniqueness[i] = true;
                minimality[i] = true;
                minimalWith[i].add(i);
                answer++;
            }
        }
        for (int i = 2; i <= columns; i++) {
            selected = new boolean[columns];
            for (int j = 0; j < columns; j++) {
                if(uniqueness[j]) continue;

            }
        }

        return answer;
    }
    public static void makeChunk(int now, int count, final int max) {
        if(count == max) {
            Pair pair = new Pair();
            for (int i = 0; i < columns; i++) {
                if(selected[i]) {
                    pair.keys.add(i);
                }
            }

        }
        for (int i = now; i < columns; i++) {
            if(selected[now]) continue;
            if(minimality[now]) continue;
            selected[now] = true;
            makeChunk(now, count + 1, max);
            selected[now] = false;
        }
    }

    public static boolean checkMinimality(Pair pair) {

    }
    public static boolean checkUniqueness(String[][] relation, int column) {
        Set<String> set = new HashSet<>();
        for (String[] row : relation) {
            set.add(row[column]);
        }
        return set.size() == relation.length;
    }
}


public class programmers_후보키 {
    public static void main(String[] args) {

    }
}
