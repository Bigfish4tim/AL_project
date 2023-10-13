import java.util.*;

public class AL_25636_2 {

    public static class Node implements Comparable<Node> {
        int vertex;
        long weight;

        public Node(Integer vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static class Amount {
        long weightAmount;
        long waterAmount;

        public Amount(long weightAmount, long waterAmount) {
            this.weightAmount = weightAmount;
            this.waterAmount = waterAmount;
        }
    }

    static int cross;
    static long[] water;
    static int roadCount;
    static int fireStation;
    static int fireLocation;
    static Map<Integer, ArrayList<Node>> graph = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        cross = sc.nextInt();

        water = new long[cross+1];

        for (int i=1; i<cross+1; i++) {
            water[i] = sc.nextLong();
        }

        roadCount = sc.nextInt();

        for (int i=0; i<cross+1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i=0; i<roadCount; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            long weight = sc.nextLong();

            graph.get(from).add(new Node(to, weight));
        }

        fireStation = sc.nextInt();
        fireLocation = sc.nextInt();

        Amount[] result = dij();

        if (result[fireLocation].weightAmount == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result[fireLocation].weightAmount + " " + result[fireLocation].waterAmount);
        }
    }

    public static Amount[] dij() {
        Amount[] distances = new Amount[cross+1];
        for (int i=0; i< distances.length; i++) {
            distances[i] = new Amount(Long.MAX_VALUE, 0);
        }
        distances[fireStation].weightAmount = 0;
        distances[fireStation].waterAmount = water[fireStation];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(fireStation, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.vertex;
            long currentDistance = currentNode.weight;
            long currentWater = distances[current].waterAmount;

            if (currentDistance > distances[current].weightAmount)
                continue;

            for (Node neighbor : graph.get(current)) {
                long newDistance = currentDistance + neighbor.weight;
                long newWater = currentWater + water[neighbor.vertex];
                if (newDistance < distances[neighbor.vertex].weightAmount) {
                    distances[neighbor.vertex].weightAmount = newDistance;
                    distances[neighbor.vertex].waterAmount = newWater;
                    pq.add(new Node(neighbor.vertex, newDistance));

                } else if (newDistance == distances[neighbor.vertex].weightAmount) {
                    if (newWater > distances[neighbor.vertex].waterAmount) {
                        distances[neighbor.vertex].weightAmount = newDistance;
                        distances[neighbor.vertex].waterAmount = newWater;
                        pq.add(new Node(neighbor.vertex, newDistance));
                    }
                }
            }
        }
        return distances;
    }
}


/*
6
1 1 1 2 2 1
6
1 2 2
1 4 3
2 3 2
3 6 2
4 5 1
5 6 2
1 6
 */

/*


 */