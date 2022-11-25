package sedgewick_algorithms_c.chapter_17.impl

import sedgewick_algorithms_c.chapter_17.adt.AdjIterator
import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph
import java.util.*

class SparseGraphLinkedList(
    private val vertexCount: Int,
    private val diGraph: Boolean
) : Graph {

    private data class Node(
        val from: Int,
        val to: Int,
        var prev: Node? = null,
        var next: Node? = null
    )

    private val edgeStorage: MutableMap<Int, Node> = hashMapOf()

    private var edgeCount = 0

    private val adjStorage = ArrayList<Node?>(vertexCount)

    init {
        for (i in 0 until vertexCount) {
            adjStorage.add(null)
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

    /**
     * Inserts an edge to the graph in constant time.
     * Prevents inserting an edge, that is already in the graph.
     */
    override fun insert(edge: Edge) {
        if (checkEdgeExists(edge)) return
        val (v, w) = edge
        addNodeToAdjList(v, w)
        if (!diGraph) addNodeToAdjList(w, v)
        ++edgeCount
    }

    private fun addNodeToAdjList(sourceVertex: Int, toInsert: Int) {
        val prev = adjStorage[sourceVertex]

        val node = Node(
            from = sourceVertex,
            to = toInsert,
            prev = prev,
            next = null
        )
        adjStorage[sourceVertex] = node
        edgeStorage[computeEdgeKey(sourceVertex, toInsert)] = node
    }

    /**
     * Removes the given edge from the graph in constant time.
     * If there's no edge in the graph, then nothing happens.
     */
    override fun remove(edge: Edge) {
        val (v, w) = edge
        val fromKey = computeEdgeKey(v, w)
        val toRemoveFrom = edgeStorage[fromKey]
            ?: return

        unlink(toRemoveFrom, v)
        edgeStorage.remove(fromKey)
        --edgeCount
        if (!diGraph) {
            val toKey = computeEdgeKey(w, v)
            val toRemoveTo = edgeStorage[toKey]
                ?: throw IllegalStateException(
                    "Undirected graph is in inconsistent state: vertices $v and $w have " +
                            "not been stored in each other's adjacency lists!"
                )
            unlink(toRemoveTo, w)
            edgeStorage.remove(toKey)
        }
    }

    private fun unlink(toRemoveFrom: Node, v: Int) {
        val prev = toRemoveFrom.prev
        val next = toRemoveFrom.next
        if (prev == null && next == null) {
            adjStorage[v] = null
        } else if (prev == null) {
            next!!.prev = null
            toRemoveFrom.next = null
            adjStorage[v] = next
        } else if (next == null) {
            adjStorage[v] = prev
            prev.next = null
            toRemoveFrom.prev = null
        } else {
            prev.next = next
            next.prev = prev
            toRemoveFrom.prev = null
            toRemoveFrom.next = null
        }
    }


    /**
     * Checks if the given edge exists in the graph in costant time.
     */
    override fun checkEdgeExists(edge: Edge): Boolean {
        val (v, w) = edge
        val fromKey = computeEdgeKey(v, w)
        val existsInV = edgeStorage[fromKey] != null
        if (!diGraph) {
            val toKey = computeEdgeKey(w, v)
            val existsInW = edgeStorage[toKey] != null
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

    private fun computeEdgeKey(from: Int, to: Int): Int {
        return from * vertexCount + to
    }

    private inner class AdjListAdjIterator(
        val vertex: Int
    ) : AdjIterator {
        private var next: Node? = null
        override fun beg(): Int {
            next = adjStorage[vertex]
            return if (next != null) next!!.to else -1
        }

        override fun nxt(): Int {
            if (next != null) {
                next = next!!.next
            }
            return if (next != null) next!!.to else -1
        }

        override fun end(): Boolean {
            return next == null
        }
    }
}