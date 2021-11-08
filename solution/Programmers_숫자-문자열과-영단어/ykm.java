class Solution {
      public int solution(String s) {

          String[] string_to_num = {"zero","one","two","three","four","five","six","seven","eight","nine"};
          String temp = "";
          String answer = "";
          for(int i = 0 ; i<s.length(); i++){
              char current = s.charAt(i);

              if(current>='0' && current<='9') {
                  answer += current;
                  continue;
              }
              else temp+=current;

              for(int j = 0; j<10; j++){
                  if(temp.equals(string_to_num[j])){
                      answer += Integer.toString(j);
                      temp = "";
                      break;
                  }
              }
          }

          return Integer.parseInt(answer);
      }
  }
