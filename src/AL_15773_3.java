import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773_3 {
    static int n;
    static int count;
    static data[] dataArr;

    public static class data implements Comparable<data> {
        long l;
        int d;

        public long getL() { return l; }
        public int getD() { return d; }

        public data(long l, int d) {
            this.l = l;
            this.d = d;
        }

        @Override
        public int compareTo(data o) {
            if(this.l+this.d > o.getL()+o.getD()) return 1;
            else if(this.l+this.d < o.getL()+o.getD()) return -1;
            else return Long.compare(this.l, o.getL());
        }
    }

    public static void starter(int start, int sum, int cnt) {
        if(count < cnt) count = cnt;
        if(count == dataArr.length) return;

        for(int i=start; i< dataArr.length; i++) {
            if(dataArr.length - i <= count - cnt) break;

            data temp = dataArr[i];
            if(sum <= temp.l) {
                starter(i+1, sum+ temp.d, cnt+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        dataArr = new data[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());
            dataArr[i] = new data(tempL, tempD);
        }
        Arrays.sort(dataArr);

        starter(0,0,0);

        System.out.println(count);
    }
}