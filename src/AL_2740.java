import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2740 {
    static int N, M, K;
    static int[][] map1, map2;
    static int[][] result;

    public static int[][] calc(int[][] map1, int[][] map2) {
        for(int i=0; i<map1.length; i++) {
            for(int j=0; j<map2[0].length; j++) {
                for(int k=0; k<map1[0].length; k++) {
                    result[i][j] += map1[i][k] * map2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map1 = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map2 = new int[M][K];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<K; j++) {
                map2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[N][K];

        calc(map1, map2);

        for(int i=0; i<N; i++) {
            for(int j=0; j<K; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
