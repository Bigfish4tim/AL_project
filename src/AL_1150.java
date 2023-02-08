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
            if(this.length > o.length) return 1;
            else if(this.length < o.length) return -1;
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
            distance temp = pq.poll();
            assert temp != null;
            if(arr.contains(temp.start) || arr.contains(temp.end)) {
                q.add(temp);
                continue;
            }
            total += temp.length;
        }
        while (!q.isEmpty()) pq.add(q.poll());

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        comp = new int[n+1];
        min = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            comp[i] = Integer.parseInt(st.nextToken());
            if(i==1)
                continue;
            pq.add(new distance(i-1, i, comp[i]-comp[i-1]));
        }

        int startPoint = -1;
        int endPoint = -1;

        distance[] distArr = pq.toArray(new distance[0]);

        for(int i=0; i<pq.size(); i++) {
            distance temp = pq.poll();
        }

        for(int i=0; i<k; i++) {
            distance temp = pq.poll();

        }
    }
}
