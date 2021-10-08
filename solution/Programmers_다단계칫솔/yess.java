```

3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    // 부모랑 자식 연결짓기 
    static HashMap <String , String > map = new HashMap <String , String > (); 
    // 나와 돈을 연결짓는 해쉬맵
    static HashMap <String, Integer> map2  = new HashMap <String , Integer> (); 

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        makeTree(enroll, referral);
        calculate(seller,amount);
        int []answer = new int [enroll.length]; 

        for(int i = 0 ; i < answer.length; i ++) {
            answer[i] = map2.get(enroll[i]); 
        }
        return answer; 
    }
     void calculate(String [] seller, int [] amount) {

        for(int i = 0 ; i < seller.length; i ++) {
            String now = seller[i]; 
            int nowmoney = amount[i] * 100 ; 
            while (!now.equals("-")) {
                int sendmoney = nowmoney / 10 ;
                map2.put(now, map2.get(now) + (nowmoney - sendmoney ));
                if (sendmoney < 1 ) break; 
                now = map.get(now); 
                nowmoney = sendmoney; 
            }
        }
    }
     void makeTree(String [] enroll, String [] referral) {
        for(int i = 0 ; i < enroll.length; i ++) {  
            map.put(enroll[i], referral[i]); 
            map2.put(enroll[i], 0);
        }
    }
}
```
