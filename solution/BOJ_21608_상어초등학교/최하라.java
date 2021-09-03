import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static int[][] map, spaceMap;
    static ArrayList<Integer>[] stuList;
    static Coord[] stuPlace;
    static int N;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    
    static class Coord {
        int x, y;
        public Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //1. 입력 받기 & define var
        N = Integer.valueOf(br.readLine());
        int totalStud = N * N;
        int answer = 0;
        map = new int[N + 1][N + 1];
        stuPlace = new Coord[totalStud + 1];
        stuList = new ArrayList[totalStud + 1];

        //2. 빈칸 수 맵 만들기
        buildSpaceMap();

        //3. 총 학생 배치하기
        for (int i = 1; i <= totalStud; i++) {
            //좋아하는 학생 list로 저장
            int currStud = storeLikeStud();

            //학생 자리에 배치
            findPlace(currStud);
        }

        //4. 학생 만족도 총 합 구하기
        for(int i = 1; i <= totalStud; i++) {
            Coord student = stuPlace[i];
            int count = 0;
            for(int likeFri : stuList[i]) { //인접한 좋아하는 학생 수 구하기
                if(Math.abs(stuPlace[likeFri].x - student.x) + Math.abs(stuPlace[likeFri].y - student.y) == 1)
                    count++;
            }

            //학생의 만족도 총합에 더하기
            answer += satisfyScore(count);
        }

        //5. 총 만족도 출력
        System.out.println(answer);
    }

    static int storeLikeStud() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int student = Integer.parseInt(st.nextToken());
        stuList[student] = new ArrayList<>();

        for (int j = 0; j < 4; j++)  //좋아하는 학생 번호 저장
            stuList[student].add(Integer.parseInt(st.nextToken()));

        return student;
    }

    static void findPlace(int num) {
        int[][] countDup = new int[N + 1][N + 1];
        for(int friend : stuList[num]) {
            if(stuPlace[friend] != null) { //좋아하는 친구 맵위 위치한다면
                Coord student = stuPlace[friend];

                for(int i = 0; i < 4; i++) { //인접한 위치 1 증가
                    int mx = student.x + dx[i];
                    int my = student.y + dy[i];
                    if(mx >= 0 && my >= 0 && mx <= N && my <= N && map[mx][my] == 0)
                        countDup[mx][my]++;
                }
            }
        }

        Coord tmp = new Coord(0,0); //학새을 배치할 자리
        int spaceMax = -1;
        int maxDup = -1;

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j] != 0) continue; //이미 학생이 배치됨

                if(maxDup < countDup[i][j]) {//인접한 좋아하는 학생 많음
                    tmp = new Coord(i, j);
                    maxDup = countDup[i][j];
                    spaceMax = spaceMap[i][j];
                }
                else if(maxDup == countDup[i][j] && spaceMax < spaceMap[i][j]) { //max 좋아하는 학생이 같을 때 빈공간이 더 많은 곳 저장
                    spaceMax = spaceMap[i][j];
                    tmp = new Coord(i, j);
                }
            }
        }

        map[tmp.x][tmp.y] = num; //map update
        stuPlace[num] = tmp; //학생 위치 저장

        //배치된 학생 위치 주변 공간 업데이트
        updateSpace(stuPlace[num]);
    }

    static int satisfyScore(int rate) {
        switch (rate) {
            case 1 : return 1;
            case 2 : return 10;
            case 3 : return 100;
            case 4 : return 1000;
        }
        return 0;
    }

    static void updateSpace(Coord coord) {
        spaceMap[coord.x][coord.y] = 0;
        for(int i = 0; i < 4; i++) {
            int mx = coord.x + dx[i];
            int my = coord.y + dy[i];
            if(mx >= 0 && my >= 0 && mx <= N && my <= N && map[mx][my] == 0) {
                spaceMap[mx][my]--;
            }
        }
    }

    static void buildSpaceMap() {
        spaceMap = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int cnt = 4;
                if(i == 1 || i == N) cnt--;
                if(j == 1 || j == N) cnt--;
                spaceMap[i][j] = cnt;
            }
        }
    }

    static void print (int[][] arr) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}