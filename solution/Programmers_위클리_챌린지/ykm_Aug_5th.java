package Programmers; // 자식수가 6인 tree?
import java.io.*;
import java.util.*;

public class weekly0902 {
    static class Node {
        String data;
        Node [] child = new Node [5];

        public Node(){}
    }
    
    static class Tree{ // child수가 5
        public Node root;
    
        public void setRoot(Node node) {
            this.root = node;
        }
        
        public Node getRoot() {
            return root;
        }

        public void setChild(Node parent) {
            parent.child[0].data = parent.data.concat("A");
            parent.child[1].data = parent.data.concat("E");
            parent.child[2].data = parent.data.concat("I");
            parent.child[3].data = parent.data.concat("O");
            parent.child[4].data = parent.data.concat("U");
        }

        public int build(String word){
            char [] letters = {'A','E','I','O','U'};
            int count = 0;
            int depth = 0;
            // 전위순회 
            while(true){
                for(int i = 0 ; i<5; i++){
                    if(word.charAt(depth)==letters[i]){
                        if(word.length()-1 == depth){
                            return count;
                        }
                    }
                    count++;
                }
                depth++;
            }
        }
    }

    static class Solution {
        // bfs  cf. 전위순회
        public int solution(String word) {
            PriorityQueue<String> q = new PriorityQueue<String>();
            int count = 0;
            String [] letters = {"A","E","I","O","U"};
            for(int i =0 ; i<5; i++){
                q.add(letters[i]);
            }
            
            while(!q.isEmpty()){
                String current = q.poll(); // 큐에서 꺼냄

                if(word.equals(current)){ // 알파벳과 큐에서 꺼낸 단어가 같음
                    return count + 1;
                }else count++;
                

                for(int i = 0 ; i<5; i++){ // 다음 방문
                    String next = current;
                    if(current.length()<5){
                        next = next.concat(letters[i]);
                        q.add(next);
                    }                    
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution answer = new Solution();
        System.out.println(answer.solution("EIO"));
    }
}
