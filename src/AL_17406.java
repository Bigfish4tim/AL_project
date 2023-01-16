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
        int[][] tempMap = new int[N][M];

        for(int i=0; i<N; i++) {
            tempMap[i] = rotMap[i].clone();
        }

        for(int i=1; i<=s; i++) {
            int size = i*2;
            for(int j=0; j<size; j++) {
                tempMap[r-1-i][c-i+j] = rotMap[r-1-i][c-1-i+j];
                tempMap[r-i+j][c-1+i] = rotMap[r-1-i+j][c-1+i];
                tempMap[r-1+i][c-2+i-j] = rotMap[r-1+i][c-1+i-j];
                tempMap[r-2+i-j][c-1-i] = rotMap[r-1+i-j][c-1-i];
            }
        }

        return tempMap;
    }

    public static int findMin(int[][] staticMap) {
        int min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            int temp = 0;
            for(int j=0; j<M; j++)
                temp = temp + staticMap[i][j];
            if(min > temp)
                min = temp;
        }

        return min;
    }

    public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r){
        if(depth == r){
            perm.add(out.clone());

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

        int[][] rotMap = new int[N][M];

        for(int i=0; i<N; i++)
            rotMap[i] = map[i].clone();

        permutation(KPoint, new int[K], new boolean[K], 0, K);

        while (!perm.isEmpty()) {
            int[] temp = perm.poll();
            for(int i=0; i<temp.length; i++) {
                rotMap = rotation(rotMap, RMap[temp[i]][0], RMap[temp[i]][1], RMap[temp[i]][2]);
            }

            int t = findMin(rotMap);

            if(ans > t)
                ans = t;

            for(int i=0; i<N; i++)
                rotMap[i] = map[i].clone();
        }

        System.out.println(ans);
    }
}
