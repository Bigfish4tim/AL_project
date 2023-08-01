import java.util.*;

public class AL_2834 {
    static int N;
    static int[] map;

    public static class Cycle {
        ArrayList<Integer> elements;

        public Cycle() {
            elements = new ArrayList<>();
        }

        public void addElement(int element) {
            elements.add(element);
        }

        public void printCycle() {
            for (int element : elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        public int getSize() {
            return elements.size();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        map = new int[N+1];
        map[0] = 0;

        for (int i=1; i<N+1; i++)
            map[i] = scanner.nextInt();

        ArrayList<Cycle> cycles = new ArrayList<>();
        cycles = findCycle();

        if (cycles.size() > 2) {
            Cycle temp = new Cycle();
            for (Cycle cycle:cycles) {
                for (int i=0; i<cycle.getSize(); i++) {
                    temp.addElement(cycle.elements.get(i));
                }
            }

            ArrayList<Cycle> cycles3rd = new ArrayList<>();

            cycles3rd.add(temp);

            int[] tempInt = new int[N+1];
            tempInt[0] = 0;
            tempInt = map.clone();
            for (int i=0; i<temp.getSize()-1; i++)
                tempInt[temp.elements.get(i+1)] = map[temp.elements.get(i)];
            tempInt[temp.elements.get(0)] = map[temp.elements.get(temp.getSize()-1)];

            map = tempInt;

            cycles = findCycle();

            for (int i=0; i<cycles.size(); i++) {
                cycles3rd.add(cycles.get(0));
            }

            temp = cycles.get(0);

            for (int i=0; i<temp.getSize()-1; i++) {
                tempInt[temp.elements.get(i+1)] = map[temp.elements.get(i)];
            }
            tempInt[temp.elements.get(0)] = map[temp.elements.get(temp.getSize()-1)];

            map = tempInt;

            System.out.println(cycles3rd.size());
            for (Cycle cycle:cycles3rd) {
                System.out.print(cycle.getSize() + ": ");
                for (int i=0; i<cycle.getSize(); i++) {
                    System.out.print(cycle.elements.get(i) + " ");
                }
                System.out.println();
            }

//            for (int j : map) {
//                System.out.print(j + " ");
//            }
        } else if (cycles.size() > 0) {
            System.out.println(cycles.size());
            for (Cycle cycle:cycles) {
                System.out.print(cycle.getSize() + ": ");
                for (int i=0; i<cycle.getSize(); i++) {
                    System.out.print(cycle.elements.get(i) + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("0");
        }
    }

    public static ArrayList<Cycle> findCycle() {
        ArrayList<Cycle> output = new ArrayList<>();
        boolean[] visit = new boolean[N+1];

        for (int i=1; i<N+1; i++) {
            if (!visit[i]) {
                Cycle cycle = new Cycle();
                int current = i;

                while (!visit[current]) {
                    visit[current] = true;
                    cycle.addElement(map[current]);
                    current = map[current];
                }

                if (cycle.getSize() > 1) {
                    output.add(cycle);
                }
            }
        }
        return output;
    }

}


/*

8
8 7 6 5 4 3 2 1

9
3 2 1 6 5 4 9 8 7


 */