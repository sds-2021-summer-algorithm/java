```
import java.util.PriorityQueue;
class Player implements Comparable <Player>{
    int id; 
    int indegree; 
    int outdegree; 
    int none ; 
    int heavy; 
    int myweight; 
    int tot ; 
    double  score; 
    public Player (int id, int weight) {
        this.id = id;
        this.myweight = weight; 
    }
    @Override 
    public int compareTo(Player p) {
        if(p.score == this.score){
            if(this.heavy == p.heavy){
                if(this.myweight == p.myweight){
                    return this.id - p.id; 
                }return p.myweight - this.myweight;
            }return p.heavy - this.heavy; 
        }
        else if(this.score < p.score ){
            return 1; 
        }
        else{
            return -1; 
        }
    }
}
class Solution {

    static int size; 
    static char [][] arr; 
    static Player [] player; 
    public int[] solution(int[] weights, String[] head2head) {
        PriorityQueue<Player> pq = new PriorityQueue<>(); 
        size = weights.length ;
        arr = new char[size][size]; 
        player = new Player [size]; 
        for(int i = 0 ; i < size; i ++ ) {
            String temp =   head2head[i]; 
            player[i]= new Player(i,weights[i]);
            for(int j = 0 ; j < size; j ++) {
                arr[i][j] = temp.charAt(j);
                if(i != j  && arr[i][j] == 'W') {
                    player[i].outdegree ++; 
                    if(weights[j] > weights[i]) player[i].heavy ++; 
                }
                if(i != j && arr[i][j] == 'L') {
                    player[i].indegree ++; 
                }
                if(i != j && arr[i][j] == 'N'){
                    player[i].none ++; 
                }
            }
            if(player[i].none == size -1 ) player[i].score = 0 ; 
            else {
                player[i].score = player[i].outdegree * 100  / (double) (size - player[i].none - 1);
            }
            pq.add(player[i]); 

        }
        int [] answer = new int [size];
        int idx = 0 ; 
        while(!pq.isEmpty()) {
            Player now = pq.poll();
            answer[idx] = now.id + 1; 
            idx ++; 
        }
        return answer; 
    }
}
```
