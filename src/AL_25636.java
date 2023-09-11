import java.util.*;

public class AL_25636 {

    public static class Node implements Comparable<Node> {
        Integer vertex;
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
    static int[] water;
    static int roadCount;
    static int fireStation;
    static int fireLocation;

    public static void main(String[] args) {
        Map<Integer, ArrayList<Node>> map = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        cross = sc.nextInt();

        water = new int[cross];

        for(int i=0; i<cross; i++) {
            water[i] = sc.nextInt();
        }

        roadCount = sc.nextInt();

        ArrayList<ArrayList<Node>> graphs = new ArrayList<>();

        for(int i=0; i<cross+1; i++) {
            graphs.add(new ArrayList<>());
        }

        for(int i=0; i<roadCount; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            graphs.get(from).add(new Node(to, weight));
        }

        for(int i=0; i<cross; i++) {
            map.put(i+1, graphs.get(i+1));
        }

        fireStation = sc.nextInt();
        fireLocation = sc.nextInt();
        Result ans = dijFunc(map, fireStation, water);

        if(ans.result.get(fireLocation) == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans.result.get(fireLocation) + " " + ans.waterSum.get(fireLocation));
        }
    }
}
