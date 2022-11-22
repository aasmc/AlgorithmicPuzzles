package geeks_for_geeks.algorithms.graphs

interface Graph<V : Comparable<V>> {

    fun addEdge(from: V, to: V)

    fun checkEdgeExists(from: V, to: V): Boolean

    fun getAdjacentFor(vertex: V): List<V>

    fun removeEdge(from: V, to: V): Boolean

    fun visualizeGraph()

    fun getVertexNumber(): Int

    fun getEdgeNumber(): Long

    fun vertices(): Set<V>

    /**
     * Returns an index corresponding to a given vertex.
     *
     * If the given vertex is not present in the graph,
     * returns -1.
     *
     * Throws IllegalArgumentException if there's no index
     * associated with vertex that is present in the graph.
     */
    fun getVertexIdx(vertex: V): Int

    fun bfs(source: V, consume: (V) -> Unit)

    /**
     * Performs a BFS traversal of the graph and returns the
     * number of connected components in it.
     */
    fun bfsNoSource(consume: (V) -> Unit): Int

    fun dfs(source: V, consume: (V) -> Unit)

    fun dfsNoSource(consume: (V) -> Unit)

    fun adjIterator(source: V): AdjIterator<V>

}

interface AdjIterator<V : Comparable<V>> {
    fun next(): V
    fun hasNext(): Boolean
}