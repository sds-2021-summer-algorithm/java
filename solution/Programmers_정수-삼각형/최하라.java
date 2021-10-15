class Solution {
    public int solution(int[][] triangle) {
        //1. set dp
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];

        //2. set start point
        dp[0][0] = triangle[0][0];

        //3. 최대값 저장하기
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = triangle[i][j] + dp[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }

        //4. max값 찾기 & 결과 출력
        int max = 0;
        for(int i : dp[dp.length - 1]) max = Math.max(max, i);

        return max;
    }
}
