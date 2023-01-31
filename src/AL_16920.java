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

    public static void bfs() {

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

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = st.nextToken().charAt(0);
                try {
                    int temp = Character.getNumericValue(map[i][j]) - 1;
                    PS[temp][0] = i;
                    PS[temp][1] = j;
                } catch (NumberFormatException e) {
                    int temp = -1;
                }
            }
        }
    }
}
