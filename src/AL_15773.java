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
//                subArr.add(temp);
//                iter.remove();
//            }
//        }

        int size = arr.size();
        for(int i=0; i<size; i++) {
            data temp = arr.get(i);
            if(temp.l >= pos) {
                pos += temp.d;
                subArr.add(arr.remove(i));
                size--;
                i--;
            }
        }

        int count = subArr.size();
        Collections.sort(subArr);

        while (!arr.isEmpty()) {
            size = arr.size();
            for (int i=0; i<size; i++) {
                data temp = arr.get(i);
                int j=0;
                pos = 0;

                if(subArr.get(j).l <= temp.l) {
                    while (subArr.get(j).l <= temp.l) {
                        pos += subArr.get(j).d;
                        j++;
                        if (pos > temp.l) {
                            j = j-1;
                            pos -= subArr.remove(j).d;
                            break;
                        }
                    }

                    while (subArr.size() != j) {
                        data temp2 = subArr.remove(j);
//                        arr.add(subArr.remove(j));
                        arr.add(temp2);
                    }
                }

                Collections.sort(arr);

                size = arr.size();
                if (temp.l >= pos) {
                    pos += temp.d;
                    subArr.add(arr.remove(i));
                    size--;
                    i--;
                }
            }
        }

//        while (!arr.isEmpty()) {
//            Iterator<data> iter = arr.iterator();
//            while (iter.hasNext()) {
//                data temp = iter.next();
//                int i=0;
//                pos = 0;
//
//                while (subArr.get(i).l <= temp.l) {
//                    pos += subArr.get(i).d;
//                    i++;
//                    if (pos > temp.d) {
//                        pos -= subArr.remove(i).d;
//                        i = i-1;
//                        break;
//                    }
//                }
//
//                while (subArr.size() != i) {
//                    arr.add(subArr.remove(i));
//                }
//
//                Collections.sort(arr);
//
//                if(temp.l >= pos) {
//                    pos += temp.d;
//                    subArr.add(temp);
//                    iter.remove();
//                }
//            }
//        }
    }
}
