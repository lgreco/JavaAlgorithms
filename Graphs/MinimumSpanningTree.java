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
            this.comp[vertex] = component;
        }

        public int getCount() {
            return this.count;
        }

        public int[] getComp() {
            return comp;
        }
    } // inner class CountAndLabel

    static class SafeEdge {
        int a;
        int b;
    } // inner class SafeEdge

    // Shortcut to infinity
    static int __ = Integer.MAX_VALUE;

    // Demo input
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
    } // method countAndLabel

    public static int[][] boruvka(int[][] G) {
        int NO_EDGE = G[0][0];
        int n = G.length;

        // Initialize candidate tree T
        int[][] T = new int[n][n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                T[u][v] = NO_EDGE;
            }
        }

        // Obtain count and component labels
        CountLabels countLabels = countAndLabel(T);
        // Unpack the object countLabels
        int count = countLabels.getCount();
        int[] comp = countLabels.getComp();

        // Principal loop
        while (count > 1) {

            // Prepare array with safe edges for each component
            SafeEdge[] safeEdges = new SafeEdge[count + 1];

            // Consider all edges in different components
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (G[u][v] != NO_EDGE && comp[u] != comp[v]) {
                        // Process comp[u]
                        if (safeEdges[comp[u]] == null) {
                            // No safe edge for this component; accept (u,v) as the safe edge
                            SafeEdge newEdge = new SafeEdge();
                            newEdge.a = u;
                            newEdge.b = v;
                            safeEdges[comp[u]] = newEdge;
                        } else {
                            // if edge (u,v) is smaller than existing safe edge, accept (u,v)
                            int a = safeEdges[comp[u]].a;
                            int b = safeEdges[comp[u]].b;
                            if (G[u][v] < G[a][b]) {
                                safeEdges[comp[u]].a = u;
                                safeEdges[comp[u]].b = v;
                            }
                        }
                        // Process comp[v]
                        if (safeEdges[comp[v]] == null) {
                            // No safe edge for this component; accept (u,v) as the safe edge
                            SafeEdge newEdge = new SafeEdge();
                            newEdge.a = u;
                            newEdge.b = v;
                            safeEdges[comp[v]] = newEdge;
                        } else {
                            // if edge (u,v) is smaller than existing safe edge, accept (u,v)
                            int a = safeEdges[comp[v]].a;
                            int b = safeEdges[comp[v]].b;
                            if (G[v][u] < G[a][b]) {
                                safeEdges[comp[u]].a = v;
                                safeEdges[comp[u]].b = u;
                            }
                        }
                    }
                }
            } // end consider all edges

            // Add safe edges to T
            for (int t = 1; t < count + 1; t++) {
                int a = safeEdges[t].a;
                int b = safeEdges[t].b;
                T[a][b] = G[a][b];
                T[b][a] = G[b][a];
            }

            // Obtain new count and component labels
            countLabels = countAndLabel(T);
            // Unpack the object countLabels
            count = countLabels.getCount();
            comp = countLabels.getComp();
        }
        // Principal loop ended and T has one component.
        return T;
    } // method boruvka

    // Driver code
    public static void main(String[] args) {
        int[][] T = boruvka(G);
        printArray(T);
    } // method main

    static void printArray(int[][] array) {
        int weight = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i][j] != array[0][0]) {
                    weight += array[i][j];
                }
            }
        }
        System.out.printf("total weight=%d\n", weight);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                String element = "\u221E";
                if (array[i][j] != array[0][0]) {
                    element = String.valueOf(array[i][j]);
                }
                System.out.printf("%4s", element);
            }
            System.out.println();
        }
    } // method printArray


} // class MinimumSpanningTree
