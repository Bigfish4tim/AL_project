import java.util.*;

public class AL_1845_3 {
    static int N;
    static int[] map;

    static point head;
    static point tail;

    public static class point {
        int num;
        int index;

        public point(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static void reverse(int a, int b) {
        int range = (b-a+1);
        int size = (int) Math.ceil((double) range / 2);

        for(int i=0; i<size; i++) {
            int temp;
            temp = map[a+i];
            map[a+i] = -map[b-i];
            map[b-i] = -temp;
        }
    }

    public static boolean isc(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        map = new int[N+2];

        for (int i=0; i<N; i++)
            map[i+1] = scanner.nextInt();
        map[0] = 0;
        map[N+1] = N+1;

        while (head.num != N+1) {
            head = new point(0,0);
            tail = new point(0,0);
            for (int i=1; i<N+1; i++) {
                if (Math.abs(head.num) < Math.abs(map[i])) {
                    head = new point(map[i], i);
                    continue;
                }
                if (Math.abs(tail.num - map[i]) != 1) {

                }
            }
        }
    }
}
