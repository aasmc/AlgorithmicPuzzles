package geeks_for_geeks.algorithms.graphs.java;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathUnweighted {


    /**
     * Prints the shortest distance and path between source vertex and destination
     * vertex.
     */
    public static void printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s,
            int dest,
            int v
    ) {
        int[] predecessors = new int[v];
        int[] distances = new int[v];
        if (!BFS(adj, s, dest, v, predecessors, distances)) {
            System.out.println("There's no path from source " + s + " to destination " + dest + ".");
            return;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.add(crawl);
        while (predecessors[crawl] != -1) {
            path.add(predecessors[crawl]);
            crawl = predecessors[crawl];
        }

        System.out.println("Shortest path length is: " + distances[dest]);
        System.out.println("Path is ::");
        for(int i = path.size() - 1; i >= 0; --i) {
            System.out.print(path.get(i) + " ");
        }
    }


    /**
     * A modified version of BFS that stores predecessor of each vertex
     * in array [predecessors] and its distance from source in array
     * [distances].
     *
     * @param adj  representation of graph as an adjacency list
     * @param src  source vertex
     * @param dest destination vertex
     * @param v    number of vertices in the graph
     * @return whether there's a path from src to dest.
     */
    public static boolean BFS(ArrayList<ArrayList<Integer>> adj,
                              int src,
                              int dest,
                              int v,
                              int[] predecessors,
                              int[] distances) {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean[] visited = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // distances[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = -1;
        }
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        distances[src] = 0;
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.remove();
            ArrayList<Integer> uAdjList = adj.get(u);
            for (int i = 0; i < uAdjList.size(); i++) {
                int currentAdj = uAdjList.get(i);
                if (!visited[currentAdj]) {
                    visited[currentAdj] = true;
                    distances[currentAdj] = distances[u] + 1;
                    predecessors[currentAdj] = u;
                    queue.add(currentAdj);

                    // stopping condition (when we find
                    // our destination)
                    if (currentAdj == dest) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}


























