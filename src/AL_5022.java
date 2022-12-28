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

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,0});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if(temp[0] == b2[0] && temp[1] == b2[1]) {
                if(min > temp[2])
                    min = temp[2];
            }
            for (int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;
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

        map = new int[N][M];
        visit = new boolean[N][M];

        int aXLength = Math.abs(a1[0] - a2[0]);
        int aYLength = Math.abs(a1[1] - a2[1]);

        int aLength = aXLength + aYLength;

        aLine = new int[aLength+1][2];

        for(int i=0; i<=aXLength; i++) {
            visit[Math.max(a1[0], a2[0]) - i][a1[1]] = true;
        }
        for(int i=0; i<aYLength; i++) {
            visit[a2[0]][Math.max(a1[1], a2[1]) - i] = true;
        }
        bfs(b1[0], b1[1]);

        if(min == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(min+aLength);
    }
}
