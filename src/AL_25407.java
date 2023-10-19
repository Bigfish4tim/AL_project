import java.util.*;

public class AL_25407 {

    static int N;

    static int k;

    public static class Node {
        int node;
        int distance;

        Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static Map<Integer, List<Node>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i=2; i<=N; i++) {
            int from = scanner.nextInt();
            int length = scanner.nextInt();

            graph.get(from).add(new Node(i, length));
            graph.get(i).add(new Node(from, length));
        }

        System.out.println(graph.get(1));

    }
}
