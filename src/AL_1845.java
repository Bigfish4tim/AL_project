import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AL_1845 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        int[] finalArray = new int[N];
        for (int i = 0; i < N; i++) {
            finalArray[i] = scanner.nextInt();
        }

        List<String> operations = new ArrayList<>();
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                List<Integer> cycle = new ArrayList<>();
                int current = i;
                while (!visited[current]) {
                    cycle.add(current);
                    visited[current] = true;
                    current = findIndex(finalArray, arr[current]);
                }

                if (cycle.size() > 1) {
                    reverse(arr, cycle);
                    operations.add((cycle.get(0) + 1) + " " + (cycle.get(cycle.size() - 1) + 1));
                }
            }
        }

        System.out.println(operations.size());
        for (String operation : operations) {
            System.out.println(operation);
        }
    }

    private static int findIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void reverse(int[] array, List<Integer> indices) {
        int start = 0;
        int end = indices.size() - 1;
        while (start < end) {
            int temp = array[indices.get(start)];
            array[indices.get(start)] = -array[indices.get(end)];
            array[indices.get(end)] = -temp;
            start++;
            end--;
        }
    }
}
