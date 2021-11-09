import java.util.*;

class Solution {
      public int[] solution(int N, int[] stages) {
          int maxStage = 0;
          for(int i = 0 ; i< stages.length; i ++){
              maxStage = Math.max(maxStage, stages[i]);
          }

          int[] reach = new int[N+1]; // 각 스테이지를 지나간 (도달했던) 사람수
          int[] current = new int[N+1]; // 각 스테이지에 위치한 사람수

          for(int i = 0 ; i< stages.length; i++){
              for(int j = 0 ; j<stages[i]; j++){
                  reach[j]++;
              }
              current[stages[i]-1]++;
          }

          double [][] rate = new double[N][2];
          for(int i = 0; i<N; i++){
              rate[i][1] = i+1;
              if(reach[i]==0) continue;
              else if(current[i]==0) continue;
              else rate[i][0] = (double)current[i]/reach[i];
          }

          Arrays.sort(rate, new Comparator<double[]>() {
              @Override
              public int compare(double[] o1, double[] o2) {
                  if(Double.compare(o1[0],o2[0])==0) return Double.compare(o1[1],o2[1]);
                  else return -Double.compare(o1[0],o2[0]);
              }
          });

          int[] answer = new int[N];
          int index = 0;
          for(int i = 0 ; i<rate.length ; i++){
              answer[index] = (int) rate[i][1];
              if(answer[index]>N){
                  index--;
              }
              index++;
          }
          return answer;
      }
  }
