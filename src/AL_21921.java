import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_21921 {
    static int X, N;
    static int[] map;
    static int ans = 0;
    static int ans2 = 1;

    public static void slidingWindow() {
        int sum = 0;

        for(int i=0; i<X; i++)
            sum += map[i];

        ans = sum;

        for(int i=X; i<N; i++) {
            sum -= map[i-X];
            sum += map[i];

            if(ans < sum) {
                ans2 = 0;
                ans2++;
                ans = sum;
            } else if(ans == sum) {
                ans2++;
            }
        }

        if(ans == 0) {
            System.out.println("SAD");
            return;
        }

        System.out.println(ans);
        System.out.println(ans2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            map[i] = Integer.parseInt(st.nextToken());

        slidingWindow();
    }
}
