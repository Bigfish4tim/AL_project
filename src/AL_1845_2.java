import java.util.*;

public class AL_1845_2 {
    static int[] map;
    static int N;

    public static int[] reverse(int[] pmap, int a, int b) {
        int range = (b-a+1);
        int size = (int) Math.ceil((double) range / 2);

        for(int i=0; i<size; i++) {
            int temp;
            temp = pmap[a+i];
            pmap[a+i] = -pmap[b-i];
            pmap[b-i] = -temp;
        }

        return pmap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        map = new int[N+2];

        for (int i=0; i<N; i++)
            map[i+1] = scanner.nextInt();
        map[0] = 0;
        map[N+1] = N+1;

    }
}
