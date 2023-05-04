import java.io.*;
import java.util.*;

public class AL_10254 {
    static int t,n;
    static point[] map;

    public static class point {
        long x;
        long y;

        public point(long x, long y) {
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

    public static long dist(point p1, point p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return (dx*dx + dy*dy);
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

            for(int j=0; j<n; j++){
                if(map[0].y > map[j].y || map[0].y == map[j].y && map[0].x > map[j].x){
                    point temp = map[0];
                    map[0] = map[j];
                    map[j] = temp;
                }
            }

            Arrays.sort(map, 1, n, new Comparator<point>() {
                @Override
                public int compare(point o1, point o2) {
                    int vertex = ccw(new point(map[0].x, map[0].y), o1, o2);
                    if (vertex > 0) return -1;
                    if (vertex < 0) return 1;
                    return (int) ((Math.abs(o1.x) + o1.y) - (Math.abs(o2.x) + o2.y));
                }
            });

            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for(int j=1; j<n; j++){
                while(stack.size() > 1 && ccw(map[stack.get(stack.size()-2)], map[stack.peek()], map[j]) <=0 ){
                    stack.pop();
                }
                stack.add(j);
            }

            long max_dist = 0;
            point[] point_pair = new point[2];

            int k=1;
            for (int j=0; j<stack.size(); j++) {
                int left_next = (j+1) % stack.size();
                while (true) {
                    int right_next = (k+1) % stack.size();

                    long ab_x = map[stack.get(left_next)].x - map[stack.get(j)].x;
                    long ab_y = map[stack.get(left_next)].y - map[stack.get(j)].y;

                    long cd_x = map[stack.get(right_next)].x - map[stack.get(k)].x;
                    long cd_y = map[stack.get(right_next)].y - map[stack.get(k)].y;

                    if (ccw(new point(0,0), new point(ab_x, ab_y), new point(cd_x, cd_y)) > 0) {
                        k = right_next;
                    } else {
                        break;
                    }
                }

                if(dist(map[stack.get(j)], map[stack.get(k)]) > max_dist) {
                    max_dist = dist(map[stack.get(j)], map[stack.get(k)]);
                    point_pair[0] = map[stack.get(j)];
                    point_pair[1] = map[stack.get(k)];
                }
            }
            bw.write(point_pair[0].x + " " + point_pair[0].y + " " + point_pair[1].x + " " + point_pair[1].y + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}