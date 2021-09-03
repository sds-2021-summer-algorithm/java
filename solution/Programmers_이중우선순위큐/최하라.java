import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < operations.length; i++) {
            StringTokenizer st = new StringTokenizer(operations[i]);
            String dirStr = st.nextToken();
            int dirNum = Integer.parseInt(st.nextToken());
            switch (dirStr) {
                case "I":
                    minPQ.add(dirNum);
                    maxPQ.add(dirNum);
                    break;
                case "D":
                    if (dirNum == 1) {
                        if (!maxPQ.isEmpty()) {
                            int num = maxPQ.poll();
                            minPQ.remove(num);
                        }
                    } else {
                        if (!minPQ.isEmpty()) {
                            int num = minPQ.poll();
                            maxPQ.remove(num);
                        }
                    }
            }
        }
        if (!maxPQ.isEmpty())
            answer[0] = maxPQ.poll();
        if (!minPQ.isEmpty())
            answer[1] = minPQ.poll();
        return answer;
    }
}