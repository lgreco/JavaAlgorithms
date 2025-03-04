import java.util.ArrayList;
import java.util.Arrays;

public class DirectedAcyclicGraphSolutions {

    /**
     * Computers in-degrees for every vertex in a dag
     * 
     * @param G int[][] dag adjacency matrix
     * @return int[i] is the in-degree of vertex i
     */
    public static int[] inDegree(int[][] G) {
        int n = G.length;
        int[] dIn = new int[n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (G[v][u] != G[0][0]) {
                    dIn[u] += 1;
                }
            }
        }
        return dIn;
    } // method inDegree

    /**
     * Computers out-degrees for every vertex in a dag
     * 
     * @param G int[][] dag adjacency matrix
     * @return int[i] is the out-degree of vertex i
     */
    public static int[] outDegree(int[][] G) {
        int n = G.length;
        int[] dOut = new int[n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (G[u][v] != G[0][0]) {
                    dOut[u] += 1;
                }
            }
        }
        return dOut;
    } // method outDegree

    /**
     * Implement's Khan's algorithm for the topological sorting of a dag
     * 
     * @param G int[][] with the dag's adjacency matrix
     * @return int[i] is the vertex at the i-th topological position
     */
    public static int[] topoSort(int[][] G) {
        // Obtain number of vertices in graph
        int n = G.length;
        // Create a list to hold the source vertices throughout execution
        ArrayList<Integer> S = new ArrayList<>();
        // Initialize the return item
        int[] T = new int[n];
        // Initialize index for return array
        int topo_position = 0;
        // Obtain the in-degrees for every vertex in the graph
        int[] dIn = inDegree(G);
        // Identify the source vertices and add them to S
        for (int u = 0; u < dIn.length; u++) {
            if (dIn[u] == 0) {
                S.add(u);
            }
        }
        // This is the main part of the algorithm. Process every source vertex by
        // pretending to eliminate it from the graph -- notice, we are not touching the
        // graph itself, i.e., we are not modifying array G. Instead we update the
        // source array S with new vertices as their in-degrees are reduced due to the
        // (again) hypothetical removal of existing source vertices.
        while (S.size() > 0) {
            int u = S.remove(0);
            // Add this vertex to the topological sorting and increate the position in the
            // topological sorting array.
            T[topo_position++] = u;
            // Find all the vertices adjacent to u and reduce their in-degree by one, thus
            // simulating the removal of their source vertex.
            for (int v = 0; v < n; v++) {
                // First check if there is an edge from u to v
                if (G[u][v] != G[0][0]) {
                    dIn[v] -= 1;
                    // If the in-degree for vertex v is 0, mark it as source
                    if (dIn[v] == 0) {
                        S.add(v);
                    }
                }
            }
        }
        // Done!
        return T;
    } // method topoSort

    /**
     * Performs SSSP using the topological ordering of a dag to identify all the
     * edges that need to be relaxed.
     * 
     * @param G int[][] with adjancency matrix for the dag
     * @param s int label for the source vertex; 0 <= s <= G.length
     * @return int[] with SSSP distances from s, and int[] is predecessors for each
     *         vertex in the SSSP, packaged as an object with two int[] fields
     */
    public static ResultsForSSSP efficientSSSP(int[][] G, int s) {
        // Shortcut to number of vertices
        int n = G.length;
        // Initialize return object
        ResultsForSSSP result = new ResultsForSSSP(n, s);
        // Obtain topological sorting of G
        int[] topo = topoSort(G);
        // Explore every vertex in topological order
        for (int v : topo) {
            // Explore every edge out of this vertex. If the edge is tense, relax it.
            for (int u = 0; u < n; u++) {
                // Check if there is an edge u-->v
                if (G[u][v] != G[0][0]) {
                    // Edge exists; is it tense?
                    if (result.dist[v] > result.dist[u] + G[u][v]) {
                        // Edge is tense; relax it and mark u as predecessor to v
                        result.dist[v] = result.dist[u] + G[u][v];
                        result.pred[v] = u;
                    }
                }
            }
        }
        // Done
        return result;
    } // method efficientSSSP

    public static void main(String[] args) {
        int __ = Integer.MAX_VALUE;
        int[][] G = {
                { __, __, __, 2, 20, __ },
                { __, __, __, __, __, 2 },
                { __, 6, __, __, __, __ },
                { __, 5, 4, __, 25, 15 },
                { __, __, __, __, __, __ },
                { __, __, __, __, 6, __ }
        };
        System.out.println(Arrays.toString(inDegree(G)));
        System.out.println(Arrays.toString(outDegree(G)));
        System.out.println(Arrays.toString(topoSort(G)));
        ResultsForSSSP demo = efficientSSSP(G, 0);
        System.out.println(Arrays.toString(demo.dist));
        System.out.println(Arrays.toString(demo.pred));
    } // method main

    /*
     * Simple inner class with two lists so that sssp method can return two
     * arraylists together. Keep the fields public for easy access here.
     */
    static class ResultsForSSSP {
        int[] dist;
        int[] pred;

        // constructor
        public ResultsForSSSP(int n, int s) {
            dist = new int[n];
            pred = new int[n];
            for (int i = 0; i < n; i++) {
                pred[i] = -1;
                dist[i] = Integer.MAX_VALUE;
            }
            dist[s] = 0;
        } // constructor

        /** String representation for printing */
        @Override
        public String toString() {
            return "Distances: " + Arrays.toString(dist) + "\nPredecessors: " + Arrays.toString(pred);
        } // method toString
    } // inner class DistPred

} // class DirectedAcyclicGraph
