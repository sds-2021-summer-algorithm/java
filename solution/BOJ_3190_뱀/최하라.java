import java.io.*;
import java.util.*;

public class Main {
    static class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Direction {
        int sec; String direction;
        public Direction(int sec, String direction) {
            this.sec = sec;
            this.direction = direction;
        }
    }

    static int N, K, L, seconds, turnPoint;
    static int[][] board; //사과 1; 뱀 2;
    static Queue<Direction> dirStack;
    static Deque<Coord> snake;
    static String currentDir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //1. 입력 받기
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        //2. 사과 위치 저장
        board = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        //3. 뱀 이동방향 저장
        dirStack = new ArrayDeque<>();
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            dirStack.add(new Direction(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        //4. 뱀 이동하기
        snake = new ArrayDeque<>();
        board[1][1] = 2;
        snake.add(new Coord(1,1)); //뱀 시작점 저장
        currentDir = "right";
        seconds = 0;
        move();

        System.out.println(seconds);
    }

    static void move() {
        while (true) {
            seconds++;
            if (dirStack.isEmpty()) turnPoint = 101;
            else turnPoint = dirStack.peek().sec;

            //방향 확인
            if (turnPoint + 1 == seconds) { //방향조정
                Direction dir = dirStack.poll();
                if (dir.direction.equals("D")) turnRight();
                else turnLeft();
            } else
                straight();


            //벽 체크
            if (snake.peekFirst().x == 0 || snake.peekFirst().y == 0 || snake.peekFirst().x > N || snake.peekFirst().y > N)
                return;

            //몸 체크
            if (board[snake.peekFirst().x][snake.peekFirst().y] == 2)
                return;


            //사과체크
            if (board[snake.peekFirst().x][snake.peekFirst().y] != 1) { //사과 x; 꼬리 움직이기
                Coord tail = snake.pollLast();
                board[tail.x][tail.y] = 0;
            }
            board[snake.peekFirst().x][snake.peekFirst().y] = 2;
        }
    }

    static void turnRight() {
        Coord current = snake.peekFirst();
        switch (currentDir) {
            case "right" :
                currentDir = "down";
                snake.addFirst(new Coord(current.x + 1, current.y));
                break;
            case "down" :
                snake.addFirst(new Coord(current.x, current.y - 1));
                currentDir = "left";
                break;
            case "left" :
                currentDir = "up";
                snake.addFirst(new Coord(current.x - 1, current.y));
                break;
            case "up" :
                currentDir = "right";
                snake.addFirst(new Coord(current.x, current.y + 1));
                break;
        }
    }

    static void turnLeft() {
        Coord current = snake.peekFirst();
        switch (currentDir) {
            case "right" :
                currentDir = "up";
                snake.addFirst(new Coord(current.x - 1, current.y));
                break;
            case "down" :
                currentDir = "right";
                snake.addFirst(new Coord(current.x, current.y + 1));
                break;
            case "left" :
                currentDir = "down";
                snake.addFirst(new Coord(current.x + 1, current.y));
                break;
            case "up" :
                snake.addFirst(new Coord(current.x, current.y - 1));
                currentDir = "left";
                break;
        }
    }

    static void straight() {
        Coord current = snake.peekFirst();
        switch (currentDir) {
            case "right" :
                snake.addFirst(new Coord(current.x, current.y + 1));
                break;
            case "down" :
                snake.addFirst(new Coord(current.x + 1, current.y));
                break;
            case "left" :
                snake.addFirst(new Coord(current.x, current.y - 1));
                break;
            case "up" :
                snake.addFirst(new Coord(current.x - 1, current.y));
                break;
        }
    }
}