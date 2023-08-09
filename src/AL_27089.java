import java.util.*;

public class AL_27089 {

    static int N, K;

    public static class Operator {
        int x;
        int y;
        int sight;

        public Operator(int x, int y, int sight) {
            this.x = x;
            this.y = y;
            this.sight = sight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        K = scanner.nextInt();

        Operator[] map = new Operator[N];

        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int sight = scanner.nextInt();

            map[i] = new Operator(x, y, sight);
        }

        int[] counts = new int[N];

        int[] perm = new int[N];

        for (int i=0; i<N; i++) {
            map[i].sight
        }
    }
}
