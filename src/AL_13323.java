import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_13323 {


    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        long ans = 0;
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            temp -= i;
            pq.add(temp);

            if(!pq.isEmpty() && pq.peek() > temp) {
                ans += pq.poll() - temp;
                pq.add(temp);
            }
        }

        System.out.println(ans);
    }
}
