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
            stack.push(1);
            for(int j=1; i<n; i++){
                while(stack.size() > 1 && ccw(map[stack.get(stack.size()-2)], map[stack.peek()], map[i]) <=0 ){
                    stack.pop();
                }
                stack.add(i);
            }
        }
    }
}
