import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_3967 {
    static char[][] map = new char[5][9];
    static Queue<int[]> hexQ;

    public static boolean calc(char[][] map) {
        if(map[0][4] + map[1][3] + map[2][2] + map[3][1] != 26) {
            return false;
        }
        if(map[0][4] + map[1][5] + map[2][6] + map[3][7] != 26) {
            return false;
        }
        if(map[1][1] + map[1][3] + map[1][5] + map[1][7] != 26) {
            return false;
        }
        if(map[3][1] + map[3][3] + map[3][5] + map[3][7] != 26) {
            return false;
        }
        if(map[1][1] + map[2][2] + map[3][3] + map[4][4] != 26) {
            return false;
        }
        if(map[1][7] + map[2][6] + map[3][5] + map[4][4] != 26) {
            return false;
        }

        return true;
    }

    public static boolean calc1() {
        return (map[0][4] - '@') + (map[1][3] - '@') + (map[2][2] - '@') + (map[3][1] - '@') == 26;
    }


    public static void hexM(int x, int y) {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] line;

        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            line = st.nextToken().toCharArray();
            for(int j=0; j<9; j++) {
                map[i][j] = line[j];
                if(map[i][j] != '.' && map[i][j] != 'x')
                    hexQ.add(new int[] {i,j});
            }
        }

        for(int i=0; i<5; i++) {
            for(int j=0; j<9; j++) {
                if(map[i][j] == 'x') {
                    hexM(i,j);
                }
            }
        }
    }
}
