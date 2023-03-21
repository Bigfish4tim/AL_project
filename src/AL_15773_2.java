import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773_2 {
    static int n;
    static ArrayList<data> arr = new ArrayList<>();
    static int count;

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
            if(this.l > o.getL()) return 1;
            else if(this.l < o.getL()) return -1;
            else return Integer.compare(this.d, o.getD());
        }
    }

    public static void starter(int start, int sum, int cnt) {
//        if(count < cnt) count = cnt;

        for(int i=start; i<arr.size(); i++) {
            if(arr.size() - i < count - cnt) break;
            data temp = arr.get(i);
            if(sum <= temp.l) starter(i+1, sum+temp.d, cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());
            arr.add(new data(tempL, tempD));
        }
        Collections.sort(arr);

        starter(0, 0, 0);

        System.out.println(count);
    }
}

