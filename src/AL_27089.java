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

    public static class Cycle {
        ArrayList<Integer> elements;

        public Cycle() {
            elements = new ArrayList<>();
        }

        public void addElement(int element) {
            elements.add(element);
        }

        public void printCycle() {
            for (int element : elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        public int getSize() {
            return elements.size();
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

        int[] counts = new int[N];

        int[] perm = new int[N];

        int[] shootRange = new int[N];


        int start, end;

        start = 0;
        end = -1;

        boolean[] visit = new boolean[N];

        ArrayList<Integer> shoots = new ArrayList<>();


        for (int i=1; i<N+1; i++) {
            if (!visit[i]) {
                AL_2834.Cycle cycle = new AL_2834.Cycle();
                int current = i;

                while (!visit[current]) {
                    visit[current] = true;
                    cycle.addElement(map[current]);
                    current = map[current];
                }

                if (cycle.getSize() > 1) {
                    output.add(cycle);
                }
            }
        }

        Cycle cycle = new Cycle();
        int current = 0;

        while (!visit[current]) {
            visit[current] = true;
        }

        while (start == end) {
            for (int i=0; i<N; i++) {
                if (i==j) continue;

                double deg = degrees(map[j], map[i]);

                if (deg <= map[j].upSight && deg >= map[j].downSight)
                    shootRange[i] += taxiDistance(map[j], map[i]);
            }
        }

        for (int i=0; i<N; i++) {

        }



        /*

         */



        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (i==j) continue;

                double deg = degrees(map[j], map[i]);

                if (deg <= map[j].upSight && deg >= map[j].downSight)
                    shootRange[i] += taxiDistance(map[j], map[i]);
            }

            double dist = Double.MAX_VALUE;
            for (int j = 0; j<N; j++) {

                if (i == j) continue;

                double deg = degrees(map[i], map[j]);

                if (deg <= map[i].upSight && deg >= map[i].downSight) {
                    double tempDist = calculateDistance(map[i], map[j]);
                    if (dist > tempDist) {
                        dist = tempDist;
                        perm[i] = j;
                    }
                } else if (map[i].upSight == 360.0 && deg == 0.0) {
                    double tempDist = calculateDistance(map[i], map[j]);
                    if (dist > tempDist) {
                        dist = tempDist;
                        perm[i] = j;
                    }
                }
            }
        }

        int point = 0;

        for (int i=0; i<K; i++) {
            counts[point] += shootRange[point];
            point = perm[point];
        }

        for (int i=0; i<N; i++) {
            System.out.println(counts[i]%998244353);
        }
    }

    public static int AtkPoint(int index) {
        int output = 0;
        for (int i=0; i<N; i++) {
            if (index==i) continue;

            double deg = degrees(map[i], map[index]);

            if (deg <= map[i].upSight && deg >= map[i].downSight)
                shootRange[index] += taxiDistance(map[i], map[index]);
        }

        return output;
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
}

