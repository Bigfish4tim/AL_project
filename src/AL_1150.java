import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1150 {
    static int n, k;
    static int[] comp;
    static int min;
    static PriorityQueue<distance> pq = new PriorityQueue<>();

    public static class distance implements Comparable<distance> {
        int start;
        int end;
        int length;

        public int getLength() {
            return length;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public distance(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }


        @Override
        public int compareTo(distance o) {
            if(this.length > o.getLength()) return 1;
            else if(this.length < o.getLength()) return -1;
            return 0;
        }
    }

    public static int count(distance dis) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(dis.start);
        arr.add(dis.end);

        int total = dis.length;

        Queue<distance> q = new LinkedList<>();

        for(int i=0; i<k-1; i++) {
            if(pq.size()==0) {
                while (!q.isEmpty()) { pq.add(q.poll()); }
                return Integer.MAX_VALUE;
            }
            distance temp = pq.poll();
            assert temp != null;
            if(arr.contains(temp.start) || arr.contains(temp.end)) {
                q.add(temp);
                i--;
                continue;
            }
            total += temp.length;
            arr.add(temp.start);
            arr.add(temp.end);
            q.add(temp);
        }
        while (!q.isEmpty()) { pq.add(q.poll()); }

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<distance> tempQ = new PriorityQueue<>();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        comp = new int[n+1];
        min = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            comp[i] = Integer.parseInt(st.nextToken());
            if(i==1)
                continue;
            tempQ.add(new distance(i-1, i, comp[i]-comp[i-1]));
        }

        if(n/2 == k && n%2 == 0) {
            int total = 0;
            int size = comp.length;
            for(int i=1; i<size-1; i++) {
                if(i%2 == 1) {
                    total += (comp[i+1] - comp[i]);
                }
            }
            System.out.println(total);
            return;
        }

        distance[] distArr = new distance[tempQ.size()];

        for(int i=0, size=tempQ.size(); i<size; i++) {
            distance temp = tempQ.poll();
            distArr[i] = temp;
            pq.add(temp);
        }

        for(int i=0; i<pq.size(); i++) {
            int temp = count(distArr[i]);

            if(temp < min) min = temp;
        }

        System.out.println(min);
    }
}
