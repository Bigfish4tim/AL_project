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
