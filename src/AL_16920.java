import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_16920 {
    static int N, M;
    static char[][] map;
    static int[] S;
    static int P;
    static int[] PS;
    static boolean[][] visit;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static PriorityQueue<obj> priostartPoint = new PriorityQueue<>();
    static Queue<obj> startPoint = new LinkedList<>();

    public static class obj implements Comparable<obj> {
        int x;
        int y;
        int count;
        char k;

        public obj(int x, int y, int count, char k) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.k = k;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getCount() {
            return this.count;
        }

        public char getK() {
            return this.k;
        }

        @Override
        public int compareTo(obj o) {
            if(this.k > o.getK())
                return 1;
            else if(this.k < o.getK())
                return -1;
            return 0;
        }
    }

    public static void bfs(int x, int y, int count, char k) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y, 0});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == '#')
                    continue;
                if(map[nextX][nextY] == k) {
                    visit[nextX][nextY] = true;
                    if(temp[2]+1 < count) { q.add(new int[]{nextX, nextY, temp[2] + 1}); }
                    else if(temp[2]+1 == count) { startPoint.add(new obj(nextX, nextY, count, k)); }
                } else if(map[nextX][nextY] == '.') {
                    map[nextX][nextY] = k;
                    PS[Character.getNumericValue(k)-1]++;
                    visit[nextX][nextY] = true;
                    if(temp[2]+1 < count) { q.add(new int[]{nextX, nextY, temp[2] + 1}); }
                    else if(temp[2]+1 == count) { startPoint.add(new obj(nextX, nextY, count, k)); }
                }


            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        S = new int[P];
        visit = new boolean[N][M];
        PS = new int[P];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            PS[i] = 0;
        }

        char[] line;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            line = st.nextToken().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = line[j];
                if(map[i][j] == '#') {
                    visit[i][j] = true;
                } else if(map[i][j] != '.') {
                    int temp = Character.getNumericValue(map[i][j]) - 1;

                    if(temp >= 0) {
                        priostartPoint.add(new obj(i, j, S[temp], map[i][j]));
                        PS[temp]++;
                    }
                }
            }
        }

        while (!priostartPoint.isEmpty())
            startPoint.add(priostartPoint.poll());

        while (!startPoint.isEmpty()) {
            obj temp = startPoint.poll();
            bfs(temp.x, temp.y, temp.count, temp.k);
            visit = new boolean[N][M];
        }

        for(int i=0; i<P; i++) {
            System.out.print(PS[i] + " ");
        }
    }
}
