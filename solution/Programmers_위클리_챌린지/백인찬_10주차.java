import java.io.*;
import java.util.*;

class Solution {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static long minY, minX, maxY, maxX;
    static PriorityQueue<Point> crossPoints = new PriorityQueue<>((o1, o2) -> {
        if(o1.y == o2.y) return (int) (o2.x - o1.x);
        return (int) (o2.y - o1.y);
    });
    public String[] solution(int[][] lines) {
        minY = Long.MAX_VALUE;
        minX = Long.MAX_VALUE;
        maxY = Long.MIN_VALUE;
        maxX = Long.MIN_VALUE;
        findCrossPoints(lines);
        String[] answer = new String[(int)(maxY - minY + 1)];
        String str = ".";
        long curY = crossPoints.peek().y;
        StringBuilder sb = new StringBuilder(str.repeat((int)(maxX - minX + 1)));
        Arrays.fill(answer, sb.toString());
        while(!crossPoints.isEmpty()) {
            Point cur = crossPoints.remove();
            if(cur.y != curY) {
                answer[(int)-(curY - maxY)] = sb.toString();
                curY = cur.y;
                sb = new StringBuilder(str.repeat((int)(maxX - minX + 1)));
            }
            sb.replace((int)(cur.x - minX), (int)(cur.x - minX + 1), "*");
        }
        answer[(int)-(curY - maxY)] = sb.toString();
        return answer;
    }
    static void findCrossPoints(int[][] lines) {
        for (int i = 0; i < lines.length -1 ; i++) {
            long A = lines[i][0];
            long B = lines[i][1];
            long E = lines[i][2];

            for (int j = i + 1; j < lines.length; j++) {
                long C = lines[j][0];
                long D = lines[j][1];
                long F = lines[j][2];
                double x, y;

                long Det = A * D - B * C;
                if(Det == 0) continue;

                x = (double) (B * F - E * D) / Det;
                y = (double) (E * C - A * F) / Det;

                if(!isInteger(x)) continue;
                if(!isInteger(y)) continue;

                maxX = Math.max(maxX, (long) x);
                maxY = Math.max(maxY, (long) y);
                minX = Math.min(minX, (long) x);
                minY = Math.min(minY, (long) y);
                crossPoints.add(new Point((long) x, (long) y));
            }
        }
    }
    static boolean isInteger(double a) {
        return a - (long) a == 0;
    }
}


public class programmers_10 {
    public static void main(String[] args) {
        int[][] lines = {{0, 1, 0}, {1, 0, 5}, {1, 0, -3}};
        Solution s = new Solution();
        String[] solution = s.solution(lines);
        System.out.println(Arrays.toString(solution));
    }
}