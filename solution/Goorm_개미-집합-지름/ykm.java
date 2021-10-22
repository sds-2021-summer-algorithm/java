import java.io.*;
import java.util.*;
class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] ants = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N ; i++){
			ants[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ants);
		int min = ants[0];
		int max = ants[ants.length-1];
		int answer = 0;

		if((max-min)>M){
				int[] position = new int[max-min+1];
				for(int i = 0 ; i<ants.length; i++){
						position[ants[i]-min]++;
				}

				int front = 0;
				int end = 0;
				int index = 0;
				while((max-min)>M){
						front += position[index]; 
						end += position[max-min-index];
						M++;
						index++;
				}
				answer = Math.min(front, end);
		}

		
		System.out.println(answer);
	}
}
