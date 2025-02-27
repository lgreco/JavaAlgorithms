public class MinimumSpanningTree {

    static int __ = Integer.MAX_VALUE;

    static int[][] G = {
            { __, __, __, 5, 1, __ },
            { __, __, 20, 5, __, 10 },
            { __, 20, __, 10, __, __ },
            { 5, 5, 10, __, __, 15 },
            { 1, __, __, __, __, 20 },
            { __, 10, __, 15, 20, __ }
    };

    public static void boruvka(int[][] G) {

        // Create the output graph
        int n = G.length;
        int[][] T = new int[n][n];
        for (int u=0; u< n; u++)
            for (int v = 0; v <n; v++)
                T[u][v] = G[0][0];
    }

    
}
