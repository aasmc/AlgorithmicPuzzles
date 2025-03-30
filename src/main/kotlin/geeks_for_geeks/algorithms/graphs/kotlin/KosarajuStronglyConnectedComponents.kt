package geeks_for_geeks.algorithms.graphs.kotlin

import java.util.Stack

object KosarajuStronglyConnectedComponents {
    /**
     * Finds strongly connected components in a directed graph using
     * Kosaraju's algorithm.
     *
     * Step 1. Order the vertices in descending order of their DFS finish time.
     * Step 2. Reverse all the edges (transpose the graph).
     * Step 3. Do DFS traversal of the reversed graph in the order formed by step 1.
     */
    fun <V : Comparable<V>> findStronglyConnectedComponents(graph: Graph<V>): List<List<V>> {
        val ordered = orderVerticesByDFSFinishTimeDescending(graph)
        val transposed = transposeGraph(graph)

        val result = mutableListOf<MutableList<V>>()
        val visited = BooleanArray(transposed.getVertexCount()) { false }
        for (v in ordered) {
            val vIdx = transposed.getVertexIdx(v)
            if (!visited[vIdx]) {
                val list = mutableListOf<V>()
                mainDFSHelper(v, visited,transposed, list)
                if (list.isNotEmpty()) {
                    result.add(list)
                }
            }
        }
        return result
    }

    private fun <V : Comparable<V>> mainDFSHelper(
        source: V,
        visited: BooleanArray,
        transposed: Graph<V>,
        result: MutableList<V>
    ) {
        val idx = transposed.getVertexIdx(source)
        visited[idx] = true
        result.add(source)
        for (adj in transposed.getAdjacentFor(source)) {
            val adjIdx = transposed.getVertexIdx(adj)
            if (!visited[adjIdx]) {
                mainDFSHelper(adj, visited, transposed, result)
            }
        }
    }

    private fun <V : Comparable<V>> orderVerticesByDFSFinishTimeDescending(graph: Graph<V>): List<V> {
        val stack = Stack<V>()
        val visited = BooleanArray(graph.getVertexCount()) { false }
        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                orderDFSHelper(v, visited, graph, stack)
            }
        }
        val result = mutableListOf<V>()
        while (stack.isNotEmpty()) {
            result.add(stack.pop())
        }
        return result
    }

    private fun <V : Comparable<V>> orderDFSHelper(
        source: V,
        visited: BooleanArray,
        graph: Graph<V>,
        st: Stack<V>
    ) {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true
        for (u in graph.getAdjacentFor(source)) {
            val uIdx = graph.getVertexIdx(u)
            if (!visited[uIdx]) {
                orderDFSHelper(u, visited, graph, st)
            }
        }
        st.push(source)
    }

    private fun <V : Comparable<V>> transposeGraph(graph: Graph<V>): Graph<V> {
        val result = GraphAdjList<V>(directed = true)
        for (v in graph.vertices()) {
            val adj = graph.getAdjacentFor(v)
            for (u in adj) {
                result.addEdge(u, v)
            }
        }
        return result
    }
}