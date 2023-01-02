import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_9944 {
    static int N,M;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[] line;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            line = st.nextToken().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = line[j];
            }
        }


    }
}
