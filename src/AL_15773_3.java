import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773_3 {
    static int n;
    static int count;
    static data[] dataArr;
    static PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static class data implements Comparable<data> {
        long l;
        long d;

        public long getD() { return d; }
        public long getL() { return l; }

        public data(long l, long d) {
            this.l = l;
            this.d = d;
        }

        @Override
        public int compareTo(data o) {
            if(this.l+this.d > o.getD()+o.getL()) return 1;
            else if(this.l+this.d < o.getD()+o.getL()) return -1;
            else return Long.compare(this.l, o.getL());
        }
    }

    public static int starter2() {
        long sum = 0;
        int count = 0;
        for(int i=0; i<n; i++) {
            if (sum <= dataArr[i].l) {
                sum += dataArr[i].d;
                pq.add(dataArr[i].d);
                count++;
            } else if (dataArr[i].d < pq.peek()) {
                sum = sum - pq.poll() + dataArr[i].d;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        dataArr = new data[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            long tempD = Long.parseLong(st.nextToken());
            dataArr[i] = new data(tempL, tempD);
        }
        Arrays.sort(dataArr);

        count = starter2();

        System.out.println(count);
    }
}