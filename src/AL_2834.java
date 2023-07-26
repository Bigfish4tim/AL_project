import java.util.*;

public class AL_2834 {
    static int N;
    static int[] map;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        map = new int[N];

        for (int i=0; i<N; i++)
            map[i] = scanner.nextInt();

        int[] oriMap = new int[N];
        for (int i=0; i<N; i++)
            oriMap[i] = i+1;


    }
}
