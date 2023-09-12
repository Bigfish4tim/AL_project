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

    public static class Result {
        Map<Integer, Long> result;
        Map<Integer, Long> waterSum;

        public Result(Map<Integer, Long> result, Map<Integer, Long> waterSum) {
            this.result = result;
            this.waterSum = waterSum;
        }
    }

    public static Result dijFunc(Map<Integer, ArrayList<Node>> map, Integer start, int[] waters) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Long> result = new HashMap<>();
        Node pqNode;
        ArrayList<Node> nodeList;

        for(Integer key : map.keySet()) {
            result.put(key, Long.MAX_VALUE);
        }
        result.put(start, 0L);
        pq.add(new Node(start, 0));

        Map<Integer, Integer> waterMap = new HashMap<>();

        for(int i=0; i<waters.length; i++) {
            waterMap.put(i+1, waters[i]);
        }

        Map<Integer, Long> waterSum = new HashMap<>();

        for(int i=0; i<waters.length; i++) {
            waterSum.put(i+1, 0L);
        }
        waterSum.put(start, (long) waters[start-1]);

        while (!pq.isEmpty()) {
            pqNode = pq.poll();
            if(result.get(pqNode.vertex) < pqNode.weight) {
                continue;
            }
            nodeList = map.get(pqNode.vertex);

            for(Node searchNode : nodeList) {
                long newWeight = searchNode.weight + pqNode.weight;
                if(newWeight < result.get(searchNode.vertex)) {
                    result.put(searchNode.vertex, newWeight);
                    pq.add(new Node(searchNode.vertex, newWeight));

                    long totalwater = waterMap.get(searchNode.vertex) + waterSum.get(pqNode.vertex);
                    waterSum.put(searchNode.vertex, totalwater);

                } else if (newWeight == result.get(searchNode.vertex)) {
                    long totalwater = waterMap.get(searchNode.vertex) + waterSum.get(pqNode.vertex);
                    if(totalwater > waterSum.get(searchNode.vertex)) {
                        waterSum.put(searchNode.vertex, totalwater);
                        result.put(searchNode.vertex, newWeight);
                        pq.add(new Node(searchNode.vertex, newWeight));
                    }
                }
            }
        }

        return new Result(result, waterSum);
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
    }

    public static class Amount {
        long weightAmount;
        long waterAmount;

        public Amount(long weightAmount, long waterAmount) {
            this.weightAmount = weightAmount;
            this.waterAmount = waterAmount;
        }
    }

    public static void dij() {
        Amount[] distances = new Amount[cross+1];
        Arrays.fill(distances, new Amount(Long.MAX_VALUE, 0));
        distances[fireStation].weightAmount = 0;
        distances[fireStation].waterAmount = water[fireStation];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(fireStation, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.vertex;
            long currentDistance = currentNode.weight;
            long currentWater = distances[current].waterAmount;

            if (currentDistance > distances[current].weightAmount) {
                continue;
            }

            for (Node neighbor : graph.get(current)) {
                long newDistance = currentDistance + neighbor.weight;
                if (newDistance < distances[neighbor.vertex].weightAmount) {
                    distances[neighbor.vertex].weightAmount = newDistance;
                    pq.add(new Node(neighbor.vertex, newDistance));
                }
            }
        }
    }
}
