import java.util.*;

public class AL_27089 {

    static int N;
    static long K;
    static Operator[] map;

    public static class Operator {
        int x;
        int y;
        double upSight;
        double downSight;

        public Operator(int x, int y, double upSight, double downSight) {
            this.x = x;
            this.y = y;
            this.upSight = upSight;
            this.downSight = downSight;
        }
    }

    public static class Point {
        int from;
        int to;

        public Point(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static double calculateDistance(Operator a1, Operator a2) {
        double deltaX = a2.x - a1.x;
        double deltaY = a2.y - a1.y;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static int taxiDistance(Operator a1, Operator a2) {
        int deltaX = a2.x - a1.x;
        int deltaY = a2.y - a1.y;

        return Math.abs(deltaX) + Math.abs(deltaY);
    }

    public static double degrees(Operator a1, Operator a2) {
        // 각도를 계산합니다.
        double angleRadians = Math.atan2(a2.y -a1.y, a2.x - a1.x);
        double angleDegrees = Math.toDegrees(angleRadians - Math.PI/4);

        if (angleDegrees < 0) {
            angleDegrees = 360 + angleDegrees;
        }

        return angleDegrees;
    }

    public static int NextTarget(int index) {
        double dist = Double.MAX_VALUE;
        int output = -1;
        for (int i = 0; i<N; i++) {
            if (index == i) continue;

            double deg = degrees(map[index], map[i]);

            if (deg <= map[index].upSight && deg >= map[index].downSight) {
                double tempDist = calculateDistance(map[index], map[i]);
                if (dist > tempDist) {
                    dist = tempDist;
                    output = i;
                }
            } else if (map[index].upSight == 360.0 && deg == 0.0) {
                double tempDist = calculateDistance(map[index], map[i]);
                if (dist > tempDist) {
                    dist = tempDist;
                    output = i;
                }
            }
        }
        if (output == -1) {
            return (index+1) % N;
        }
        return output;
    }

    public static int AtkPoint(int index) {
        int output = 0;
        for (int i=0; i<N; i++) {
            if (index==i) continue;

            double deg = degrees(map[i], map[index]);

            if (deg <= map[i].upSight && deg >= map[i].downSight)
                output += taxiDistance(map[i], map[index]);
        }

        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        K = scanner.nextLong();

        map = new Operator[N];

        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int sight = scanner.nextInt();

            if (sight == 0)
                map[i] = new Operator(x, y, 360.0, 270.0);
            else if (sight == 1)
                map[i] = new Operator(x, y, 180.0, 90.0);
            else if (sight == 2)
                map[i] = new Operator(x, y, 90.0, 0.0);
            else if (sight == 3)
                map[i] = new Operator(x, y, 270.0, 180.0);
        }

        long[] perm = new long[N];

        int[] shootRange = new int[N];

        boolean[] visit = new boolean[N];

        int current = 0;
        int length = 0;
        int temp = 0;

        ArrayList<Point> cycle = new ArrayList<>();

        while (!visit[current]) {
            visit[current] = true;
            temp = NextTarget(current);
            cycle.add(new Point(current, temp));
            shootRange[current] = AtkPoint(current);
            length++;
            current = temp;
        }

        ArrayList<Point> tempArr = new ArrayList<>();

        for (int i=0; i<cycle.size(); i++) {
            Point t = cycle.get(i);
            if (t.from == current) {
                tempArr.add(new Point(t.from, t.to));
                current = t.to;
            }
        }

        int remLength = length - tempArr.size();

        int stp = 0;
        for (int i=0; i<remLength; i++) {
            perm[stp] += shootRange[stp];
            stp = cycle.get(i).to;
        }

        K -= remLength;

        long share = (K / tempArr.size());
        long remainder = K % tempArr.size();

        if (tempArr.size() != 0) {
            for (int i=0; i<tempArr.size(); i++) {
                perm[tempArr.get(i).from] += share * shootRange[tempArr.get(i).from];
            }

            for (int i=0; i<remainder; i++) {
                perm[tempArr.get(i).from] += shootRange[tempArr.get(i).from];
            }
        }

        for (int i=0; i<N; i++) {
            System.out.println(perm[i]%998244353);
        }
    }
}
