import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_17406 {
    static int[][] map;
    static int N, M, K;
    static int[][] RMap;

    public static int[][] rotation(int r, int c, int s) {
        int[][] tempMap = map.clone();

        for(int i=1; i<=s; i++) {
            int size = i*2;
            for(int j=0; j<size; j++) {
                tempMap[r-i][c-i+j+1] = map[r-i][c-i+j];
                tempMap[r-i+j+1][c+i] = map[r-i+j][c+i];
                tempMap[r+i][c+i-j-1] = map[r+i][c+i-j];
                tempMap[r+i-j-1][c-i] = map[r+i-j][c-i];
            }
        }

//        int min = Integer.MAX_VALUE;
//
//        for(int i=0; i<N; i++) {
//            for(int j=0; j<M; j++) {
//                if(min > tempMap[i][j]) {
//                    min = tempMap[i][j];
//                }
//            }
//        }

        return tempMap;
    }

    public static int findMin(int[][] staticMap) {
        int min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(min > staticMap[i][j]) {
                    min = staticMap[i][j];
                }
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        RMap = new int[K][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                RMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rotMap = new int[N][M];

        for(int i=0; i<RMap.length; i++) {
            rotMap = rotation(RMap[i][0], RMap[i][1], RMap[i][2]);
            rotMap = rotation(RMap[i][0], RMap[i][1], RMap[i][2]);

        }
    }
}
