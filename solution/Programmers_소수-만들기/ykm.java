class Solution {
    static boolean[] isPrime = new boolean[3000];
    static int answer = 0;
    
    private static void makePrime(){
        
        for(int i = 0; i<3000; i++){
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i<3000; i++){
            if(isPrime[i]){
                for(int j = 2; j<3000;j ++){
                    if(i*j<3000) isPrime[i*j] = false;
                }
            }
        }
    }
    
    private static boolean DFS(int[] nums, int x, int count, int sum){
        if(count == 3){
            if(isPrime[sum]) {
                answer++;
                return true;
            }
            else return false;
        }
        else{
            for(int i = x+1; i<nums.length; i++){
                DFS(nums, i, count+1, sum+nums[i]);
            }
        }
        return false;
    }
  
    public int solution(int[] nums) {
        makePrime();
        for(int i = 0 ; i<nums.length-1; i++){
            DFS(nums, i, 1, nums[i]);
        }   
        return answer;
    }
}
