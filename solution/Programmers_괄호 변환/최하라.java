import java.util.*;

public class Solution {
    public String solution(String p) {
        String answer = "";

        // 1. 빈 문자열
        if (p.length() == 0)
            return p;

        // 올바른 문자열 확인
        if (isBalanced(p))
            return p;

        return changeP(p);
    }

    // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야
    // 하며, v는 빈 문자열이 될 수 있습니다.
    public String changeP(String p) {
        if (p.length() == 0)
            return "";

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        int point = divPoint(p);
        String u = p.substring(0, point + 1);
        String v = p.substring(point + 1);

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        if (isBalanced(u)) {
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + changeP(v);
        }
        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
        else {
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            // 4-3. ')'를 다시 붙입니다.
            String tmp = "(" + changeP(v) + ")";

            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(')
                    sb.append(")");
                else
                    sb.append("(");
            }
            // 4-5. 생성된 문자열을 반환합니다.
            return tmp + sb.toString();
        }
    }

    public boolean isBalanced(String p) {
        // p가 올바른 문자열인지 확인
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            if (stack.isEmpty())
                stack.add(p.charAt(i));
            else if (p.charAt(i) == ')') {
                if (stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            } else
                stack.add(p.charAt(i));
        }

        if (!stack.isEmpty())
            return false;
        return true;
    }

    static int divPoint(String w) {
        int leftOp = 0;
        int rightOp = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(')
                rightOp++;
            else
                leftOp++;
            if (leftOp == rightOp)
                return i; // 두개의 괄호의 수가 같을 때 index 리턴
        }
        return w.length() - 1;
    }
}