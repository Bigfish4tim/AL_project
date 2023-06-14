import java.util.*;

public class AL_1845 {
    static Map<List<Integer>, Integer> toSort;

    public static void precalc(int n) {
        List<Integer> perm = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();

        for (int i = 0; i < n; i++)
            perm.add(i);

        q.offer(perm);
        toSort.put(perm, 0);

        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            int cost = toSort.get(here);

            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j++) {
                    Collections.reverse(here.subList(i, j));
                    if (!toSort.containsKey(here)) {
                        toSort.put(new ArrayList<>(here), cost + 1);
                        q.offer(new ArrayList<>(here));
                    }
                    Collections.reverse(here.subList(i, j));
                }
            }
        }
    }

    public static int solve(List<Integer> perm) {
        int n = perm.size();
        List<Integer> fixed = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int smaller = 0;
            for (int j = 0; j < n; j++) {
                if (perm.get(i) > perm.get(j))
                    smaller++;
            }
            fixed.add(smaller);
        }

        return toSort.get(fixed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C = scanner.nextInt();
        toSort = new HashMap<>();
        precalc(8);

        while (C-- > 0) {
            int N = scanner.nextInt();
            List<Integer> v = new ArrayList<>();
            int k = 1000001;

            for (int i = 0; i < N; i++) {
                int tmp = scanner.nextInt();
                v.add(tmp);
            }

            while (v.size() < 8)
                v.add(k++);

            System.out.println(solve(v));
        }
    }
}
