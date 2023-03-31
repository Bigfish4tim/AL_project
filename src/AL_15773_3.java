import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_15773_3 {
    static int n;
    static int count;
    static data[] dataArr;
    static boolean[] visit;
    static ArrayList<data> list = new ArrayList<>();

    public static class data implements Comparable<data> {
        long l;
        int d;

        public long getL() { return l; }
        public int getD() { return d; }

        public data(long l, int d) {
            this.l = l;
            this.d = d;
        }

//        @Override
//        public int compareTo(data o) {
//            if(this.l+this.d > o.getL()+o.getD()) return 1;
//            else if(this.l+this.d < o.getL()+o.getD()) return -1;
//            else return Long.compare(this.l, o.getL());
//        }
//        @Override
//        public int compareTo(data o) {
//            if(this.d > getD()) return 1;
//            else if(this.d < o.getD()) return -1;
//            else return Long.compare(this.l, o.getL());
//        }
        @Override
        public int compareTo(data o) {
            if(this.l > getL()) return 1;
            else if(this.l < o.getL()) return -1;
            else return Long.compare(this.d, o.getD());
        }
    }

    public static int step() {
        int listSum = 0;
        int sum = 0;
        int cnt;
        int check;
        while (!list.isEmpty()) {
            listSum++;
            cnt = 0;
            check = -1;
            for(int i=0; i<list.size(); i++) {
                list.sort(null);
                data temp = list.remove(i);
//                list.sort(new Comparator<data>() {
//                    @Override
//                    public int compare(data o1, data o2) {
//                        if (o1.getL() > o2.getL()) return 1;
//                        else if (o1.getL() < o2.getL()) return -1;
//                        else return Integer.compare(o1.getD(), o2.getD());
//                    }
//                });
                int tempSum = sum + temp.d;
                int tempCheck = 0;
                for(int j=0; j<list.size(); j++) {
                    if (tempSum <= list.get(j).l) tempCheck++;
                }
                if (cnt < tempCheck) {
                    cnt = tempCheck;
                    check = i;
                }
                list.add(temp);
            }
            list.sort(null);
            if (check == -1) break;
            sum += list.remove(check).d;
        }
        return listSum;
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

    public static void binarySearch(List<Integer> numbers, Integer value){
        if(numbers == null || numbers.isEmpty()){
            return;
        }

        final Integer comparison = numbers.get(numbers.size()/2);
        System.out.println("기준값: "+comparison);
        if(value.equals(comparison)){
            System.out.print("결과: " + comparison);
            return;
        }

        if(value<comparison){
            //subList()메소드는 리스트의 일부를 추출함
            binarySearch(numbers.subList(0, numbers.size()/2), value);
        }else{
            binarySearch(numbers.subList(numbers.size()/2+1, numbers.size()), value);
        }
    }

    public static void starter2() {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        dataArr = new data[n];
        visit = new boolean[n];


        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long tempL = Long.parseLong(st.nextToken());
            int tempD = Integer.parseInt(st.nextToken());
            dataArr[i] = new data(tempL, tempD);
//            list.add(new data(tempL, tempD));
        }


        Arrays.sort(dataArr);
//
//        starter(0,0,0);

//        list.sort(null);

//        list.sort(new Comparator<data>() {
//            @Override
//            public int compare(data o1, data o2) {
//                if (o1.getL() > o2.getL()) return 1;
//                else if (o1.getL() < o2.getL()) return -1;
//                else return Integer.compare(o1.getD(), o2.getD());
//            }
//        });

//        System.out.println(count);

        System.out.println(step());
    }
}