```
class Solution {
   
	static String alpabet = "AEIOU"; 
	static String [] dict = alpabet.split("");
    static int result = 0 ; 
    static int count = 0 ; 
    public int   solution(String word) {
        StringBuilder sb = new StringBuilder(); 
        dfs(5,0,sb,word);
        return result ; 
      
    }
   void  dfs(int target, int cnt , StringBuilder sb,String word) {
		if(sb.toString().equals(word)) {
            result = count ; 
            return ; 
		}
		
		if(target == cnt ) {
			return ; 
		}
		
		for(int i = 0 ; i < dict.length; i ++) {
			sb.append(dict[i]); 
            count ++; 
			dfs(target,cnt +1, sb,word); 
			sb.deleteCharAt(sb.length()-1); 
			
		}
         
	}
  
}
```
