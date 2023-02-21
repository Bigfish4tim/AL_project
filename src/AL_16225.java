import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_16225 {
    static int n;
    static int[] a;
    static int[] b;
    static PriorityQueue<data> pq = new PriorityQueue<>();
//    static PriorityQueue<data> pq2 = new PriorityQueue<>(new Comparator<data>() {
//        @Override
//        public int compare(data o1, data o2) {
//            return Integer.compare(o2.getA(), o1.getA());
//        }
//    });
    static PriorityQueue<Integer> pq3 = new PriorityQueue<>(Collections.reverseOrder());
    static int ans = 0;

    public static class data implements Comparable<data> {
        int a;
        int b;

        public int getA() {return a;}
        public int getB() {return b;}

        public data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(data o) {
            if(this.b > o.getB()) return 1;
            else if(this.b < o.getB()) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) b[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) pq.add(new data(a[i], b[i]));
        int count = 0;

        ans += Objects.requireNonNull(pq.poll()).a;

        while (count != n/2-1) {
            data temp = pq.poll();
            data temp2 = pq.poll();

            assert temp != null;
            assert temp2 != null;
            pq3.add(temp.a);
            pq3.add(temp2.a);

            ans += Objects.requireNonNull(pq3.poll());

            count++;
        }

        System.out.println(ans);
    }
}
