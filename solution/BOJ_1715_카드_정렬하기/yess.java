
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); 
		PriorityQueue <Integer> pq = new PriorityQueue<Integer>(); 
		for(int i = 0 ; i < N ; i ++) {
			pq.add(Integer.parseInt(br.readLine())); 
		}
		int answer = 0 ; 
		if (pq.size() == 0 ) {
			System.out.println(0);
			return ; 
		}
		while (pq.size() >= 2) {
			int num1 = pq.poll();
			int num2 = pq.poll();
			answer += num1 + num2; 
			pq.add(num1 + num2); 
		}
		System.out.println(answer);
		
	}
}
