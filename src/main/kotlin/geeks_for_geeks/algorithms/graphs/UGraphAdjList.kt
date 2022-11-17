package geeks_for_geeks.algorithms.graphs

import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.HashMap

class UGraphAdjList<V : Comparable<V>>(
    initialCapacity: Int = 16
) : Graph<V> {

    private val mapStorage: MutableMap<V, MutableList<V>> = HashMap(initialCapacity)
    private val indexCreator = AtomicInteger(0)
    private val indexStorage: MutableMap<V, Int> = hashMapOf()

    override fun addEdge(from: V, to: V) {
        if (mapStorage.containsKey(from)) {
            mapStorage[from]!!.add(to)
        } else {
            mapStorage[from] = arrayListOf(to)
            indexStorage[from] = indexCreator.getAndIncrement()
        }
        if (mapStorage.containsKey(to)) {
            mapStorage[to]!!.add(from)
        } else {
            mapStorage[to] = arrayListOf(from)
            indexStorage[to] = indexCreator.getAndIncrement()
        }
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
        val removedTo = mapStorage[to]?.remove(from) ?: false
        if ((removedFrom && !removedTo) || (!removedFrom && removedTo)) {
            throw IllegalStateException(
                "This graph is in inconsistent state, because edges" +
                        " $from and $to have not been stored correctly!"
            )
        }
        return removedFrom && removedTo
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

    /**
     * Breadth-first traversal of the graph:
     *   - visit the source vertex
     *   - visit all adjacent vertices of the source vertex
     *   - visit all other vertices reachable from the source through its adjacent vertices
     *
     *   A vertex is visited only once.
     */
    override fun bfs(source: V, consume: (V) -> Unit) {
        if (!mapStorage.containsKey(source)) {
            throw IllegalArgumentException("There's no vertex in the graph: $source")
        }
        // keeps track of visited vertices
        val visitedArr = BooleanArray(mapStorage.size) { false }

        bfsHelper(source, visitedArr, consume)

    }

    private fun bfsHelper(source: V, visited: BooleanArray, consume: (V) -> Unit) {
        val sourceIdx = indexStorage[source]
            ?: throw IllegalStateException(
                "No index for vertex: $source has been saved " +
                        "when it was inserted into the graph!"
            )
        visited[sourceIdx] = true
        val queue = LinkedList<V>()
        queue.add(source)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            consume(current)
            val adjList = mapStorage[current] ?: throw IllegalStateException(
                "Vertex $current has not been saved to the graph!"
            )
            for (v in adjList) {
                val index = indexStorage[v] ?: throw IllegalStateException(
                    "No index for vertex: $v has been saved " +
                            "when it was inserted into the graph!"
                )
                if (!visited[index]) {
                    visited[index] = true
                    queue.add(v)
                }
            }
        }
    }

    /**
     * Performs a BFS traversal of the entire graph, even if some of its edges are
     * disconnected.
     */
    override fun bfsNoSource(consume: (V) -> Unit): Int {
        val visited = BooleanArray(mapStorage.size) { false }
        var count = 0
        for (vertex in mapStorage.keys) {
            val idx = indexStorage[vertex] ?: throw IllegalStateException(
                "No index for vertex: $vertex has been saved " +
                        "when it was inserted into the graph!"
            )
            if (!visited[idx]) {
                ++count
                bfsHelper(vertex, visited, consume)
            }
        }
        return count
    }
}

















