import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_16225 {
    static int n;
    static int[] a,b;
//    static PriorityQueue<data> pq = new PriorityQueue<>(new Comparator<data>() {
//        @Override
//        public int compare(data o1, data o2) {
//            if(o1.getA() < o2.getA()) return 1;
//            else if(o1.getA() > o2.getA()) return -1;
//            return 0;
//        }
//    });
    static PriorityQueue<data> pq = new PriorityQueue<>();
    static boolean[] visit;

    public static class data implements Comparable<data> {
        int a;
        int b;
        int index;

        public int getA() {return a;}
        public int getB() {return b;}

        public data(int a, int b, int index) {
            this.a = a;
            this.b = b;
            this.index = index;
        }

        @Override
        public int compareTo(data o) {
            if(this.b > o.getB()) return -1;
            else if(this.b < o.getB()) return 1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) b[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) pq.add(new data(a[i], b[i], i));

        while (!pq.isEmpty()) {
            data temp = pq.poll();

        }
    }
}
