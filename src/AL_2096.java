import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2096 {
    static int N;
    static int[][] map;
    static int max = 0;
    static int min = Integer.MAX_VALUE;
    static int[] pos;

    public static void SlindingWindow() {
        int sum = 0;

        for(int i=0; i<N; i++) {
            sum += map[i][0];
        }

        for(int i=N-1; i>=0; i--) {
            for(int j=0; j<3; j++) {

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][3];
        pos = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i] = 0;
            for(int j=0; j<3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
