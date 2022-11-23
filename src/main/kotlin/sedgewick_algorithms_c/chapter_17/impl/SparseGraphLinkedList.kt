package sedgewick_algorithms_c.chapter_17.impl

import sedgewick_algorithms_c.chapter_17.adt.AdjIterator
import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph
import java.util.*
import kotlin.collections.ArrayList

class SparseGraphLinkedList(
    private val vertexCount: Int,
    private val diGraph: Boolean
) : Graph {

    private var edgeCount = 0

    private val adjStorage = ArrayList<LinkedList<Int>>(vertexCount)

    init {
        for (i in 0 until vertexCount) {
            adjStorage.add(LinkedList())
        }
    }

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
        adjStorage[v].addLast(w)
        if (!diGraph) adjStorage[w].addLast(v)
        ++edgeCount
    }

    override fun remove(edge: Edge) {
        val (v, w) = edge
        adjStorage[v].remove(w)
        --edgeCount
        if (!diGraph) {
            adjStorage[w].remove(v)
        }
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

    private fun checkVertexInList(list: LinkedList<Int>, vertex: Int): Boolean {
        return list.contains(vertex)
    }

    override fun adjIterator(v: Int): AdjIterator {
        return AdjListAdjIterator(v)
    }

    private inner class AdjListAdjIterator(
        val vertex: Int
    ) : AdjIterator {
        private var curListIterator = adjStorage[vertex].iterator()
        override fun beg(): Int {
            curListIterator = adjStorage[vertex].iterator()
            return nxt()
        }

        override fun nxt(): Int {
            return curListIterator.next()
        }

        override fun end(): Boolean {
            return curListIterator.hasNext()
        }
    }
}