import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773 {
    static int n;
    static PriorityQueue<data> pq = new PriorityQueue<>();

    public static class data implements Comparable<data> {
        int l;
        int d;

        public int getL() {
            return l;
        }

        public data(int l, int d) {
            this.l = l;
            this.d = d;
        }

        @Override
        public int compareTo(data o) {
            if(this.l < o.getL()) return 1;
            else if(this.l > o.getL()) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int tempL = Integer.parseInt(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());

            pq.add(new data(tempL, tempD));
        }

    }
}
