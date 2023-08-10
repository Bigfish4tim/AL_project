import java.util.*;

public class AL_27089 {

    static int N, K;

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

    public static void angleCheck(int sight) {
        /*
        +x = 0
        -x = 1
        +y = 2
        -y = 3
         */

        if (sight == 0) {

        }
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
        K = scanner.nextInt();

        Operator[] map = new Operator[N];

        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int sight = scanner.nextInt();

            if (sight == 0)
                map[i] = new Operator(x, y, 90.0, 0.0);
            else if (sight == 1)
                map[i] = new Operator(x, y, 180.0, 90.0);
            else if (sight == 2)
                map[i] = new Operator(x, y, 270.0, 180.0);
            else if (sight == 3)
                map[i] = new Operator(x, y, 360.0, 270.0);
        }

        int[] counts = new int[N];

        int[] perm = new int[N];

        for (int i=0; i<N; i++) {

            for (int j = 0; j<N; j++) {
                if (i==j) continue;

                double deg = degrees(map[i], map[j]);
            }

            if (map[i].sight == 0) {
                for (int j=0; j<N; j++) {

                }
            }
        }
    }
}
            /*
            +x = 0
            -x = 1
            +y = 2
            -y = 3
             */
