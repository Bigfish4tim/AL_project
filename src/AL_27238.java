import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_27238 {
    static int N, L, M;
    static int[] LMap;
    static int[][] MMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        LMap = new int[L];
        MMap = new int[M][2];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<L; i++) {
            LMap[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            MMap[i][0] = Integer.parseInt(st.nextToken());
            MMap[i][1] = Integer.parseInt(st.nextToken());
        }


    }
}
