package geeks_for_geeks.algorithms.graphs.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private final int V;
    private final List<List<Integer>> adj;

    public Graph(int v) {
        this.V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < v; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    private boolean isCyclicUtil(int i,
                                 boolean[] visited,
                                 boolean[] recursionStack) {
        // if self-loop return true
        if (recursionStack[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        recursionStack[i] = true;

        List<Integer> children = adj.get(i);
        for (Integer c : children) {
            if (isCyclicUtil(c, visited, recursionStack)) {
                return true;
            }
        }
        recursionStack[i] = false; // remove current from recursion stack
        return false;
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recursionStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints topological sorting of the complete graph.
     * Time Complexity O(V+E)
     * Space Complexity O(V)
     */
    public void topologicalSort() {
        // Create a array to store in-degrees of all
        // vertices. Initialize all in-degrees as 0.
        int[] inDegrees = new int[V];

        // Traverse the adjacency lists to fill in-degrees of vertices. This
        // step takes O(V+E) time.
        for (int i = 0; i < V; i++) {
            List<Integer> children = adj.get(i);
            for (Integer child : children) {
                inDegrees[child]++;
            }
        }

        // Create a queue and enqueue all vertices with inDegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        // initialize count of visited vertices
        int count = 0;
        // Create a list to store result of the topological
        // ordering of the vertices
        List<Integer> topOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            // extract front of the queue and add it to topological order
            int u = queue.poll();
            topOrder.add(u);

            // Iterate through all its neighbouring nodes and decrease their
            // in-degree by 1
            for(int node: adj.get(u)) {
                // if in-degree becomes 0, add it to the queue
                if (--inDegrees[node] == 0) {
                    queue.add(node);
                }
            }
            ++count;
        }
        if (count != V) {
            System.out.println("There is a cycle in the graph. Topological sorting can " +
                    "be achieved only on Directed Acyclic Graphs");
            return;
        }
        for (Integer i : topOrder) {
            System.out.println(i + " ");
        }
    }
}






























