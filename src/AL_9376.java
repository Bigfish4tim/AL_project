import java.io.*;
import java.util.*;

public class AL_9376 {
    static int T, h, w;
    static char[][] map;
    static boolean[][] visit;
    static int keyCount = 2;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};
    static int ans;

    public static class Pointer {
        int x;
        int y;
        int count;

        public Pointer(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void bfs(int x, int y) {
        ArrayList<Pointer> q = new ArrayList<>();

        q.add(new Pointer(x,y,0));
        visit[x][y] = true;
        int keyFound = 0;

        while (!q.isEmpty()) {
            Pointer temp = q.remove(0);

            if(keyFound == keyCount) {
                if(ans > temp.count)
                    ans = temp.count;
                continue;
            }

            for(int i=0; i<4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];

                if(nextX < 0 || nextX >= h+2 || nextY < 0 || nextY >= w+2)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == '*')
                    continue;
                if(map[nextX][nextY] == '#') {
                    q.add(new Pointer(nextX, nextY, temp.count+1));
                    visit[nextX][nextY] = true;
                    continue;
                }
                if(map[nextX][nextY] == '$') {
                    q.add(0, new Pointer(nextX, nextY, temp.count));
                    visit[nextX][nextY] = true;
                    keyFound++;
                    continue;
                }

                q.add(0, new Pointer(nextX, nextY, temp.count));
                visit[nextX][nextY] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h+2][w+2];
            visit = new boolean[h+2][w+2];
            ans = Integer.MAX_VALUE;

            for(int j=0; j<h+2; j++) {
                map[j][0] = '.';
                map[j][w+1] = '.';
            }

            for(int j=0; j<w+2; j++) {
                map[0][j] = '.';
                map[h+1][j] = '.';
            }

            char[] line;
            int[] start = new int[2];
            for(int j=1; j<h+1; j++) {
                st = new StringTokenizer(br.readLine());
                line = st.nextToken().toCharArray();
                for(int k=1; k<w+1; k++) {
                    map[j][k] = line[k-1];
                }
            }

            bfs(0,0);
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }
}
