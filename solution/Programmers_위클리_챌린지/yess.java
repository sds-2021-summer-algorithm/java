```

import java.util.Arrays;

class Part implements Comparable<Part>{
    String name; 
    int point ; 
    public Part(String name, int p) {
        this.name = name; 
        this.point =p ;
    }
    @Override 
    public int compareTo( Part p) {
        if(this.point == p.point ) {
            return this.name.compareTo(p.name); 
        }
        return p.point - this.point; 
    }
}
class Solution {
    static String [] part = new String [5]; 
    static String [][] point = new String [5][5] ; 
    static int [] sum  = new int [5]; 
    static int [][] get_point; 
    static Part [] p ; 
  
    public String solution(String[] table, String[] languages, int[] preference) {
        // 각 언어별 점수 구하기위한 배열 
        get_point = new int[languages.length][5]; 
      
        // table 구조화 
        for(int i = 0 ; i < table.length; i ++) {
            String temp[] = table[i].split(" ");
            part[i] = temp[0]; 
            for(int j = 0 ; j < point[0].length; j ++) {
                point[j][i] = temp[j + 1]; 
            }

        }

        // 각 언어별 점수 구하기 
        get_point = new int[languages.length][5]; 
        for(int l = 0 ; l <languages.length; l ++) {
            for(int i = 0 ; i < point[0].length; i ++) {
                for(int j = 0 ; j <point.length; j ++) {
                    if(point[j][i].equals(languages[l])) {
                        get_point[l][i] = 5-j; 
                        break;
                    }
                }
            }
        }

        for(int i = 0 ; i < get_point[0].length; i ++) {
            int tot = 0 ; 
            for(int j = 0 ; j <get_point.length ; j ++) {
                tot += get_point[j][i] * preference[j]; 
            }
            sum[i] = tot; 
        }
        p = new Part [5]; 
        for(int i = 0 ; i < p.length; i ++) {
            p[i] = new Part(part[i],sum[i]); 
        }
        Arrays.sort(p);

        return p[0].name; 
    }
}
```
