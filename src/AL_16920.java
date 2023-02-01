import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_16920 {
    static int N, M;
    static char[][] map;
    static int[] S;
    static int P;
    static boolean[][] visit;
    static int[][] PS;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int dotCount = 0;

    static Queue<obj> startPoint = new LinkedList<>();

    public static class obj {
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
                if(map[nextX][nextY] != '.')
                    continue;

                map[nextX][nextY] = k;
                visit[nextX][nextY] = true;
                dotCount--;
                if(temp[2]+1 < count) { q.add(new int[]{nextX, nextY, temp[2] + 1}); }
                else if(temp[2]+1 == count) { startPoint.add(new obj(nextX, nextY, count, k)); }
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
        PS = new int[P][2];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<P; i++)
            S[i] = Integer.parseInt(st.nextToken());

        char[] line;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            line = st.nextToken().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = line[j];

                int temp = Character.getNumericValue(map[i][j]) - 1;

                if(temp >= 0) {
                    startPoint.add(new obj(i, j, S[temp], map[i][j]));
                }
                if(map[i][j] == '.')
                    dotCount++;
            }
        }

        while (!startPoint.isEmpty()) {
            obj temp = startPoint.poll();
            bfs(temp.x, temp.y, temp.count, temp.k);
        }
//        while (dotCount != 0) {
//            for(int i=0; i<P; i++) {
//                bfs(PS[i][0], PS[i][1], S[i], map[PS[i][0]][PS[i][1]]);
//            }
//        }
    }
}
