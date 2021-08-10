package com.company;

// 코드 작성
// 이해
// 손글씨 정리
// 다시 코드 작성

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Node {
    int x;
    int y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int count = 1;
    static int[] order;
    static ArrayList<Node> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.valueOf(st.nextToken()); //정점의 갯수 받기
        int E = Integer.valueOf(st.nextToken()); // 간선의 갯수 받기

        ArrayList<ArrayList<Integer>> a = new ArrayList<>(); // 정점의 갯수만큼 arraylist만들어주기
        for (int i = 0; i <= V; i++){
            a.add(new ArrayList<>());
        }

        for (int i=0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.valueOf(st.nextToken());
            int B = Integer.valueOf(st.nextToken());

            a.get(A).add(B);
            a.get(B).add(A); //양방향 구현
        }

        order = new int[V+1];
        ans = new ArrayList<>();

        for(int i = 1; i <=V; i++){ //dfs 돌리기
            if (order[i] ==0){
                dfs(i,0,a);
            }
        }

        //정렬
        Collections.sort(ans, (a1, a2) -> (a1.x == a2.x) ? a1.y - a2.y : a1.x - a2.x);

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size() + "\n"); // 단절선의 목록 추가

        for (int i = 0; i < ans.size(); i++){
            sb.append(ans.get(i).x + " " + ans.get(i).y + "\n"); //단절선의 목록
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dfs(int vertax, int parent, ArrayList<ArrayList<Integer>> a){
        order[vertax] = count++;
        int ret = order[vertax];

        for (int now: a.get(vertax)){ // 간선 모두 확인
            if(now == parent){ //내가온길 제외
                continue;
            }

            if (order[now] == 0){ //자식정점이 방문되지 않았을 경우
                int low = dfs(now,vertax,a);

                if (low > order[vertax]){
                    if (now > vertax){
                        ans.add(new Node(vertax,now));
                    }
                    else{
                        ans.add(new Node(now,vertax));
                    }
                }
                ret = Math.min(ret, low);
            }
            else{ //자식 정점이 방문되었을 경우
                ret = Math.min(ret, order[now]);
            }
        }
        return ret;
    }
}
