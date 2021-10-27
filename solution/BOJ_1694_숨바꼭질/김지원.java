import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
		
	static void bfs(int[] check,int x,int k) {
		Queue<Integer> q = new LinkedList<>();
	
		q.offer(x);
		check[x] = 0;
		
		while(!q.isEmpty()) {
			int n = q.poll();
							
			if (check[k] != 0) break;
			
			//if(n+1 >=check.length || n-1<0 || n*2 >= check.length) continue;
			
			if(n+1 <check.length&& check[n+1] == 0) {
				check[n+1] =check[n]+1;
				q.offer(n+1);
			}
			if( n-1>=0 && check[n-1] == 0) {
				check[n-1] = check[n]+1;
				q.offer(n-1);
			}
			if( n*2 < check.length && check[n*2] == 0) {
				check[n*2] = check[n]+1;
				q.offer(n*2);
			}
			
		}
	}
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int k = in.nextInt();		
		
		int check[] = new int[100001];	
		
		if(n == k) {
			System.out.println(0);
			return;
		}
		
		bfs(check,n,k);
		
		System.out.println(check[k]);
		
	}
}
