import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1254 {
    static String S;
    static int ans = 0;

    public static boolean isPalind(String s) {
        int start = 0;
        int last = s.length()-1;
        while (start <= last) {
            if(s.charAt(start) != s.charAt(last))
                return false;
            start++;
            last--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        ans = S.length();

        for(int i=0; i<S.length(); i++) {
            if(isPalind(S.substring(i))) {
                break;
            }
            ans++;
        }
        System.out.println(ans);
    }
}
