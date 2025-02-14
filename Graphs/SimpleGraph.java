import java.util.*;

public class SimpleGraph {

    static List<List<Integer>> convertMatrix2List(int[][] G) {
        int n = G.length;
        int noEdge = G[0][0];
        // Initialize list to return
        List<List<Integer>> L = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            L.add(new ArrayList<>());
        }
        // Traverse array G
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (G[u][v] != noEdge) {
                    // There is an edge between vertices u, v.
                    // Add vertex v to the list for vertex u
                    L.get(u).add(v);
                }
            }
        }
        // Done!
        return L;
    } // method convertMatrix2List

    static int[][] convertList2Matrix(List<List<Integer>> L) {
        // Guard:
        if (L.isEmpty())
            return new int[0][0];
        // Initialize output array, using MAX_VALUE as infinity
        int n = L.size();
        int[][] G = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                G[i][j] = Integer.MAX_VALUE;
            }
        }
        // Traverse list L
        for (int u = 0; u < n; u++) {
            for (int v : L.get(u)) {
                // There is an edge (u,v); mark it in G
                G[u][v] = 1;
            }
        }
        // Done
        return G;
    } // method convertList2Matrix

    public static void main(String[] args) {

        int __ = Integer.MAX_VALUE;

        // Populate a demo adjacency matrix
        int[][] G = {
                { __, 30, __, __, 90 },
                { 30, __, 60, __, __ },
                { __, 60, __, 70, __ },
                { __, __, 70, __, 80 },
                { 90, __, __, 80, __ }
        };

        // Populate a demo adjacency list.
        List<List<Integer>> L = new ArrayList<>();
        // We want the list to represent a graph with 5 vertices. We can obtain L by
        // using method convertMatrix2List. I chose to populate it manually to exemplify
        // how much more versatile Python is for this kind of study.
        int n = 5;
        for (int u = 0; u < n; u++) {
            L.add(new ArrayList<>());
        }
        // Neighbors for vertex 0: 1, 4
        L.get(0).add(1);
        L.get(0).add(4);
        // Neighbors for vertex 1: 0, 2
        L.get(1).add(0);
        L.get(1).add(2);
        // Neighbors for vertex 2: 1, 3
        L.get(2).add(1);
        L.get(2).add(3);
        // Neighbors for vertex 3: 2, 4
        L.get(3).add(2);
        L.get(3).add(4);
        // Neighbors for vertex 4: 0, 3
        L.get(4).add(0);
        L.get(4).add(3);
    } // method main

} // class SimpleGraph