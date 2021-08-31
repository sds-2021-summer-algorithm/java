public class Solution {
    public int solution(String s) {
        int min = s.length();
        int equalSub, result;

        // 최대 s/2의 길이 만큼 확인
        for (int i = 1; i <= s.length() / 2; i++) {
            String currentSub = "", nextSub = "";
            equalSub = 1;
            result = 0;

            // 1부터 s/2길이 만큼 각각 압축 가능한지 확인
            for (int j = 0; j <= s.length() / i; j++) {
                int start = i * j;
                int end = i * (j + 1) > s.length() ? s.length() : i * (j + 1);
                currentSub = nextSub;
                nextSub = s.substring(start, end);

                if (currentSub.equals(nextSub)) { // 압축 가능
                    equalSub++;
                } else { // 압출 불가능
                    result += (equalSub > 1 ? String.valueOf(equalSub).length() : 0) + currentSub.length();
                    equalSub = 1;
                }
            }
            result += (equalSub > 1 ? String.valueOf(equalSub).length() : 0) + nextSub.length();

            // 압축한 문자열 최소 길이 저장
            min = Math.min(min, result);
        }
        return min;
    }
}