import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static boolean can = false;
    static char[][] Class;
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static ArrayList<Point> Ts = new ArrayList<>();
    static ArrayList<Point> Xs = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        Class = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Class[i][j] = st.nextToken().charAt(0);
                if (Class[i][j]=='X')
                    Xs.add(new Point(i, j));
                else if (Class[i][j]=='T')
                    Ts.add(new Point(i, j));
            }
        }
        fill(0, 3);
        sb.append(can? "YES" : "NO");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void fill(int s, int remain){
        if(can)
            return;
        if (remain==0){
            chk();
            return;
        }
        for (int i = s; i < Xs.size(); i++) {
            Point cur = Xs.get(i);
            Class[cur.r][cur.c] = 'O';
            fill(i+1, remain-1);
            Class[cur.r][cur.c] = 'X';
        }
    }
    static void chk(){
        int r, c;
        for (Point t : Ts){
            r = t.r-1;
            c = t.c;
            while(0<=r && r<N && 0<=c && c<N){
                if(Class[r][c]=='O' || Class[r][c]=='T') break;
                else if(Class[r][c]=='S') return;
                r--;
            }
            r = t.r;
            c = t.c+1;
            while(0<=r && r<N && 0<=c && c<N){
                if(Class[r][c]=='O' || Class[r][c]=='T') break;
                else if(Class[r][c]=='S') return;
                c++;
            }
            r = t.r+1;
            c = t.c;
            while(0<=r && r<N && 0<=c && c<N){
                if(Class[r][c]=='O' || Class[r][c]=='T') break;
                else if(Class[r][c]=='S') return;
                r++;
            }
            r = t.r;
            c = t.c-1;
            while(0<=r && r<N && 0<=c && c<N){
                if(Class[r][c]=='O' || Class[r][c]=='T') break;
                else if(Class[r][c]=='S') return;
                c--;
            }
        }
        can = true;
    }
}
