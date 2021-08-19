import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
class App {
	int memory; 
	int cost; 
	public App(int m , int c) {
		this.cost  =c; 
		this.memory = m; 
	}
	public App() {
		
	}
	
}
public class Main {
	static int n ; 
	static int m ; 
	static int [][] dp; 
	static App [] app; 
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		dp = new int[n + 1][100 * 100 + 1]; 
		app = new App[n+1]; 
		for(int i = 1; i <= n; i ++) {
			app[i] = new App(); 
		}
		st = new StringTokenizer(br.readLine()); 
		for(int i = 1; i <= n ;  i ++) {
			app[i].memory = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()); 
		for(int i = 1; i <= n ;  i ++) {
			app[i].cost = Integer.parseInt(st.nextToken());
		}; 
		
		for(int i = 1; i <= n ; i ++) {
			for(int j = 0 ; j < dp[0].length; j ++) {
				if(j >= app[i].cost) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-app[i].cost] + app[i].memory ); 
				}
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j]); 
			}
		}
		for(int i = 0 ; i < dp[0].length; i ++) {
			if(dp[n][i] >= m) {
				bw.write(String.valueOf(i));
				break;
			}
		}
		bw.flush();
		bw.close();
		br.close();
		
	}
}
