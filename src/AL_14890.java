import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_14890 {
    static int N, L;
    static int[][] map;
    static boolean[] visit;
    static int ans = 0;

    public static void road(int[] map) {
        for(int i=0; i<map.length; i++) {
            boolean trig = false;
            if(i+1 < map.length && map[i] == map[i+1])
                continue;
            if(i+L < map.length) {
                int tempCount=0;
                for(int j=1; j<L+1; j++)
                    if(Math.abs(map[i] - map[i+j]) == 1)
                        tempCount++;
                if(tempCount == L) {
                    trig = true;
                    for (int j = 0; j < L; j++)
                        visit[i + j] = true;
                }
            }
            if(i-L >= 0) {
                int tempCount=0;
                for(int j=1; j<L+1; j++)
                    if(Math.abs(map[i] - map[i-j]) == 1)
                        tempCount++;
                if(tempCount == L) {
                    trig = true;
                    for (int j = 0; j < L; j++)
                        visit[i - j] = true;
                }
            }
        }
    }

    /*

        Wolf
        HEI

        R4
        Phil-Legend
        PEPSI
        Malcha

        L1
        Jun
        Delldell
        imjoy

        R3
        KC
        MeowM
        Mochaxxx

        R2
        bam
        Hohnflare577
        RAING

        R1
        0711
        Perplextyyy
        archen

        L4
        Chromeb
        itsjoeee
        DaN




     */




    public static void bfs() {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
