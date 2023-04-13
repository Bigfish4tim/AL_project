import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15774 {
    static int n, k;
    static house[] map;

    public static class house implements Comparable<house> {
        int x;
        int y;

        public int getX() { return x; }
        public int getY() { return y; }

        public house(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(house o) {
            if (this.x > o.getX()) return 1;
            else if (this.x < o.getX()) return -1;
            else return Integer.compare(this.y, o.getY());
        }
    }

    public static int ccw(house A, house B, house C) {
        long cal = 0;
        cal = (long)(B.x - A.x) * (C.y - A.y) - (long)(C.x-A.x) * (B.y-A.y);
        if(cal > 0)    return 1;
        else if (cal< 0)    return -1;
        else    return 0;
    }

    public static long dist(house p1, house p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return (dx*dx + dy*dy);
    }

    public static house[] caliph() {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new house[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i].x = Integer.parseInt(st.nextToken());
            map[i].y = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map);
    }
}
