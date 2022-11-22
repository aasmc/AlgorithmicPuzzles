package geeks_for_geeks.algorithms.graphs

class DFSTraverser<V : Comparable<V>>(
    private val graph: Graph<V>
) {
    fun dfsNoSource(consume: (V) -> Unit) {
        val visited = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            val idx = graph.getVertexIdx(v)
            if (!visited[idx]) {
                dfsHelper(v, visited, consume)
            }
        }
    }

    fun dfsForSource(source: V, consume: (V) -> Unit) {
        val visited = BooleanArray(graph.getVertexCount())
        dfsHelper(source, visited, consume)
    }

    private fun dfsHelper(source: V, visited: BooleanArray, consume: (V) -> Unit) {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true
        consume(source)
        for (v in graph.getAdjacentFor(source)) {
            dfsHelper(v, visited, consume)
        }
    }
}