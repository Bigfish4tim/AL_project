import java.util.*;

public class AL_25636 {

    public static class Node implements Comparable<Node> {
        Integer vertex;
        int weight;

        public Node(Integer vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static class Node2 {
        Integer cross;
        Integer water;

        public Node2(Integer cross, int water) {
            this.cross = cross;
            this.water = water;
        }
    }

    public static class Result {
        Map<Integer, Integer> result;
        Map<Integer, Integer> waterSum;

        public Result(Map<Integer, Integer> result, Map<Integer, Integer> waterSum) {
            this.result = result;
            this.waterSum = waterSum;
        }
    }

    public static Result dijFunc(Map<Integer, ArrayList<Node>> map, Integer start, int[] waters) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> result = new HashMap<>();
        Node pqNode;
        ArrayList<Node> nodeList;

        for(Integer key : map.keySet()) {
            result.put(key, Integer.MAX_VALUE);
        }
        result.put(start, 0);
        pq.add(new Node(start, 0));

        Map<Integer, Integer> waterMap = new HashMap<>();

        for(int i=0; i<waters.length; i++) {
            waterMap.put(i+1, waters[i]);
        }

        Map<Integer, Integer> waterSum = new HashMap<>();

        for(int i=0; i<waters.length; i++) {
            waterSum.put(i+1, 0);
        }
        waterSum.put(start, waters[start-1]);

        while (!pq.isEmpty()) {
            pqNode = pq.poll();
            if(result.get(pqNode.vertex) < pqNode.weight) {
                continue;
            }
            nodeList = map.get(pqNode.vertex);

            for(Node searchNode : nodeList) {
                int newWeight = searchNode.weight + pqNode.weight;
                if(newWeight <= result.get(searchNode.vertex)) {
                    result.put(searchNode.vertex, newWeight);
                    pq.add(new Node(searchNode.vertex, newWeight));

                    int totalwater = waterMap.get(searchNode.vertex) + waterSum.get(pqNode.vertex);
                    if(totalwater > waterSum.get(searchNode.vertex)) {
                        waterSum.put(searchNode.vertex, totalwater);
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
