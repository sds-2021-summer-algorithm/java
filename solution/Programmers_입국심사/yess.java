```
import java.util.Arrays;
import java.util.Scanner;
class Solution {
   public long  solution( long n ,  int  [] times) {
		long answer = 0; 
		Arrays.sort(times);
		long min = times[0] * n ; 
		answer = find_min_time(min, times, n ); 
		return answer; 
	}
	public long  find_min_time(long min, int  [] times, long  n ) {
		long length = min; 
		long start = 0 ;
		long  end = length ; 
		
		
		while (start < end) {
			
			long  cnt = 0 ; 
			long  mid = ( start + end ) / 2; 
			for(int i = 0 ; i < times.length; i ++) {
				cnt += mid / times[i]; 
			}
			if (cnt >= n ) {
				end = mid; 
			}
			else {
				start = mid + 1; 
			}
			
		}
		return start; 
	}
}
```
