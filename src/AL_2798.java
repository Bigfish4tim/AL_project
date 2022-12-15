import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2798 {
    static int N, M;
    static int[] deck;
    static int jack;
    static int ans;

    public static void pick() {
        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                for(int k=j+1; k<N; k++) {
                    int sum = deck[i] + deck[j] + deck[k];
                    if(sum <= M) {
                        if((M - sum) < jack) {
                            jack = M - sum;
                            ans = sum;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jack = Integer.MAX_VALUE;

        deck = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            deck[i] = Integer.parseInt(st.nextToken());
        }

        pick();

        System.out.println(ans);
    }
}
