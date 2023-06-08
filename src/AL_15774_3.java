import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15774_3 {
    static int n, k;
    static ArrayList<house> map = new ArrayList<>();

    static long[] distSet;
    static long max;
    static int index;

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

    public static List<house> computeConvexHull(ArrayList<house> points) {

        int n = points.size();
        if (n < 3) {
            return points; // 볼록 껍질이 형성되지 않는 경우
        }

        Stack<house> upperHull = new Stack<>();
        upperHull.push(points.get(0));
        upperHull.push(points.get(1));

        // 상단 껍질 구성
        for (int i = 2; i < n; i++) {
            while (upperHull.size() >= 2) {
                house top = upperHull.pop();
                house secondTop = upperHull.peek();
                if (isLeftTurn(secondTop, top, points.get(i))) {
                    upperHull.push(top);
                    break;
                }
            }
            upperHull.push(points.get(i));
        }

        Stack<house> lowerHull = new Stack<>();
        lowerHull.push(points.get(n - 1));
        lowerHull.push(points.get(n - 2));

        // 하단 껍질 구성
        for (int i = n - 3; i >= 0; i--) {
            while (lowerHull.size() >= 2) {
                house top = lowerHull.pop();
                house secondTop = lowerHull.peek();
                if (isLeftTurn(secondTop, top, points.get(i))) {
                    lowerHull.push(top);
                    break;
                }
            }
            lowerHull.push(points.get(i));
        }

        // 전체 볼록 껍질 구성
        List<house> convexHull = new ArrayList<>();
        upperHull.pop(); // 상단 껍질의 마지막 점은 하단 껍질에 이미 포함됨
        while (!upperHull.isEmpty()) {
            convexHull.add(upperHull.pop());
        }
        while (!lowerHull.isEmpty()) {
            convexHull.add(lowerHull.pop());
        }

        return convexHull;
    }

    private static boolean isLeftTurn(house a, house b, house c) {
        // (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)의 부호를 확인하여 좌회전인지 판단
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0;
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

    static long finalDist(long a, long b) {
        long returns = 0;

        ArrayList<house> houses = new ArrayList<>();
        for (int i=0; i<map.size(); i++) {
            if (map.get(i).x >= a && map.get(i).x <= b) houses.add(map.get(i));
        }
//        ArrayList<house> list = new ArrayList<>(convexHull(houses));
        ArrayList<house> list2 = new ArrayList<>(computeConvexHull(houses));
        returns = rotatingCalipers(list2);
        return returns;
    }

    static int trial(long x){
        int curlst = 0;
        int itr = 0;
        while(curlst < n){
            itr++;
            int thres_st = 0;
            for(int i=0; curlst + (1<<i) <= n; i++){
                if(finalDist(map.get(curlst).x, map.get(curlst + (1<<i) - 1).x) <= x){
                    thres_st = (1<<i);
                }
                else break;
            }
            int thres_ed = Math.min(thres_st * 2, n - curlst);
            while(thres_st != thres_ed){
                int m = (thres_st + thres_ed) / 2;
                if(finalDist(map.get(curlst).x, map.get(curlst + m).x) <= x){
                    thres_st = m+1;
                }
                else thres_ed = m;
            }
            if(thres_st == 0) return (int) 1e9;
            curlst += thres_st;
        }
        return itr;
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

        long left = 0;
        long right = finalDist(map.get(0).x, map.get(n-1).x);

        while (left < right) {
            long mid = (left+right)/2;

            if(trial(mid) <= k) right = mid;
            else left = mid + 1;
        }


        System.out.println(left);

//        Runtime.getRuntime().gc();
//        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.print(usedMemory + " bytes");
    }
}

// 9 번 문서 오류