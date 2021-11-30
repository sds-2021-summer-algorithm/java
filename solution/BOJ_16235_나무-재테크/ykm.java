import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    private static final Comparator<? super int[]> ageComparator = new Comparator<int []>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2]; // age에 따라 정렬
        }   
    };
    static int N;
    static int M;
    static int K;
    static int[][] nutrient; // 겨울에 추가될 양분
    static int[][] ground;    // 현재 땅의 상태
    static Queue<int[]> allTrees = new PriorityQueue<int[]>(ageComparator); // 살아있는 모든 나무 정보 (나이에 따라 오름차순 정렬 필요)
    static List<int[]> treeForSpread;// 이번해에 번식할 나무
    static List<int[]> deadTree;
    static int[] dx = {-1,-1,-1, 1,1, 1, 0,0};
    static int[] dy = {-1, 0, 1,-1,0, 1,-1,1};
    public static void main(String[] args) throws IOException{
       
        handleInput();
        while(allTrees.size()>0 && K --> 0){ // K년의 흐름 & 나무가 다 죽으면 반복할 이유가 없음.
            getAge();
            die(); 
            spread();
            addNutrient();
        }
        System.out.println(allTrees.size());        
    }

    private static void handleInput() throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 대지정보 입력받기(양분)
        nutrient = new int[N][N];
        ground = new int[N][N];
        for(int i = 0 ; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N; j++){
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        // 나무정보 입력받기(위치 x,y / 나이)
        for(int i = 0 ; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            allTrees.add(new int[] {x-1,y-1,age,0});
        }
        br.close();
    }

    private static void getAge() {

        Queue<int[]> temp = new PriorityQueue<int[]>(ageComparator);
        treeForSpread = new ArrayList<int[]>(); // 매해 초기화 필요
        deadTree = new ArrayList<int[]>();
        while(!allTrees.isEmpty()){
            int[] currentTree = allTrees.poll();
            int x = currentTree[0];
            int y = currentTree[1];
            int age = currentTree[2];

            if(ground[x][y]>=age){
                ground[x][y] -= age;
                temp.add(new int[]{x,y,age+1});
                if((age + 1) % 5 ==0){
                    treeForSpread.add(new int[]{x,y});
                }
            }else{
                deadTree.add(new int[] {x,y,age});
            }
        }

        allTrees = new PriorityQueue<>(temp);
    }

    private static void die() {
        int deadTreeNum = deadTree.size();
        for(int i = 0 ; i<deadTreeNum; i++){
            int[] currentTree = deadTree.get(i);
            int x = currentTree[0];
            int y = currentTree[1];
            int age = currentTree[2];
            ground[x][y] += age/2;
        }
    }
    
    private static void spread() {
        int treeForSpreadNum = treeForSpread.size();
        for(int i = 0 ; i<treeForSpreadNum; i++){
            int[] currentTree = treeForSpread.get(i);
            int x = currentTree[0];
            int y = currentTree[1];

            for(int j = 0 ; j<8; j++){ // 팔방으로 나무 퍼뜨리기
                int nextX = x + dx[j];
                int nextY = y + dy[j];

                if(nextX<0 ||nextY<0 || nextX>=N || nextY>=N) continue;
                allTrees.add(new int[] {nextX, nextY,1});
            }
        }
    }

    private static void addNutrient() {
        for(int i = 0 ; i<N ; i++){
            for(int j=0; j<N ; j++){
                ground[i][j] += nutrient[i][j];
            }
        }
    }
}
