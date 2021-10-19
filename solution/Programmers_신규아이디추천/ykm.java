class Solution {

    public String solution(String new_id) {

        //step1
        new_id = new_id.toLowerCase();

        //step2
        new_id = new_id.replaceAll("[~!@#$%^&*()=+\\[\\{\\]\\}:?,<>/]", "");

        //step3
        int prev = new_id.length();
        while(true){
            new_id = new_id.replace("..", ".");
            int after = new_id.length();
            if(prev==after) break;
            prev = after;
        }

        // step4
        if(new_id.length()>=1 && new_id.charAt(0)=='.') new_id = new_id.substring(1, new_id.length());
        if(new_id.length()>=1 && new_id.charAt(new_id.length()-1)=='.') new_id = new_id.substring(0, new_id.length()-1);

        // step5
        if(new_id.equals("")) new_id = "a";

        // step6
        if(new_id.length()>=16){
            new_id = new_id.substring(0, 15);

            for(int i = 14; i>=0; i--){
                if(new_id.charAt(i)!='.'){
                    new_id = new_id.substring(0, i+1);
                    break;
                }
            }
        }

        // step7
        char last = new_id.charAt(new_id.length()-1);
        while(new_id.length()<3){
            new_id+=last;
        }

        return new_id;
    }
}
