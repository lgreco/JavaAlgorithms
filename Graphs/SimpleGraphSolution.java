import java.util.*;

public class SimpleGraphSolution {

    /**
     * Converts an adjacency matrix into an adjacency list
     * 
     * @param G int[][] with the adj matrix of a simple graph
     * @return List-of-lists with the neighbors for each vertex in the graph
     */
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

    /**
     * Converts an adjacency list into an adjacency matrix
     * 
     * @param L List-of-lists with the neighbors for each vertex in the graph
     * @return int[][] with the adj matrix of a simple graph
     */
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

    /**
     * Counts the components of a simple graph
     * 
     * @param G int[][] with the adj matrix of a simple graph
     * @return int count of components
     */
    static int countComponents(int[][] G) {
        // Initialize return value
        int count = 0;
        // Shortcut to number of vertices in the graph for notation consistency
        int n = G.length;
        // List of visited vertices; for consistency with class notes, we call
        // them colored vertices
        List<Integer> coloredVertices = new ArrayList<>();
        // Explore every vertex in the graph
        for (int u = 0; u < n; u++) {
            // Check if we have visited vertex u or a vertex in its component before. This
            // list allows us to skip vertices in the same component.
            if (coloredVertices.indexOf(u) == -1) {
                // We've hit a new component
                count++;
                // Find all the vertices reachable from u -- these are all vertices in the same
                // component with vertex u
                List<Integer> reachableFromU = findReachableFrom(u, G);
                // Add all vertices that are reachable from u to the list of colored vertices,
                // so that we can skip vertices in the same component
                for (int v : reachableFromU) {
                    coloredVertices.add(v);
                }
            }
        }
        // Done
        return count;
    } // method countComponents

    /**
     * Finds all vertices reachable from a specific vertex
     * 
     * @param s int vertex label to determine reachability from it
     * @param G int[][] with the adj matrix of a simple graph
     * @return List of vertex labels reachable from s
     */
    static List<Integer> findReachableFrom(int s, int[][] G) {
        List<Integer> visited = new ArrayList<>();
        List<Integer> exploreNext = new ArrayList<>();
        // Initialize bag of vertices to visit next, with source vertex s
        exploreNext.add(s);
        // Shortcut to number of vertices in the graph for notation consistency
        int n = G.length;
        // While the back of vertices to visit next is not empty
        while (exploreNext.size() > 0) {
            // Remove a vertex from the bag
            int u = exploreNext.remove(0);
            // Check if we've been here before
            if (visited.indexOf(u) == -1) {
                // Nope, mark this vertex as visited
                visited.add(u);
                // Mark all its adjacent vertices to explore next.
                for (int v = 0; v < n; v++) {
                    // There is an edge (u,v) when its weight != to weight of edge (0,0) because we
                    // do not allow loops from a vertex to itelf.
                    if (G[u][v] != G[0][0]) {
                        exploreNext.add(v);
                    }
                }
            }
        }
        // Done
        return visited;
    } // method reachableFrom

    /** Driver code */
    public static void main(String[] args) {

        // Infinity
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

        // The 8x8 adjacency matrix for testing the assignment

        int[][] G8 = {
                { __, 1, 1, __, __, __, __, __ },
                { 1, __, __, 1, 1, __, __, __ },
                { __, __, __, __, 1, __, __, __ },
                { __, 1, __, __, 1, __, __, __ },
                { __, 1, 1, 1, __, __, __, __ },
                { __, __, __, __, __, __, __, __ },
                { __, __, __, __, __, __, __, 1 },
                { __, __, __, __, __, __, 1, __ }
        };

        // Test count of components ; expect 3
        System.out.println(countComponents(G8));
    } // method main

} // class SimpleGraph