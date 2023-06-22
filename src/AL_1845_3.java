import java.util.*;

public class AL_1845_3 {
    static int N;
    static int[] map;

    static point head;
    static point tail;

    static Stack<range> q = new Stack<>();

    public static class point {
        int num;
        int index;

        public point(int num, int index) {
            this.num = num;
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
        head = new point(0,0);
        tail = new point(100000,0);

        while (tail.num != N+1) {
            head = new point(0,0);
            tail = new point(100000,0);
            for (int i=1; i<N+1; i++) {
                if (head.num > 0) {
                    if (head.num+1 == map[i]) continue;
                    else if (head.num < map[i]) {
                        head = new point(map[i], i);
                        tail = new point(map[i+1], i+1);
                        continue;
                    } else {

                    }
                } else {

                }
                if (Math.abs(head.num) < Math.abs(map[i])) {
                    head = new point(map[i], i);
                    tail = new point(map[i+1], i+1);
                    continue;
                }
                if (tail.num == map[i]) continue;
                if (map[i] >= 0) {
                    while (Math.abs(tail.num - map[i]) == 1) {
                        tail = new point(map[i], i);
                        i++;
                    }
                    reverse(head.index, tail.index);
                    break;
                } else {
                    while (tail.num - map[i] == -1) {
                        tail = new point(map[i], i);
                        i++;
                    }
                    reverse(head.index, tail.index);
                    break;
                }
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
 */