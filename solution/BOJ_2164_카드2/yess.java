
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		int N = scan.nextInt(); 
		System.out.println(solution(N)); 
	}
	public static int solution(int n) {
		List <Integer> list = new LinkedList<>(); 
		for(int i = 1; i <= n ; i ++) {
			list.add(i);
		}
		while (list.size() != 1) {
			// 빼기 
			list.remove(0);
			// 순서 바꾸기
			int temp = list.get(0);
			list.remove(0);
			list.add(list.size(),temp);
		}
		return list.get(0);
	}

}
