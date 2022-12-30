import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2638 {
    static int N, M;
    static int[][] map;
    static boolean[][] isOut;

    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    public static void outCheck(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        isOut[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;
                if(isOut[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == 1)
                    continue;

                q.add(new int[] {nextX, nextY});
                isOut[nextX][nextY] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isOut = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        boolean trig = true;

        while (trig) {
            outCheck(0,0);

            trig = false;

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    int count = 0;
                    if(map[i][j] == 0)
                        continue;
                    else {
                        for(int k=0; k<4; k++) {
                            if(isOut[i+dx[k]][j+dy[k]])
                                count++;
                        }
                        trig = true;
                    }
                    if(count>=2)
                        map[i][j] = 0;
                }
            }
            isOut = new boolean[N][M];
            ans++;
        }

        System.out.println(ans-1);
    }
}
