import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int checkpointNum;
    static Node[] checkpoints;
    static int[] distance;
    static int totalDistance;

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int manhatanDistance(Node n1, Node n2){
        int diffX = Math.abs(n1.x - n2.x);
        int diffY = Math.abs(n1.y - n2.y);
        return diffX + diffY;
    }

    static int deletePoint(int delPoint){
        int prevPoint = delPoint-1;
        int nextPoint = delPoint+1;

        return totalDistance 
            + manhatanDistance(checkpoints[prevPoint], checkpoints[nextPoint]) 
            - manhatanDistance(checkpoints[prevPoint], checkpoints[delPoint]) 
            - manhatanDistance(checkpoints[delPoint], checkpoints[nextPoint]);
    }

    private static int wholeDistance() {
        int distance = 0;
        int prevPoint = 0;
        int currentPoint;
        for(int i = 1; i<checkpointNum; i++){
            currentPoint = i;
            distance += manhatanDistance(checkpoints[prevPoint], checkpoints[currentPoint]);
            prevPoint = currentPoint;
        }

        return distance;
    }
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        checkpointNum = Integer.parseInt(br.readLine());
        checkpoints = new Node[checkpointNum];
        distance = new int[checkpointNum];

        for(int i = 0 ; i<checkpointNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            checkpoints[i] = new Node(x, y);
        }
        totalDistance = wholeDistance();

        int answer = Integer.MAX_VALUE;
        for(int i = 1; i<checkpointNum-1; i++){
            // 시작 포인트와 도착 포인트를 제외한 포인트
            answer = Math.min(answer,deletePoint(i));
        }
        System.out.println(answer);
        br.close();
    }
}
