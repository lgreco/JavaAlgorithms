#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define NO_EDGE INT_MAX

// Convert adjacency matrix to adjacency list; method is void;
// List is "returned" by reference to array of pointers ***L
void convertMatrix2List(int **G, int n, int ***L, int **listSizes) {
    *L = (int **)malloc(n * sizeof(int *));
    *listSizes = (int *)calloc(n, sizeof(int));
    
    for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
            if (G[u][v] != NO_EDGE) {
                (*listSizes)[u]++;
            }
        }
        (*L)[u] = (int *)malloc((*listSizes)[u] * sizeof(int));
        int index = 0;
        for (int v = 0; v < n; v++) {
            if (G[u][v] != NO_EDGE) {
                (*L)[u][index++] = v;
            }
        }
    }
} // convertMatrix2List

// Convert adjacency list to adjacency matrix
int **convertList2Matrix(int **L, int *listSizes, int n) {
    int **G = (int **)malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++) {
        G[i] = (int *)malloc(n * sizeof(int));
        for (int j = 0; j < n; j++) {
            G[i][j] = NO_EDGE;
        }
    }
    
    for (int u = 0; u < n; u++) {
        for (int i = 0; i < listSizes[u]; i++) {
            int v = L[u][i];
            G[u][v] = 1;
        }
    }
    return G;
} // convertList2Matrix

// Print adjacency matrix
void printMatrix(int **G, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (G[i][j] == NO_EDGE) 
                printf("INF ");
            else
                printf("%d ", G[i][j]);
        }
        printf("\n");
    }
} // printMatrix

// Print adjacency list
void printList(int **L, int *listSizes, int n) {
    for (int i = 0; i < n; i++) {
        printf("%d: ", i);
        for (int j = 0; j < listSizes[i]; j++) {
            printf("%d ", L[i][j]);
        }
        printf("\n");
    }
} // printList

// Free adjacency matrix
void freeMatrix(int **G, int n) {
    for (int i = 0; i < n; i++) {
        free(G[i]);
    }
    free(G);
} // freeMatrix

// Free adjacency list
void freeList(int **L, int *listSizes, int n) {
    for (int i = 0; i < n; i++) {
        free(L[i]);
    }
    free(L);
    free(listSizes);
} // freeList

int main() {
    int n = 5;
    int G_data[5][5] = {
        { NO_EDGE, 30, NO_EDGE, NO_EDGE, 90 },
        { 30, NO_EDGE, 60, NO_EDGE, NO_EDGE },
        { NO_EDGE, 60, NO_EDGE, 70, NO_EDGE },
        { NO_EDGE, NO_EDGE, 70, NO_EDGE, 80 },
        { 90, NO_EDGE, NO_EDGE, 80, NO_EDGE }
    };
    
    // Convert to dynamic array
    int **G = (int **)malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++) {
        G[i] = (int *)malloc(n * sizeof(int));
        for (int j = 0; j < n; j++) {
            G[i][j] = G_data[i][j];
        }
    }
    
    int **L;
    int *listSizes;
    convertMatrix2List(G, n, &L, &listSizes);
    
    printf("Adjacency List:\n");
    printList(L, listSizes, n);
    
    int **G_new = convertList2Matrix(L, listSizes, n);
    
    printf("\nReconstructed Adjacency Matrix:\n");
    printMatrix(G_new, n);
    
    freeMatrix(G, n);
    freeMatrix(G_new, n);
    freeList(L, listSizes, n);
    
    return 0;
} // main
