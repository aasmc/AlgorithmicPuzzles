package geeks_for_geeks.algorithms.graphs.java;

public class ShortestPath {

    public int minDistance(int[] dist, boolean[] shortestPathTree, int v) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < v; i++) {
            if (!shortestPathTree[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int[] dijkstra(int[][] graph, int src, int v) {
        // the output array. dist[i] will hold the shortest distance from src to i
        int[] dist = new int[v];

        // shortestPathTree[i] will be true if vertex i is included in the shortest
        // path tree or shortest distance from src to i is finalized
        boolean[] shortestPathTree = new boolean[v];

        // Initialize all distances as INFINITE and shortestPathTree[] as false
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            shortestPathTree[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // find shortest path for all vertices
        for (int count = 0; count < v - 1; ++count) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, shortestPathTree, v);
            // Mark the picked vertex as processed
            shortestPathTree[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int z = 0; z < v; z++) {
                // Update dist[z] only if is not in shortestPathTree, there is an
                // edge from u to z, and total weight of path from src to
                // z through u is smaller than current value of dist[z]
                if (!shortestPathTree[z] && graph[u][z] != 0 &&
                dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][z] < dist[z]) {
                    dist[z] = dist[u] + graph[u][z];
                }
            }
        }

        return dist;
    }
}





















