import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1일차 1번째 문제
//고스택 https://www.acmicpc.net/problem/3425
// 코드 작성 X --> C++로 해줘서 정리해야됨 코드 바꾸기도 해야되고 @@@@@@@@@@@@@@@@@@@@@@@ 3번째
// 이해
// 손글씨 정리
// 다시 코드 작성
class Literal{
    String name;
    Long number;
}

public class Main {
    static ArrayList<String> lit = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        // for문으로 input만큼 돌리기 + 돌릴때마다 스택은 초기화 + .QUIT나오면 나가리 + 스택에 저장된게 1개가 아니면 에러 + 끝나면 빈줄 넣기
        while (true) {
//            System.out.println("시작합니다");
            String inputss = br.readLine();
            if (inputss.equals("QUIT")) {
                break;
            }
//            if (inputss.equals("")) {
////                System.out.println("초기화 합니다.");
//                lit.clear();
//                continue;
//            }
            lit.add(inputss);
            if (inputss.equals("END")) {
                int count = Integer.parseInt(br.readLine());
//                System.out.println("몇개 입력받을까? : "+count);
                for (int i = 0; i < count; i++) { //값들을 입력받음
                    long k = Integer.parseInt(br.readLine());
                    Stack<Long> mystack = new Stack<>();
                    mystack.push(k);
                    boolean error = false;
                    for (int j = 0; j < lit.size(); j++){
                        String command = lit.get(j);
//                        System.out.println("실행되는 명령어 : "+command);
                        String curcommand = command.substring(0, 3);
                        if (curcommand.equals("NUM")) {
                            mystack.push(Long.valueOf(command.substring(4)));
                        }
                        if (curcommand.equals("POP")) {
                            if (mystack.isEmpty()) { //비어있으면 POP을 못함
                                error = true;
                                break;
                            }
                            mystack.pop();
                        }
                        if (curcommand.equals("INV")) {
                            if (mystack.isEmpty()) { //비어있으면 INV를 못함
                                error = true;
                                break;
                            }
                            mystack.push(mystack.pop() * -1);
                        }
                        if (curcommand.equals("DUP")) {
                            if (mystack.isEmpty()) { //비어있으면 DUP를 못함
                                error = true;
                                break;
                            }
                            mystack.push(mystack.peek());
                        }
                        if (curcommand.equals("SWP")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long first = mystack.pop();
                            Long second = mystack.pop();
                            mystack.push(first);
                            mystack.push(second);
                        }
                        if (curcommand.equals("ADD")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long item = mystack.pop() + mystack.pop();
                            if (Math.abs(item) > Math.pow(10, 9)) {
                                error = true;
                                break;
                            }
                            mystack.push(item);
//                            System.out.println(item+"NUM item입니다");
                        }
                        if (curcommand.equals("SUB")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long first = mystack.pop();
                            Long second = mystack.pop();

                            Long item = second - first;
                            if (Math.abs(item) > Math.pow(10, 9)) {
                                error = true;
                                break;
                            }

                            mystack.push(item);
                        }
                        if (curcommand.equals("MUL")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long item = mystack.pop() * mystack.pop();
                            if (Math.abs(item) > Math.pow(10, 9)) {
                                error = true;
                                break;
                            }
                            mystack.push(item);
                        }
                        if (curcommand.equals("DIV")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long first = mystack.pop();
                            Long second = mystack.pop();
                            if (first == 0) {
                                error = true;
                                break;
                            }
                            Long item = Math.abs(second) / Math.abs(first);
                            if (Math.abs(item) > Math.pow(10, 9)) {
                                error = true;
                                break;
                            }
                            if (first * second < 0) {
                                mystack.push(item * -1);

                            } else {
                                mystack.push(item);
                            }
                        }
                        if (curcommand.equals("MOD")) {
                            if (mystack.size() < 2) {
                                error = true;
                                break;
                            }
                            Long first = mystack.pop();
                            Long second = mystack.pop();
                            if (first == 0) {
                                error = true;
                                break;
                            }
                            Long item = Math.abs(second) % Math.abs(first);
                            if (Math.abs(item) > Math.pow(10, 9)) {
                                error = true;
                                break;
                            }
                            if (second < 0) {
                                mystack.push(item * -1);

                            } else {
                                mystack.push(item);
                            }
                        }
                        if (curcommand.equals("END")){
                            break;
                        }
                    }
                    if (error == true || mystack.size() != 1) {
                        System.out.println("ERROR");
                    }
                    else{
                        System.out.println(mystack.pop());
                    }
                }
//                System.out.println("탈출");
                System.out.println("");
                br.readLine();
                lit.clear();
            }
        }
    }
}
