package geeks_for_geeks.algorithms.graphs

object DirectedGraphCycleDetector {
    fun <T : Comparable<T>> detectCycleViaDFS(graph: Graph<T>): Boolean {
        val visited = BooleanArray(graph.getVertexCount())
        val recursionStack = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                if (dfsCycleHelper(graph, v, visited, recursionStack)) {
                    return true
                }
            }
        }
        return false
    }

    private fun <T : Comparable<T>> dfsCycleHelper(
        graph: Graph<T>,
        source: T,
        visited: BooleanArray,
        // keeps track of vertices currently involved in the recursion stack
        recursionStack: BooleanArray
    ): Boolean {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true
        // mark this vertex as the one in the recursion stack
        recursionStack[idx] = true
        for (v in graph.getAdjacentFor(source)) {
            val vIdx = graph.getVertexIdx(v)
            // if we have not visited this vertex, and there's a cycle among its adjacent
            // vertices then return true
            if (!visited[vIdx] && dfsCycleHelper(graph, v, visited, recursionStack)) {
                return true
                // if we have visited this vertex and it is in the recursion stack,
                // then we have a cycle
            } else if (recursionStack[vIdx]) {
                return true
            }
        }
        // when we reach here, we checked all reachable vertices, so mark this
        // as not in the recursion stack
        recursionStack[idx] = false
        return false
    }
}