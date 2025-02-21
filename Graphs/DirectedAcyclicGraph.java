import java.util.Arrays;

public class DirectedAcyclicGraph {

    public static final int INFINITY = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        int __ = INFINITY;
        int[][] G = {
                { __, __, __, 2, 20, __ },
                { __, __, __, __, __, 2 },
                { __, 6, __, __, __, __ },
                { __, 5, 4, __, 25, 15 },
                { __, __, __, __, __, __ },
                { __, __, __, __, 6, __ }
        };
        // Test the inefficient SSSP
        System.out.println(sssp(G, 0));
    } // method main

    /*
     * Simple inner class with two lists so that sssp method can return two
     * arraylists together. Keep the fields public for easy access here.
     */
    static class Results {
        int[] dist;
        int[] pred;

        // constructor
        public Results(int n, int s) {
            dist = new int[n];
            pred = new int[n];
            for (int i = 0; i < n; i++) {
                pred[i] = -1;
                dist[i] = INFINITY;
            }
            dist[s] = 0;
        } // constructor

        /** String representation for printing */
        @Override
        public String toString() {
            return "Distances: " + Arrays.toString(dist) + "\nPredecessors: " + Arrays.toString(pred);
        } // method toString
    } // inner class DistPred

    /**
     * A brute-force SSSP implementation that performs n-1 relaxation steps. -1 in
     * pred indicates the corresponding vertex has no predecessor.
     */
    public static Results sssp(int[][] G, int s) {
        int n = G.length;
        Results results = new Results(n, s);
        for (int relaxationStep = 0; relaxationStep < n - 1; relaxationStep++) {
            // Check for tense edges
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (G[u][v] < G[0][0]) {
                        // There is an edge u --> v. Check if it is tense
                        if (results.dist[v] > results.dist[u] + G[u][v]) {
                            // Edge u --> v is tense, relax it
                            results.dist[v] = results.dist[u] + G[u][v];
                            // make u a predecessor of v
                            results.pred[v] = u;
                        }
                    }
                }
            }
        }
        return results;
    } // method sssp

} // class DirectedAcyclicGraph