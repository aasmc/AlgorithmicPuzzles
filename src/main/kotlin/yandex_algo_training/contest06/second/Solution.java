package yandex_algo_training.contest06.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> collect = Arrays.stream(br.readLine().trim().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
        int edges = collect.get(1);
        int vertices = collect.get(0);
        Graph g = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] split = br.readLine().trim().split(" ");
            g.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        ArrayList<Integer>[] scc = getSCC(g);
        if (scc.length != 0) {
            int nIdx = 0;
            for (int i = 0; i < scc.length; i++) {
                if (scc[i] == null) {
                    nIdx = i;
                    break;
                }
            }
            System.out.println(nIdx);
            for (int i = 0; i < scc.length; i++) {
                ArrayList<Integer> comp = scc[i];
                if (comp == null) break;
                System.out.println(comp.size());
                String res = comp.stream().map(String::valueOf).collect(Collectors.joining(" "));
                System.out.println(res);
            }
        } else {
            for (int i = 1; i < vertices; i++) {
                System.out.println(1);
                System.out.println(i);
            }
        }
    }
    static int time = 0;
    static int arrIdx = 0;
    private static ArrayList<Integer>[] getSCC(Graph g) {
        int[] low = new int[g.getVertexCount() + 1];
        int[] disc = new int[g.getVertexCount() + 1];
        for (int i = 0; i < low.length; i++) {
            low[i] = -1;
            disc[i] = -1;
        }
        boolean[] onStack = new boolean[g.getVertexCount() + 1];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer>[] scc = new ArrayList[g.getVertexCount() + 1];
        for (int v = 1; v <= g.getVertexCount(); ++v) {
            dfs(v, g, low, disc, onStack, stack, scc);
        }
        return scc;
    }

    private static void dfs(int source, Graph g, int[] low, int[] disc, boolean[] onStack,
                              ArrayDeque<Integer> stack,
                              ArrayList<Integer>[] scc) {
        low[source] = time;
        disc[source] = time;
        ++time;
        onStack[source] = true;
        stack.push(source);
        for (int adj: g.getAdjacentFor(source)) {
            if (disc[adj] == -1) {
                dfs(adj, g, low, disc, onStack, stack, scc);
                low[source] = Math.min(low[source], low[adj]);
            } else if (onStack[adj]) {
                low[source] = Math.min(low[source], low[adj]);
            }
        }
        int top = 0;
        if (low[source] == disc[source]) {
            var list = new ArrayList<Integer>();
            while (top != source) {
                top = stack.pop();
                list.add(top);
                onStack[top] = false;
            }
            scc[arrIdx++] = list;
        }
    }
}

class Graph {
    private final int vertexCount;
    private ArrayList<Integer>[] storage;

    Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        storage = new ArrayList[vertexCount + 1];
        for (int i = 0; i < vertexCount + 1; i++) {
            storage[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        storage[from].add(to);
        storage[to].add(from);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public ArrayList<Integer> getAdjacentFor(int vertex) {
        return storage[vertex];
    }
}