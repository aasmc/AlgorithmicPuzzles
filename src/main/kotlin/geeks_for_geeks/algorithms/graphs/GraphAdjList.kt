package geeks_for_geeks.algorithms.graphs

import java.util.*
import kotlin.collections.HashMap

/**
 * Graph with representation as adjacency list.
 */
class GraphAdjList<V : Comparable<V>>(
    private val directed: Boolean = false,
    initialCapacity: Int = 16
) : Graph<V> {

    private val mapStorage: MutableMap<V, MutableList<V>> = HashMap(initialCapacity)
    private var indexCreator = 0
    private val indexStorage: MutableMap<V, Int> = hashMapOf()
    private var edgeNumber = 0L

    override fun addEdge(from: V, to: V) {
        if (mapStorage.containsKey(from)) {
            mapStorage[from]!!.add(to)
        } else {
            mapStorage[from] = arrayListOf(to)
            indexStorage[from] = createNextIndex()
        }
        ++edgeNumber
        if (!directed) {
            if (mapStorage.containsKey(to)) {
                mapStorage[to]!!.add(from)
            } else {
                mapStorage[to] = arrayListOf(from)
                indexStorage[to] = createNextIndex()
            }
            ++edgeNumber
        }
    }

    private fun createNextIndex(): Int {
        val current = indexCreator
        indexCreator++
        return current
    }

    override fun checkEdgeExists(from: V, to: V): Boolean {
        if (!mapStorage.containsKey(from))
            throw IllegalArgumentException("There's no vertex in the graph: $from")

        return mapStorage[from]?.let { list ->
            list.firstOrNull { it == to } != null
        } ?: false
    }

    override fun getAdjacentFor(vertex: V): List<V> {
        if (!mapStorage.containsKey(vertex))
            throw IllegalArgumentException("There's no vertex in the graph: $vertex")

        return mapStorage[vertex]!!.toList()
    }

    override fun removeEdge(from: V, to: V): Boolean {
        if (!mapStorage.containsKey(from))
            throw IllegalArgumentException("There's no vertex in the graph: $from")

        if (!mapStorage.containsKey(to)) {
            throw IllegalArgumentException("There's no vertex in the graph: $to")
        }

        val removedFrom = mapStorage[from]?.remove(to) ?: false
        --edgeNumber
        if (!directed) {
            val removedTo = mapStorage[to]?.remove(from) ?: false
            if ((removedFrom && !removedTo) || (!removedFrom && removedTo)) {
                throw IllegalStateException(
                    "This graph is in inconsistent state, because edges" +
                            " $from and $to have not been stored correctly!"
                )
            }
            --edgeNumber
            return removedFrom && removedTo
        }

        return removedFrom
    }

    override fun visualizeGraph() {
        for ((k, v) in mapStorage) {
            print("$k ")
            for (vertex in v) {
                print("$vertex ")
            }
            println()
        }
    }


    override fun adjIterator(source: V): AdjIterator<V> {
        return GraphAdjListIterator(source)
    }

    private inner class GraphAdjListIterator(
        source: V
    ) : AdjIterator<V> {

        private var currentList = mapStorage[source]
        private var currentIdx = 0

        override fun next(): V {
            currentList?.let { list ->
                return list[currentIdx++]
            } ?: throw IllegalAccessException("You should call hasNext before calling next to ensure iterator validity")
        }

        override fun hasNext(): Boolean {
            if (currentList.isNullOrEmpty()) return false
            return currentIdx < currentList!!.size
        }
    }

    override fun getVertexIdx(vertex: V): Int {
        return if (mapStorage.containsKey(vertex)) {
            indexStorage[vertex] ?: throw IllegalArgumentException("No index has been associated with vertex: $vertex")
        } else {
            -1
        }
    }

    override fun getVertexCount(): Int {
        return mapStorage.size
    }

    override fun getEdgeNumber(): Long {
        return edgeNumber
    }

    override fun vertices(): Set<V> {
        return mapStorage.keys
    }
}



















