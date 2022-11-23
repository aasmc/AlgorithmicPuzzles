package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Graph
import java.util.*

class GraphConnectivity(
    private val graph: Graph
) {
    /**
     * Returns the number of connected components in the graph.
     */
    fun count(): Int {
        var count = 0
        val visited = BooleanArray(graph.vertexCount()) { false }
        for (i in 0 until graph.vertexCount()) {
            if (!visited[i]) {
                ++count
                bfsHelper(visited, i)
            }
        }
        return count
    }

    private fun bfsHelper(visited: BooleanArray, source: Int) {
        val queue = LinkedList<Int>()
        queue.add(source)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val iterator = graph.adjIterator(current)
            var next = iterator.beg()
            while (!iterator.end()) {
                if (!visited[next]) {
                    visited[next] = true
                    queue.add(next)
                }
                next = iterator.nxt()
            }
        }
    }

    /**
     * Checks if two vertices are in the connected component of
     * the Graph.
     */
    fun checkConnected(v: Int, w: Int): Boolean {
        val visited = BooleanArray(graph.vertexCount())
        val queue = LinkedList<Int>()
        queue.add(v)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val iterator = graph.adjIterator(current)
            var next = iterator.beg()
            while (!iterator.end()) {
                if (next == w) return true
                if (!visited[next]) {
                    visited[next] = true
                    queue.add(next)
                }
                next = iterator.nxt()
            }
        }
        return false
    }
}