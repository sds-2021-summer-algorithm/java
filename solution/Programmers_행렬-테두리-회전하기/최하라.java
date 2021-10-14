class Solution {
    static int[][] table;
    static int min;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        //1. build table
        table = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++)
                table[i][j] = num++;
        }

        //2. rotate table
        for (int i = 0; i < queries.length; i++) {
            rorate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
            answer[i] = min; // 최소값 저장
        }

        return answer;
    }

    static void rorate(int x1, int y1, int x2, int y2) {
        min = Integer.MAX_VALUE;
        int tmp = table[x1][y1];

        //좌 frame
        for (int i = x1; i < x2; i++) {
            table[i][y1] = table[i + 1][y1];
            min = Math.min(min, table[i][y1]);
        }

        //하
        for (int i = y1; i < y2; i++) {
            table[x2][i] = table[x2][i + 1];
            min = Math.min(min, table[x2][i]);
        }

        //우
        for (int i = x2; i > x1; i--) {
            table[i][y2] = table[i - 1][y2];
            min = Math.min(min, table[i][y2]);
        }

        //상
        for (int i = y2; i > y1; i--) {
            table[x1][i] = table[x1][i - 1];
            min = Math.min(min, table[x1][i]);
        }

        //저장해둔 첫 칸 마지막 idx에 넣기
        table[x1][y1 + 1] = tmp;
        min = Math.min(min, tmp);
    }
}
