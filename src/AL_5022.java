import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_5022 {
    static int N, M;
    static int[] a1, a2, b1, b2;
    static int[][] map;
    static boolean[][] visit;
    static int[][] aLine;
    static int min = Integer.MAX_VALUE;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    public static class Point {
        int x;
        int y;
        int count;
        Point pre;
        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.pre = null;
        }
        public Point(int x, int y, int count, Point pre) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.pre = pre;
        }
    }

    public static void bfsInit(int x, int y, int fx, int fy) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y,0));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Point temp = q.poll();

            for (int i=0; i<4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N)
                    continue;
                if(nextX == fx && nextY == fy) {
                    if(min > temp.count+1) {
                        min = temp.count+1;
                    }
                    visit = new boolean[M][N];
                    visit[nextX][nextY] = true;
                    while (true) {
                        visit[temp.x][temp.y] = true;
                        if(temp.pre == null)
                            break;
                        temp = temp.pre;
                    }
                    return;
                }
                if(visit[nextX][nextY])
                    continue;

                q.add(new Point(nextX, nextY, temp.count+1, temp));
                visit[nextX][nextY] = true;
            }
        }
    }

    public static void bfs(int x, int y, int fx, int fy) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,0});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
//            if(temp[0] == b2[0] && temp[1] == b2[1]) {
//                if(min > temp[2])
//                    min = temp[2];
//            }
            for (int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N)
                    continue;
                if(nextX == fx && nextY == fy) {
                    if(min > temp[2]+1)
                        min = temp[2]+1;
                    return;
                }
                if(visit[nextX][nextY])
                    continue;
                q.add(new int[] {nextX, nextY, temp[2]+1});
                visit[nextX][nextY] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken())+1;

        a1 = new int[2];
        a2 = new int[2];
        b1 = new int[2];
        b2 = new int[2];

        st = new StringTokenizer(br.readLine());
        a1[1] = Integer.parseInt(st.nextToken());
        a1[0] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a2[1] = Integer.parseInt(st.nextToken());
        a2[0] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        b1[1] = Integer.parseInt(st.nextToken());
        b1[0] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        b2[1] = Integer.parseInt(st.nextToken());
        b2[0] = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];

        visit[a1[0]][a1[1]] = true;
        visit[a2[0]][a2[1]] = true;
        visit[b1[0]][b1[1]] = true;
        visit[b2[0]][b2[1]] = true;

        int aYLength;
        int aXLength;

//        for(int i=0; i<=aXLength; i++) {
//            visit[Math.max(a1[0], a2[0]) - i][a1[1]] = true;
//        }
//        for(int i=0; i<aYLength; i++) {
//            visit[a2[0]][Math.max(a1[1], a2[1]) - i] = true;
//        }
        bfsInit(a1[0], a1[1], a2[0], a2[1]);
        aXLength = min;
        min = Integer.MAX_VALUE;

        bfs(b1[0], b1[1], b2[0], b2[1]);
        aYLength = min;

        if(min == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(aXLength+aYLength);
    }
}
