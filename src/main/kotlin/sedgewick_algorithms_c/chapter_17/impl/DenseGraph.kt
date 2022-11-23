package sedgewick_algorithms_c.chapter_17.impl

import sedgewick_algorithms_c.chapter_17.adt.AdjIterator
import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph

class DenseGraph(
    private val vertexCount: Int,
    private val directed: Boolean
) : Graph {

    private var edgeCount = 0
    private val adjMatrix = ArrayList<BooleanArray>(vertexCount)

    init {
        for (i in 0 until vertexCount) {
            adjMatrix.add(BooleanArray(vertexCount) { false })
        }
    }

    override fun isDirected(): Boolean {
        return directed
    }

    override fun vertexCount(): Int {
        return vertexCount
    }

    override fun edgeCount(): Int {
        return edgeCount
    }

    override fun insert(edge: Edge) {
        val (v, w) = edge
        if (!adjMatrix[v][w]) edgeCount++
        adjMatrix[v][w] = true
        if (!directed) {
            adjMatrix[w][v] = true
        }
    }

    override fun remove(edge: Edge) {
        val (v, w) = edge
        if (adjMatrix[v][w]) edgeCount--
        adjMatrix[v][w] = false
        if (!directed) adjMatrix[w][v] = false
    }

    override fun checkEdgeExists(edge: Edge): Boolean {
        val (v, w) = edge
        return adjMatrix[v][w]
    }

    override fun adjIterator(v: Int): AdjIterator {
        return MatrixAdjIterator(v)
    }

    private inner class MatrixAdjIterator(
        private val vertex: Int
    ): AdjIterator {
        var next = -1

        override fun beg(): Int {
            next = -1
            return nxt()
        }

        override fun nxt(): Int {
            ++next
            while (next < vertexCount) {
                if (adjMatrix[vertex][next]) return next
                next++
            }
            return -1
        }

        override fun end(): Boolean {
            return next >= vertexCount
        }

    }
}