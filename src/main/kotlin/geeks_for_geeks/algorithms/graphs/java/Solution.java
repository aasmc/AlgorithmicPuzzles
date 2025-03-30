package geeks_for_geeks.algorithms.graphs.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * Given the adjacency list of a bidirectional graph.
     * Your task is to copy/clone the adjacency list for each vertex and return a new list.
     */
    public ArrayList<ArrayList<Integer>> printGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(V);
        int currentVertex = 0;
        for (ArrayList<Integer> list : adj) {
            ArrayList<Integer> clone = new ArrayList<>();
            clone.add(currentVertex++);
            clone.addAll(list);
            result.add(clone);
        }
        return result;
    }

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<>();
        int s = 0;

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            result.add(s);
            for (int i = 0; i < adj.get(s).size(); i++) {
                int newNode = adj.get(s).get(i);
                if (!visited[newNode]) {
                    visited[newNode] = true;
                    queue.add(newNode);
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        ArrayList<Integer> result = new ArrayList<>();
        dfsUtil(visited, 0, adj, result);
        return result;
    }

    private void dfsUtil(boolean[] visited,
                         int source,
                         ArrayList<ArrayList<Integer>> graph,
                         ArrayList<Integer> result
    ) {
        visited[source] = true;
        result.add(source);
        ArrayList<Integer> adj = graph.get(source);
        for (int i = 0; i < adj.size(); i++) {
            int u = adj.get(i);
            if (!visited[u]) {
                dfsUtil(visited, u, graph, result);
            }
        }
    }

    /**
     * Given a grid of size n*m (n is the number of rows and m is the number
     * of columns in the grid) consisting of '0's (Water) and '1's(Land).
     * Find the number of islands.
     */
    public int numIslands(char[][] grid) {
        List<List<Integer>> directions = List.of(
                List.of(0, 1),
                List.of(1, 0),
                List.of(-1, 0),
                List.of(0, -1),
                List.of(1, 1),
                List.of(1, -1),
                List.of(-1, -1),
                List.of(-1, 1)
        );
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visited[i], false);
        }
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    LinkedList<List<Integer>> queue = new LinkedList<>();
                    queue.push(List.of(row, col));
                    ++count;
                    visited[row][col] = true;
                    while (!queue.isEmpty()) {
                        List<Integer> current = queue.poll();
                        for (List<Integer> direction : directions) {
                            int newX = current.get(0) + direction.get(0);
                            int newY = current.get(1) + direction.get(1);
                            boolean newXWithinBounds = newX >= 0 && newX < rows;
                            boolean newYWithinBounds = newY >= 0 && newY < cols;
                            if (newXWithinBounds && newYWithinBounds &&
                                    grid[newX][newY] == '1' && !visited[newX][newY]) {
                                visited[newX][newY] = true;
                                queue.push(List.of(newX, newY));
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Given an undirected graph with V vertices and E edges, check whether
     * it contains any cycle or not. Graph is in the form of adjacency list
     * where adj[i] contains all the nodes ith node is having edge with.
     */
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        Arrays.fill(visited, false);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (undirectedCycleDFSHelper(
                        adj,
                        i,
                        visited,
                        -1
                )) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean undirectedCycleDFSHelper(
            ArrayList<ArrayList<Integer>> graph,
            Integer source,
            boolean[] visited,
            int parent
    ) {
        visited[source] = true;
        for (Integer adj: graph.get(source)) {
            if (!visited[adj]) {
                if (undirectedCycleDFSHelper(graph, adj, visited, source)) {
                    return true;
                }
            } else if (parent != adj) {
                return true;
            }
        }
        return false;
    }
}




















