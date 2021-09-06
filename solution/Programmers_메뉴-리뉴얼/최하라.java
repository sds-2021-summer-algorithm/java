import java.util.*;

class Solution {
    static boolean[][] orderCheck;
    static int totalOrders;
    static HashMap<String, Integer> newCourseMap;
    static int[] maxLengCount;

    static int M;
    static String N;

    public String[] solution(String[] orders, int[] course) {
        orderCheck = new boolean[orders.length][27]; // 각 사람 주문 저장
        totalOrders = orders.length; // 총 주문자
        newCourseMap = new HashMap<>(); // 새로운 매뉴 중복 수 확인 및 저장
        maxLengCount = new int[21]; // 최대 중복 수 저장

        // 1. 각 주문 정렬하기
        sortOrder(orders);

        // 2. 모든 주문 저장
        storeOrder(orders);

        // 3. 각 주문의 주어진 길이만큼의 가능한 새로운 코스 map에 저장
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < course.length; j++) {
                // order의 길이가 주어진 길이보다 짧을 때
                if (orders[i].length() < course[j])
                    break;

                M = course[j];
                N = orders[i];
                buildNewCourse(0, 0, ""); // dfs
            }
        }

        // 4. 각 오더에 중복된 코스 있는지 확인
        Object[] mapKey = newCourseMap.keySet().toArray();
        for (int i = 0; i < mapKey.length; i++) { // 가능한 새로운 메뉴 총 개수 만큼
            String key = (String) mapKey[i];
            for (int j = 0; j < totalOrders; j++) { // 총 order 수 만큼
                boolean pass = true;
                for (int k = 0; k < key.length(); k++) { // 현재 키 길이 만큼
                    if (!orderCheck[j][key.charAt(k) - 'A']) {
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    newCourseMap.put(key, newCourseMap.get(key) + 1); // 중복 코스

                    if (newCourseMap.get(key) > maxLengCount[key.length()]) // 최대 중복된 수 저장
                        maxLengCount[key.length()] = newCourseMap.get(key);
                }
            }
        }

        // 5. 최대 중복인 코스만 저장
        ArrayList<String> newCourseList = new ArrayList<>();
        for (int i = 0; i < mapKey.length; i++) {
            String key = (String) mapKey[i];
            if (newCourseMap.get(key) == maxLengCount[key.length()] && maxLengCount[key.length()] > 1)
                newCourseList.add(key);
        }

        // 6. 결과 정렬 후 출력
        String[] result = new String[newCourseList.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = newCourseList.get(i);

        Arrays.sort(result);

        return result;
    }

    static void sortOrder(String[] orders) {
        for (int i = 0; i < orders.length; i++) {
            char[] c = orders[i].toCharArray();
            Arrays.sort(c);
            String str = "";
            for (char j : c)
                str += String.valueOf(j);
            orders[i] = str;
        }
    }

    static void storeOrder(String[] orders) {
        for (int i = 0; i < totalOrders; i++)
            for (int j = 0; j < orders[i].length(); j++)
                orderCheck[i][orders[i].charAt(j) - 'A'] = true;
    }

    static void buildNewCourse(int depth, int at, String course) {
        if (depth == M)
            newCourseMap.put(course, 0);

        for (int i = at; i < N.length(); i++)
            buildNewCourse(depth + 1, i + 1, course + N.charAt(i));
    }
}