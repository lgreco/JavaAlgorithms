import java.util.ArrayList;
import java.util.List;

public class MinimumSpanningTree {

    static class CountLabels {
        int count;
        int[] comp;

        public CountLabels(int n) {
            this.count = 0;
            this.comp = new int[n];
        } // basic constructor

        public void setCount(int count) {
            this.count = count;
        }

        public void setComponent(int vertex, int component) {
            this.comp[vertex] = comp;
        }

        public int getCount() {
            return this.count;
        }

        public int[] getComp() {
            return comp;
        }
    } // inner class CountAndLabel

    static int __ = Integer.MAX_VALUE;

    static int[][] G = {
            { __, __, __, 5, 1, __ },
            { __, __, 20, 5, __, 10 },
            { __, 20, __, 10, __, __ },
            { 5, 5, 10, __, __, 15 },
            { 1, __, __, __, __, 20 },
            { __, 10, __, 15, 20, __ }
    };

    public static CountLabels countAndLabel(int[][] graph) {
        int n = graph.length;
        CountLabels countAndLabels = new CountLabels(n);
        List<Integer> visited = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            if (visited.indexOf(u) == -1) {
                countAndLabels.setCount(countAndLabels.getCount() + 1); // count ++
                List<Integer> bag = new ArrayList<>();
                bag.add(u);
                while (bag.size() > 0) {
                    int v = bag.remove(0);
                    if (visited.indexOf(v) == -1) {
                        visited.add(v);
                        countAndLabels.setComponent(v, countAndLabels.getCount());
                        // find all the neighbors of v and add them to explore next
                        for (int w = 0; w < n; w++) {
                            if (graph[v][w] < graph[0][0]) {
                                bag.add(w);
                            }
                        }
                    }
                }
            }
        }
        return countAndLabels;
    }

    public static void boruvka(int[][] G) {
        int NO_EDGE = G[0][0];
        int n = G.length;

        // Initialize candidate tree T
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                T[u][v] = NO_EDGE;
            }
        }


    }
}
