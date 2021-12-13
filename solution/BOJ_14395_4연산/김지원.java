import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static HashSet<Integer> hs;
    
    static class Info {
        int val;
        String str="";
        public Info(int val, String str){
            this.val = val;
            this.str = str;
        }
    };
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String ss = br.readLine();
        StringTokenizer st = new StringTokenizer(ss);
    	int start = Integer.parseInt(st.nextToken());
    	int target = Integer.parseInt(st.nextToken());
    	int mulMax = 34000, maxi=1000000000;
        //초기화
        hs = new HashSet<>();
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(start,""));
        String as="";
        boolean finish=false;
        
        while(!q.isEmpty()){
            Info ii = q.poll();
            int cv = ii.val;
            String cs = ii.str;
            if(cv==target){
                as = cs;
                finish=true;
                continue;
            }
            if(as.length()!=0 && as.length()<=cs.length()) continue;
            if(!hs.contains(0)){
                hs.add(0);
                q.offer(new Info(0,cs+"-"));
            }
            if(cv<=mulMax && !hs.contains(cv*cv)){
                q.offer(new Info(cv*cv,cs+"*"));
                hs.add(cv*cv);
            }
            if(cv+cv<=maxi && !hs.contains(cv+cv)){
                q.offer(new Info(cv+cv,cs+"+"));
                hs.add(cv+cv);
            }
            if(cv!=0 && !hs.contains(cv/cv)){
                q.offer(new Info(cv/cv,cs+"/"));
                hs.add(cv/cv);
            }
        }
        if(!finish) System.out.print(-1);
        else System.out.print(as.equals("")? 0 : as);
	}
}
