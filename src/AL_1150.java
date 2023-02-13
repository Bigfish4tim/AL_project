import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1150 {
    static int n, k;
    static int[] comp;
    static int min;
    static boolean[] visit;

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

    public static int count(distance[] distArr, int num) {
        visit = new boolean[n+1];

        visit[distArr[num].start] = true;
        visit[distArr[num].end] = true;

        int total2 = distArr[num].length;

        int count = 1;

        for(int i=0; i<distArr.length; i++) {
            if(count == k) {
                return total2;
            }

            if(visit[distArr[i].start] || visit[distArr[i].end]) {
                continue;
            }
            total2 += distArr[i].length;
            count++;
            visit[distArr[i].start] = true;
            visit[distArr[i].end] = true;
        }

        return Integer.MAX_VALUE;
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
        }

        for(int i=0; i<distArr.length; i++) {
            int temp = count(distArr, i);

            if(temp < min) min = temp;
        }

        System.out.println(min);
    }
}
