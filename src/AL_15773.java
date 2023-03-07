import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773 {
    static int n;
    public static class data implements Comparable<data> {
        int l;
        int d;

        public int getL() { return l; }

        public data(int l, int d) {
            this.l = l;
            this.d = d;
        }

        @Override
        public int compareTo(data o) {
            if(this.l > o.getL()) return 1;
            else if(this.l < o.getL()) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<data> pq1 = new PriorityQueue<>();

        ArrayList<data> arr = new ArrayList<>();

        ArrayList<data> subArr = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int tempL = Integer.parseInt(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());

            arr.add(new data(tempL, tempD));
        }
        Collections.sort(arr);

        int pos = 0;
        int cnt = 0;

//        Iterator<data> iter = arr.iterator();
//        while (iter.hasNext()) {
//            data temp = iter.next();
//            if(temp.l >= pos) {
//                pos += temp.d;
//                pq1.add(temp);
//                iter.remove();
//            }
//        }

        Iterator<data> iter = arr.iterator();
        while (iter.hasNext()) {
            data temp = iter.next();
            if(temp.l >= pos) {
                pos += temp.d;
                subArr.add(temp);
                iter.remove();
            }
        }

        int count = subArr.size();
        Collections.sort(subArr);

        while (!arr.isEmpty()) {
            PriorityQueue<data> pq2 = new PriorityQueue<>();

            pq1.add(arr.remove(0));



            Iterator<data> tempIter = arr.iterator();
            while (iter.hasNext()) {
                data temp = iter.next();
                int i=0;
                pos = 0;

                if (subArr.get(0).l <= temp.l) {
                    while (subArr.get(i).l <= temp.l) {
                        pos += subArr.get(i).d;
                        i++;
                        if (pos > temp.d) {
                            subArr.remove(i);
                            break;
                        }
                    }
                }
            }

        }

        while (!arr.isEmpty()) {
            PriorityQueue<data> pq2 = new PriorityQueue<>();
            pq2.add(arr.remove(0));
            assert pq2.peek() != null;
            pos = pq2.peek().l;

            assert pq1.peek() != null;
            if(pq1.peek().l <= arr.get(0).l) {
                if(pq1.peek().l <= pq2.peek().l) {
                    if(pq1.peek().d <= pq2.peek().l) {
                        pq2.add(pq1.poll());
                    }
                }
            }

            Iterator<data> tempIter = arr.iterator();
            while (iter.hasNext()) {
                data temp = tempIter.next();
                if (temp.l >= pos) {
                    pos += temp.d;;
                    pq2.add(temp);
                    tempIter.remove();
                }
            }

            Iterator<data> iter = arr.iterator();
            while (iter.hasNext()) {
                data temp = iter.next();
                if(temp.l >= pos) {
                    pos += temp.d;
                    pq1.add(temp);
                    iter.remove();
                }
            }
        }
    }
}
