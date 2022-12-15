import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_9663 {
    static int N;
    static int[] map;
    static int count;

    public static void solve() {
        for(int i=1; i<N; i++) {
            for(int j=0; j<N; j++) {

            }
        }
    }

    public static void bfs(int x) {
        Queue<Integer> q = new LinkedList<>();

        q.add(x);

        while (!q.isEmpty()) {
            int temp = q.poll();

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N];

        for(int i=0; i<N; i++) {
            map[0] = i;
            solve();
        }
    }
}
