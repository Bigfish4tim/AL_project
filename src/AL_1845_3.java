import java.util.*;

public class AL_1845_3 {
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
        head = new point(0,0,0);
        tail = new point(100000,100000,0);

        while (tail.tail != N+1) {
            head = new point(map[1], map[1], 1);
            tail = new point(map[2], map[2], 2);
            for (int i=2; i<N+1; i++) {
                if (head.head > 0) {
                    if (head.tail+1 == map[i]) {
                        head = new point(head.head, map[i], head.index);
                        tail = new point(map[i+1], map[i+1], i+1);
                        continue;
                    }
                    else if (head.tail < Math.abs(map[i])) {
                        head = new point(map[i], map[i], i);
                        tail = new point(map[i+1], map[i+1], i+1);
                        continue;
                    } else {
                        while (Math.abs(map[i] - map[i + 1]) == 1) {
                            tail = new point(tail.head, map[i + 1], i + 1);
                            i++;
                        }
                        reverse(head.index, tail.index);
                        break;
                    }
                } else {
                    if (head.tail - map[i] == -1) {
                        head = new point(head.head, map[i], head.index);
                        tail = new point(map[i+1], map[i+1], i+1);
                        continue;
                    }
                    else if (Math.abs(head.head) < Math.abs(map[i])) {
                        head = new point(map[i], map[i], i);
                        tail = new point(map[i+1], map[i+1], i+1);
                        continue;
                    } else {
                        while (map[i] - map[i+1] == -1) {
                            tail = new point(tail.head, map[i+1], i+1);
                            i++;
                        }
                        reverse(head.index, tail.index);
                        break;
                    }
                }
            }
        }

        for (int i=1; i<N+1; i++) {
            if (map[i] < 0) {
                int from = i, to = i;
                while (map[i+1] - map[i] == 1) {
                    i++;
                    to++;
                }
                reverse(from, to);
            }
        }

        int size = q.size();
        System.out.println(size);
        for (int i=0; i<size; i++) {
            range temp = q.pop();

            assert temp != null;
            System.out.println(temp.to + " " + temp.from);
        }
    }
}


/*
6
-1 -2 -3 -4 -5 -6

6
-6 -5 -4 -3 -2 -1

8
-4 -3 -5 1 2 7 6 8

6
-4 -3 -5 1 2 6
 */