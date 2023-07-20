import java.util.*;

public class AL_1845_4 {
    static int N;
    static int[] map;

    static point head;
    static point tail;

    static Stack<range> q = new Stack<>();

    public static class point {
        int head;
        int tail;
        int index;

        public point(int head, int tail, int index) {
            this.head = head;
            this.tail = tail;
            this.index = index;
        }
    }

    public static class range {
        int to;
        int from;

        public range(int to, int from) {
            this.to = to;
            this.from = from;
        }
    }

    public static int continuityFInd() {
        int[] dp = new int[map.length];

        for (int i=1; i< map.length; i++) {
            if (map[i]-1 == map[i-1]) {
                dp[i] = dp[i-1]+1;
            }
        }

        int MAX = 0;
        for (int length : dp) {
            MAX = Math.max(MAX, length);
        }

        return MAX+1;
    }

    public static void evenFind(int point) {
        int start = map[point-1];
        int end = map[point+1];

        for (int i=1; i< map.length; i++) {

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

        q.add(new range(a,b));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        map = new int[N+2];

        for (int i=0; i<N; i++)
            map[i+1] = scanner.nextInt();
        map[0] = 0;
        map[N+1] = N+1;

    }
}
