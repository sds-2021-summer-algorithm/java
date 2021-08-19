import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }

        int left = 0;
        int right = max - min;

        while(left <= right){
            int mid = (left + right) / 2;
            if(Mid(mid,m,nums)){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        System.out.println(left);

    }

    private static boolean Mid(int mid, int m, int[] nums) {
        int count = 1;
        int min = nums[0];
        int max = nums[0];
        for(int i=1; i<nums.length; i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);

            if((max - min) > mid){
                min = nums[i];
                max = nums[i];
                count++;
                if(count > m) return false;
            }
        }
        return true;
    }
}
