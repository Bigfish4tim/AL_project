import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773 {
    static int n;
    static ArrayList<data> arr = new ArrayList<>();
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
            else {
                return Integer.compare(this.d, o.getD());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<data> subArr = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());

            arr.add(new data(tempL, tempD));
        }
        Collections.sort(arr);

        int pos = 0;

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

        boolean trig = true;

        while (!arr.isEmpty()) {
            size = arr.size();
            for (int i=0; i<size; i++) {
                data temp = arr.get(i);

                if(trig) {
                    pos = 0;
                    int j=0;
                    while (subArr.get(j).l <= temp.l) {
                        pos += subArr.get(j).d;
                        j++;
                        if (pos > temp.l) {
                            j = j-1;
                            if (temp.l == subArr.get(j).l && temp.d > subArr.get(j).d) {
                                pos -= subArr.get(j).d;
                                arr.remove(i);
                                temp = arr.get(i);
                            } else {
                                pos -= subArr.remove(j).d;
                            }
                            if (subArr.size() == j) break;
                        }
                    }

                    while (subArr.size() != j) {
                        data temp2 = subArr.remove(j);
                        arr.add(temp2);
                    }
                    trig = false;
                    Collections.sort(arr);
                }

                size = arr.size();
                if (temp.l >= pos) {
                    pos += temp.d;
                    subArr.add(arr.remove(i));
                    size--;
                    i--;
                }
            }
            trig = true;
            if (count < subArr.size()) {
                count = subArr.size();
            }
        }
        System.out.println(count);
    }
}
