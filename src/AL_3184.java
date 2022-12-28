import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_3184 {
    static int X, Y;
    static char[][] map;
    static boolean[][] visit;
    static int O, V;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        int sheep = 0, wolves = 0;

        q.add(new int[] {x,y});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == '#')
                    continue;

                if(map[nextX][nextY] == 'v')
                    wolves++;
                else if(map[nextX][nextY] == 'o')
                    sheep++;
                q.add(new int[] {nextX, nextY});
                visit[nextX][nextY] = true;
            }
        }
        if(sheep > wolves)
            O = O+sheep;
        else
            V = V+wolves;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        map = new char[X][Y];
        visit = new boolean[X][Y];

        O = 0;
        V = 0;

        char[] line;
        for(int i=0; i<X; i++) {
            st = new StringTokenizer(br.readLine());
            line = st.nextToken().toCharArray();
            if (Y >= 0) System.arraycopy(line, 0, map[i], 0, Y);
        }

        for(int i=0; i<X; i++) {
            for(int j=0; j<Y; j++) {
                bfs(i,j);
            }
        }

        System.out.println(O + " " + V);
    }
}
