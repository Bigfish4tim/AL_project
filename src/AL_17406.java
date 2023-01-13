import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_17406 {
    static int[][] map;
    static int N, M, K;
    static int[][] RMap;
    static int ans = Integer.MAX_VALUE;
    static Queue<int[]> perm = new LinkedList<>();

    public static int[][] rotation(int[][] rotMap, int r, int c, int s) {
        int[][] tempMap = rotMap.clone();

        for(int i=1; i<=s; i++) {
            int size = i*2;
            for(int j=0; j<size; j++) {
//                tempMap[r-i][c-i+j+1] = map[r-i][c-i+j];
//                tempMap[r-i+j+1][c+i] = map[r-i+j][c+i];
//                tempMap[r+i][c+i-j-1] = map[r+i][c+i-j];
//                tempMap[r+i-j-1][c-i] = map[r+i-j][c-i];

                tempMap[r-i-1][c-i+j] = map[r-i-1][c-i+j-1];
                tempMap[r-i+j][c+i-1] = map[r-i+j-1][c+i-1];
                tempMap[r+i-1][c+i-j-2] = map[r+i-1][c+i-j-1];
                tempMap[r+i-j-2][c-i-1] = map[r+i-j-1][c-i-1];
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

    public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r){
        if(depth == r){
            perm.add(out.clone());

//            for(int num: out) System.out.print(num);
//            System.out.println();

            return;
        }
        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = arr[i];
                permutation(arr, out, visited, depth+1, r);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        RMap = new int[K][3];

        int[] KPoint = new int[K];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            KPoint[i] = i;
            for(int j=0; j<3; j++) {
                RMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rotMap = map.clone();

        permutation(KPoint, new int[K], new boolean[K], 0, K);

        while (!perm.isEmpty()) {
            int[] temp = perm.poll();
            for(int i=0; i<temp.length; i++) {
                rotMap = rotation(rotMap, RMap[temp[i]][0], RMap[temp[i]][1], RMap[temp[i]][2]);
            }

            int t = findMin(rotMap);

            if(ans > t)
                ans = t;

            rotMap = map.clone();
        }

        System.out.println(ans);
    }
}
