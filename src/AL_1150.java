import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1150 {
    static int n, k;
    static data[] comps;

    public static class data implements Comparable<data> {
        int length;
        int left;
        int right;

        public int getLength() {
            return length;
        }

        public data(int length, int left, int right) {
            this.left = left;
            this.length = length;
            this.right = right;
        }

        @Override
        public int compareTo(data o) {
            if(this.length > o.getLength()) return 1;
            else if(this.length < o.getLength()) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        comps = new data[n+2];
        PriorityQueue<data> pq = new PriorityQueue<>();

        int s1, s2;

        st = new StringTokenizer(br.readLine());
        s1 = Integer.parseInt(st.nextToken());

        for(int i=1; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            s2 = Integer.parseInt(st.nextToken());

            comps[i] = new data(s2-s1, i-1, i+1);

            pq.add(new data(comps[i].length, i, i+1));

            s1 = s2;
        }
        comps[n] = new data(0, n-1, n+1);
        comps[n+1] = new data(0, n, n+2);
        comps[0] = new data(0, -1, 1);


        int ans = 0;

        for(int i=0; i<k;) {
            data t = pq.poll();
            assert t != null;
            int tl = t.left, tr = t.right;
            if(tl > 0 && tr <= n && tr == comps[tl].right && tl == comps[tr].left) {
                ans += t.length;
                if(++i >= k) break;

                int nl = comps[tl].left, nr = comps[tr].right;
                t.left = nl;
                t.right = nr;

                comps[nl].length = comps[nl].length + comps[tr].length - t.length;
                t.length = comps[nl].length;

                pq.add(t);
                comps[nl].right = nr;
                comps[nr].left = nl;
            }
        }

        System.out.println(ans);
    }
}
