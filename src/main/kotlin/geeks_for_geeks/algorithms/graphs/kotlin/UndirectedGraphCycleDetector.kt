package geeks_for_geeks.algorithms.graphs.kotlin

object UndirectedGraphCycleDetector {
    fun <V: Comparable<V>> detectCycle(graph: Graph<V>): Boolean {
        if (graph.isDirected()) {
            throw UnsupportedOperationException("This cycle detector works only with undirected graphs.")
        }
        val visited = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            val idx = graph.getVertexIdx(v)
            if (!visited[idx]) {
                if (helper(graph, v, visited, parent = null)) {
                    return true
                }
            }
        }
        return false
    }

    private fun <V: Comparable<V>> helper(graph: Graph<V>, source: V, visited: BooleanArray, parent: V?): Boolean {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true

        for (v in graph.getAdjacentFor(source)) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                // recursively check this adjacent vertex making source vertex its parent.
                if (helper(graph, v, visited, source)) {
                    return true
                }
             // we already visited the vertex, but need to make sure it is not
             // our parent, because we are in undirected graph, this vertex (source) has parent
             // among its adjacent vertices as well.
            } else if (parent != null && v.compareTo(parent) != 0) {
                return true
            }
        }
        return false
    }
}