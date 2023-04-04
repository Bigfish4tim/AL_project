import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773_3 {
    static int n;
    static int count;
    static data[] dataArr;
    static int[] dp;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static class data implements Comparable<data> {
        long l;
        int d;
        long sum;

        public long getL() { return l; }
        public long getSum() {return sum;}

        public data(long l, int d) {
            this.l = l;
            this.d = d;
            this.sum = l+d;
        }

        @Override
        public int compareTo(data o) {
            if(this.sum > o.getSum()) return 1;
            else if(this.sum < o.getSum()) return -1;
            else return Long.compare(this.l, o.getL());
        }
    }

    public static int starter2() {
        int sum = 0;
        int count = 0;
        for(int i=0; i<n; i++) {
            if (sum <= dataArr[i].l) {
                sum += dataArr[i].d;
                pq.add(dataArr[i].d);
                count++;
            } else if (dataArr[i].d < pq.peek()) {
                sum -= pq.poll();
                sum += dataArr[i].d;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        dataArr = new data[n];
        dp = new int[n];
        pq.add(0);

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());
            dataArr[i] = new data(tempL, tempD);
        }
        Arrays.sort(dataArr);

        count = starter2();

        System.out.println(count);
    }
}