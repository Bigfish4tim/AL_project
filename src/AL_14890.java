import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_14890 {
    static int N, L;
    static int[][] map;
    static int ans = 0;

    public static void road(int[] map) {
        for(int i=0; i<map.length; i++) {
            if(i+L < map.length) {
                if(map[i] == map[i+1])
                    continue;
                for(int j=1; j<L; j++) {
                    if(Math.abs(map[i] - map[i+j]) >= 2) {
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
