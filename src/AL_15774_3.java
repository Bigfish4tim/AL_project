import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15774_3 {
    static int n, k;
    static ArrayList<house> map = new ArrayList<>();

    static double[] divider;
    static long[] distSet;
    static long max;
    static int index;
    static int count;
    static double start;
    static double end;

    public static class house implements Comparable<house> {
        long x;
        long y;

        public long getX() { return x; }
        public long getY() { return y; }

        public house(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(house o) {
            if (this.x > o.getX()) return 1;
            else if (this.x < o.getX()) return -1;
            else return Long.compare(this.y, o.getY());
        }
    }

    static long ccw(house p1, house p2, house p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
    }

    public static long dist(house p1, house p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return (dx*dx + dy*dy);
    }

    public static Stack<house> convexHull(ArrayList<house> input) {
        house root = new house(Long.MAX_VALUE,Long.MAX_VALUE);

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).x < root.x) {
                root = input.get(i);
            } else if (input.get(i).x == root.x) {
                if (input.get(i).y < root.y) {
                    root = input.get(i);
                }
            }
        }

        house finalRoot = root;
        input.sort(new Comparator<house>() {
            @Override
            public int compare(house o1, house o2) {
                long ccw = ccw(finalRoot, o1, o2);

                if (ccw > 0) {
                    return -1;
                } else if (ccw < 0) {
                    return 1;
                } else {
                    double distance1 = dist(finalRoot, o1);
                    double distance2 = dist(finalRoot, o2);

                    return Double.compare(distance1, distance2);
                }
            }
        });

        Stack<house> stack = new Stack<>();
        stack.add(root);

        for (int i=1; i<input.size(); i++) {
            while (stack.size() > 1 && (ccw(stack.get(stack.size()-2), stack.get(stack.size()-1), input.get(i)) <= 0)) {
                stack.pop();
            }
            stack.add(input.get(i));
        }

        return stack;
    }

    static long rotatingCalipers(ArrayList<house> convexHull) {
        if (convexHull.size() <= 1) {
            return 0;
        }
        long max_dist = 0;

        int j = 1;
        for (int i = 0; i < convexHull.size(); i++) {
            int i_next = (i + 1) % convexHull.size();
            for (; ; ) {
                int j_next = (j + 1) % convexHull.size();

                long bx = convexHull.get(i_next).x - convexHull.get(i).x; // 왼쪽 벡터
                long by = convexHull.get(i_next).y - convexHull.get(i).y;
                long cx = convexHull.get(j_next).x - convexHull.get(j).x;   // 오른쪽 벡터
                long cy = convexHull.get(j_next).y - convexHull.get(j).y;

                long ccw = ccw(new house(0, 0), new house(bx, by), new house(cx, cy));
                if (ccw > 0) {  // 반시계 방향이면 오른쪽에 있는 점을 다음으로
                    j = j_next;
                } else {    // 시계 방향이면 왼족에 있는 점을 다음으로
                    break;
                }
            }

            // 최대 거리 구하기
            if (dist(convexHull.get(i), convexHull.get(j)) > max_dist) {
                max_dist = dist(convexHull.get(i), convexHull.get(j));
            }
        }

        return max_dist;
    }

    static long finalDist(double a, double b) {
        long returns = 0;

        if (a == start) a--;

        ArrayList<house> houses = new ArrayList<>();
        for (int i=0; i<map.size(); i++) {
            if (map.get(i).x > a && map.get(i).x <= b) houses.add(map.get(i));
        }
        ArrayList<house> list = new ArrayList<>(convexHull(houses));
        returns = rotatingCalipers(list);
        return returns;
    }

    static void findMax() {
        max = 0;
        for (int i=0; i<distSet.length; i++) {
            if (max < distSet[i]) {
                max = distSet[i];
                index = i;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempX = Integer.parseInt(st.nextToken());
            long tempY = Integer.parseInt(st.nextToken());
            map.add(new house(tempX,tempY));
        }

        Collections.sort(map);

        if(k==1) {
            ArrayList<house> list = new ArrayList<>(convexHull(map));
            System.out.println(rotatingCalipers(list));
            return;
        }

        start = (double) map.get(0).x;
        end = (double) map.get(n-1).x;

        divider = new double[k+1];

        double standardCount = (double) n/k;
        int divCount = 0;

        for (int i=1; i< divider.length; i++) {
            double standardCount2 = standardCount * i;
            while (divCount < standardCount2) divCount++;
            divider[i] = map.get(divCount-1).x;
        }

        distSet = new long[k+1];
        index = 0;
        count = 0;
        for (int i=1; i<divider.length; i++) {
            ArrayList<house> houses = new ArrayList<>();
            while (count < map.size() && map.get(count).x <= divider[i]) {
                houses.add(map.get(count));
                count++;
            }
            ArrayList<house> list = new ArrayList<>(convexHull(houses));
            distSet[i] = rotatingCalipers(list);
        }

        findMax();

        while (index != 0 && divider[index] - divider[index-1] >= 1) { // 종료 조건 참색
            if (index == 1) {
                divider[index] = (divider[index] + start) / 2;
                long right = finalDist(divider[index], divider[index+1]);
                long left = finalDist(start, divider[index]);

                distSet[index] = left;
                distSet[index+1] = right;

                findMax();

            } else if (index == k) {
                divider[index-1] = (divider[index] + divider[index-1]) / 2;
                long left = finalDist(divider[index-2], divider[index-1]);
                long right = finalDist(divider[index-1], divider[index]);

                distSet[index-1] = left;
                distSet[index] = right;

                findMax();

            } else {
                divider[index] = (divider[index-1] + divider[index] * 3) / 4;
                divider[index-1] = (divider[index-1] * 3 + divider[index]) / 4;

                long middle = finalDist(divider[index-1], divider[index]);
                long left = finalDist(divider[index-2], divider[index-1]);
                long right = finalDist(divider[index], divider[index+1]);

                distSet[index-1] = left;
                distSet[index] = middle;
                distSet[index+1] = right;

                findMax();

            }
        }
    }
}

/*
87946846488004 보다 작은 max값 탐색부터 시작



 */