package geeks_for_geeks.algorithms.graphs.kotlin

object  DFSTraverser {
    fun <V : Comparable<V>> dfsNoSource(graph: Graph<V>, consume: (V) -> Unit) {
        val visited = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            val idx = graph.getVertexIdx(v)
            if (!visited[idx]) {
                dfsHelper(graph, v, visited, consume)
            }
        }
    }

    fun <V : Comparable<V>> dfsForSource(graph: Graph<V>, source: V, consume: (V) -> Unit) {
        val visited = BooleanArray(graph.getVertexCount())
        dfsHelper(graph, source, visited, consume)
    }

    private fun <V : Comparable<V>> dfsHelper(graph: Graph<V>, source: V, visited: BooleanArray, consume: (V) -> Unit) {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true
        consume(source)
        for (v in graph.getAdjacentFor(source)) {
            dfsHelper(graph, v, visited, consume)
        }
    }
}