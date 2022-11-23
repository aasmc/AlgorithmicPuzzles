package sedgewick_algorithms_c.chapter_17.adt

data class Edge(
    val v: Int = -1,
    val w: Int = -1
)

interface Graph {
    fun isDirected(): Boolean
    fun vertexCount(): Int
    fun edgeCount(): Int
    fun insert(edge: Edge)
    fun remove(edge: Edge)
    fun checkEdgeExists(edge: Edge): Boolean
    fun adjIterator(v: Int): AdjIterator
}

interface AdjIterator {
    fun beg(): Int
    fun nxt(): Int
    fun end(): Boolean
}