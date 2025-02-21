#include <stdio.h>
#include <stdlib.h>

#define INFINITY (2147483647 / 2) // Simulate Java's Integer.MAX_VALUE / 2

typedef struct {
    int *dist;
    int *pred;
} Results;

// Function prototypes
Results *sssp(int **G, int n, int s);
void print_results(Results *results, int n);
void free_results(Results *results);

int main() {
    int __ = INFINITY;
    int n = 6; // Number of vertices

    // Dynamically allocate adjacency matrix
    int **G = (int **)malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++) {
        G[i] = (int *)malloc(n * sizeof(int));
    }

    // Define adjacency matrix (manual initialization)
    int tempG[6][6] = {
        {__, __, __, 2, 20, __},
        {__, __, __, __, __, 2},
        {__, 6, __, __, __, __},
        {__, 5, 4, __, 25, 15},
        {__, __, __, __, __, __},
        {__, __, __, __, 6, __}
    };

    // Copy values to dynamically allocated matrix
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            G[i][j] = tempG[i][j];

    // Compute shortest paths from source node 0
    Results *results = sssp(G, n, 0);

    // Print results
    print_results(results, n);

    // Free allocated memory
    free_results(results);
    for (int i = 0; i < n; i++) free(G[i]);
    free(G);

    return 0;
}

/**
 * Function to compute Single-Source Shortest Path (SSSP) using brute-force relaxation.
 * Assumes a Directed Acyclic Graph (DAG) with no negative weights.
 */
Results *sssp(int **G, int n, int s) {
    // Allocate memory for results
    Results *results = (Results *)malloc(sizeof(Results));
    results->dist = (int *)malloc(n * sizeof(int));
    results->pred = (int *)malloc(n * sizeof(int));

    // Initialize distances and predecessors
    for (int i = 0; i < n; i++) {
        results->dist[i] = INFINITY;
        results->pred[i] = -1; // No predecessor
    }
    // Source vertex distance is 0
    results->dist[s] = 0;

    // Perform relaxation n-1 times
    for (int step = 0; step < n - 1; step++) {
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (G[u][v] < G[0][0]) { // Edge exists
                    int newDist = results->dist[u] + G[u][v];
                    // Check if edge is tense
                    if (newDist < results->dist[v]) { // Relaxation step
                        results->dist[v] = newDist;
                        results->pred[v] = u;
                    }
                }
            }
        }

    }
    return results;
}

/** Prints the distances and predecessor arrays */
void print_results(Results *results, int n) {
    printf("Distances: [");
    for (int i = 0; i < n; i++) {
        if (results->dist[i] == INFINITY) 
            printf(" INF");
        else
            printf(" %d", results->dist[i]);
        if (i < n - 1) printf(",");
    }
    printf(" ]\n");

    printf("Predecessors: [");
    for (int i = 0; i < n; i++) {
        printf(" %d", results->pred[i]);
        if (i < n - 1) printf(",");
    }
    printf(" ]\n");
}

/** Frees allocated memory for results */
void free_results(Results *results) {
    free(results->dist);
    free(results->pred);
    free(results);
}
