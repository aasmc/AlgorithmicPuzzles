package geeks_for_geeks.algorithms.graphs

interface Graph<V : Comparable<V>> {
    fun addEdge(from: V, to: V)
    fun checkEdgeExists(from: V, to: V): Boolean
    fun getAdjacentFor(vertex: V): List<V>
    fun removeEdge(from: V, to: V): Boolean
    fun visualizeGraph()

    fun bfs(source: V, consume: (V) -> Unit)

    /**
     * Performs a BFS traversal of the graph and returns the
     * number of connected components in it.
     */
    fun bfsNoSource(consume: (V) -> Unit): Int

    fun dfs(source: V, consume: (V) -> Unit)

    fun dfsNoSource(consume: (V) -> Unit)
}