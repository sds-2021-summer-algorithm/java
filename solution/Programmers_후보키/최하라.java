import java.util.*;

class Solution {
    static int colSize;
    static boolean[] visit;
    static ArrayList<HashSet<Integer>> candidateKeyList;

    public static int solution(String[][] relation) {
        colSize = relation[0].length;
        candidateKeyList = new ArrayList<>();

        // generate key set
        for (int i = 1; i <= colSize; i++) {
            HashSet<Integer> keySet = new HashSet<>();
            visit = new boolean[colSize];
            generateKeySet(keySet, i, 0, 0, relation);
        }

        return candidateKeyList.size();
    }

    static void generateKeySet(HashSet<Integer> keySet, int size, int idx, int start, String[][] relation) {
        if (idx == size) {
            for (HashSet<Integer> set : candidateKeyList) {
                if (keySet.containsAll(set))
                    return;
            }

            // keyset ready & check uniqueness
            if (isUnique(keySet, relation)) {
                candidateKeyList.add(keySet);
            }
        }

        for (int i = start; i < colSize; i++) {
            HashSet<Integer> newSet = new HashSet<>(keySet);
            newSet.add(i);
            generateKeySet(newSet, size, idx + 1, i + 1, relation);
        }
    }

    static boolean isUnique(HashSet<Integer> keySet, String[][] relation) {
        HashSet<String> tmpSet = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            String key = "";
            for (int j : keySet) {
                key += relation[i][j];
            }
            if (tmpSet.contains(key))
                return false;
            tmpSet.add(key);
        }
        return true;
    }
}