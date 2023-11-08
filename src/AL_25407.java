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

        ArrayList<Integer> vGraph  = new ArrayList<>();
        vGraph.add(-1);

        for (int i=1; i<=N; i++) {
            if (graph.get(i).size() == 1) {
                vGraph.add(i);
            }
        }

        for (int i=1; i<=vGraph.size()-1; i++) {
            int from = vGraph.get(i);
            int to = vGraph.get(i%(vGraph.size()-1) + 1);
            int length = scanner.nextInt();

            graph.get(from).add(new Node(to, length));
            graph.get(to).add(new Node(from, length));
        }

        k = scanner.nextInt();

        int[] ans = new int[k];

        for (int i=0; i<k; i++) {
        }
    }
    public static void dij(int start, int end) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node currentNode = q.poll();
        }

    }
}
