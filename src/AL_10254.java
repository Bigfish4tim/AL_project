import java.io.*;
import java.util.*;

public class AL_10254 {
    static int t,n;
    static point[] map;

    public static class point {
        int x;
        int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int ccw(point A, point B, point C) {
        long cal = 0;
        cal = (long)(B.x - A.x) * (C.y - A.y) - (long)(C.x-A.x) * (B.y-A.y);
        if(cal > 0)    return 1;
        else if (cal< 0)    return -1;
        else    return 0;
    }

    public static int dist(point p1, point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return (dx*dx + dy*dy);
    }

    static point[] rotatingCalipers(ArrayList<point> convexHull) {
        double max_dist = 0;
        Point[] point_pair = new Point[2];

        int j = 1;
        for (int i = 0; i < convexHull.size(); i++) {
            int i_next = (i + 1) % convexHull.size();
            for (; ; ) {
                int j_next = (j + 1) % convexHull.size();

                long bx = convexHull.get(i_next).x - convexHull.get(i).x; // 왼쪽 벡터
                long by = convexHull.get(i_next).y - convexHull.get(i).y;
                long cx = convexHull.get(j_next).x - convexHull.get(j).x;   // 오른쪽 벡터
                long cy = convexHull.get(j_next).y - convexHull.get(j).y;

                long ccw = ccw(new Point(0, 0), new Point(bx, by), new Point(cx, cy));
                if (ccw > 0) {  // 반시계 방향이면 오른쪽에 있는 점을 다음으로
                    j = j_next;
                } else {    // 시계 방향이면 왼족에 있는 점을 다음으로
                    break;
                }
            }

            // 최대 거리 구하기
            if (dist(convexHull.get(i), convexHull.get(j)) > max_dist) {
                max_dist = dist(convexHull.get(i), convexHull.get(j));
                point_pair[0] = convexHull.get(i);
                point_pair[1] = convexHull.get(j);
            }
        }

        return point_pair;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for (int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            map = new point[n];

            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                int tempX = Integer.parseInt(st.nextToken());
                int tempY = Integer.parseInt(st.nextToken());
                map[j] = new point(tempX, tempY);
            }

            for(int j=0; i<n; i++){
                if(map[0].y > map[i].y || map[0].y == map[i].y && map[0].x > map[i].x){
                    point temp = map[0];
                    map[0] = map[i];
                    map[i] = temp;
                }
            }

            Arrays.sort(map, 1, n, new Comparator<point>() {
                @Override
                public int compare(point o1, point o2) {
                    int vertex = ccw(new point(map[0].x, map[0].y), o1, o2);
                    if (vertex > 0) return -1;
                    if (vertex < 0) return 1;
                    return (Math.abs(o1.x) + o1.y) - (Math.abs(o2.x) + o2.y);
                }
            });

            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for(int j=1; i<n; i++){
                while(stack.size() > 1 && ccw(map[stack.get(stack.size()-1)], map[stack.peek()], map[i]) <=0 ){
                    stack.pop();
                }
                stack.add(i);
            }

            for (int j=0; j<stack.size(); j++) {
                int a = j;

            }
        }
    }
}
