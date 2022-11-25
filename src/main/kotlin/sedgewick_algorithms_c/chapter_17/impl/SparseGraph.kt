package sedgewick_algorithms_c.chapter_17.impl

import sedgewick_algorithms_c.chapter_17.adt.AdjIterator
import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph

class SparseMultiGraph(
    private val vertexCount: Int,
    private val diGraph: Boolean
) : Graph {

    private var edgeCount = 0

    private val adjStorage = ArrayList<Node?>(vertexCount)

    init {
        for (i in 0 until vertexCount) {
            adjStorage.add(null)
        }
    }

    private data class Node(
        val v: Int,
        var next: Node? = null
    )

    override fun isDirected(): Boolean {
        return diGraph
    }

    override fun vertexCount(): Int {
        return vertexCount
    }

    override fun edgeCount(): Int {
        return edgeCount
    }

    override fun insert(edge: Edge) {
        val (v, w) = edge
        // insert w as the head of the singly linked list
        adjStorage[v] = Node(v = w, next = adjStorage[v])
        if (!diGraph) adjStorage[w] = Node(v = v, next = adjStorage[w])
        ++edgeCount
    }

    override fun remove(edge: Edge) {
        val (v, w) = edge
        val current = adjStorage[v] ?: return
        adjStorage[v] = removeVertexFromList(current, w)
        --edgeCount
        if (!diGraph) {
            val otherCurrent = adjStorage[w] ?: return
            adjStorage[w] = removeVertexFromList(otherCurrent, v)
        }
    }

    private fun removeVertexFromList(list: Node, vertex: Int): Node? {
        if (list.v == vertex) {
            return list.next
        }
        var prev = list
        var current = list.next
        while (current != null) {
            if (current.v == vertex) {
                prev.next = current.next
                current.next = null
                break
            }
            prev = current
            current = current.next
        }
        return prev
    }

    override fun checkEdgeExists(edge: Edge): Boolean {
        val (v, w) = edge
        val vList = adjStorage[v]
        val existsInV = checkVertexInList(vList, w)
        if (!diGraph) {
            val wList = adjStorage[w]
            val existsInW = checkVertexInList(wList, v)
            if ((!existsInV && existsInW) || (existsInV && !existsInW)) {
                throw IllegalStateException(
                    "Undirected graph is in inconsistent state! Vertex $v and vertex " +
                            "$w must be in each other's adjacency lists"
                )
            }
            return existsInV && existsInW
        }
        return existsInV
    }

    private fun checkVertexInList(list: Node?, vertex: Int): Boolean {
        if (list == null) return false
        var current = list
        while (current != null) {
            if (current.v == vertex) return true
            current = current.next
        }
        return false
    }

    override fun adjIterator(v: Int): AdjIterator {
        return AdjListAdjIterator(v)
    }

    override fun countIsolatedVertices(): Int {
        return adjStorage.count { it == null }
    }

    private inner class AdjListAdjIterator(
        val vertex: Int
    ) : AdjIterator {
        var next: Node? = null
        override fun beg(): Int {
            next = adjStorage[vertex]
            return if (next != null) next!!.v else -1
        }

        override fun nxt(): Int {
            if (next != null) {
                next = next!!.next
            }
            return if (next != null) next!!.v else -1
        }

        override fun end(): Boolean {
            return next == null
        }
    }
}