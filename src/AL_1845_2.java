import java.util.*;

public class AL_1845_2 {
    static int[] map;
    static int N;

    public static void reverse(int a, int b) {
        int range = (b-a+1);
        int size = (int) Math.ceil((double) range / 2);

        for(int i=0; i<size; i++) {
            int temp;
            temp = map[a+i];
            map[a+i] = -map[b-i];
            map[b-i] = -temp;
        }
    }

    public static boolean isc(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static ArrayList<Integer> checker1() {
        ArrayList<Integer> check = new ArrayList<>();

        for (int i=1; i<N+2; i++) {
            if (Math.abs(Math.abs(map[i]) - Math.abs(map[i-1])) != 1)
                check.add(i);
        }

        return check;
    }

    public static ArrayList<Integer> checker2() {
        ArrayList<Integer> check = new ArrayList<>();

        for (int i=1; i<N+2; i++) {
            if (Math.abs(map[i] - map[i-1]) != 1)
                check.add(i);
        }

        return check;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        map = new int[N+2];

        for (int i=0; i<N; i++)
            map[i+1] = scanner.nextInt();
        map[0] = 0;
        map[N+1] = N+1;

        ArrayList<Integer> check1 = checker1();
        ArrayList<Integer> check2 = checker2();

        System.out.println("asd");
    }
}
