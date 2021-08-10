package DAY01.P1713;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    static int N, K;
    static int[] inputs;
    static Person[] people;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY01/P1713/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        inputs = new int[K];
        people = new Person[101];


        List<Person> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            System.out.println(num);
            if(people[num] == null) {
                people[num] = new Person(num, 0, 0, false);
            }
            if(people[num].isIn == true) {
                people[num].count ++;
            } else {
                if(list.size() == N) {
                    Collections.sort(list);
                    Person p = list.remove(0);
                    p.isIn = false;
                }
                people[num].count = 1;
                people[num].isIn = true;
                people[num].timeStamp = i;
                list.add(people[num]); //같은 레퍼런스로 연결되어 있음
            }
        }
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
//    			return Integer.compare(p1.num, p2.num);
                return p1.num - p2.num;
            }
        });
    }
}

class Person implements Comparable<Person>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Person(int num, int count, int timeStamp, boolean isIn) {
        super();
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }
    @Override
    public int compareTo(Person person) { //person 과 위에 변수들(num,count ..)
        //-1 바꾸지 않음
        int result = count - person.count;
        // 0 바꾸지 않음
        if(result == 0) {
            return timeStamp - person.timeStamp;
        }else {
            return result;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "num=" + num +
                ", count=" + count +
                ", timeStamp=" + timeStamp +
                ", isIn=" + isIn +
                '}';

    }
}