package geeks_for_geeks.algorithms.graphs

import java.util.*

class BFSTraverser<V : Comparable<V>>(
    private val graph: Graph<V>
) {

    /**
     * Performs a BFS traversal of the entire graph, even if some of its edges are
     * disconnected.
     *
     * Returns the number of disconnected subgraphs.
     */
    fun bfsNoSource(consume: (V) -> Unit): Int {
        val visited = BooleanArray(graph.getVertexCount()) { false }
        var count = 0
        for (vertex in graph.vertices()) {
            val idx = graph.getVertexIdx(vertex)
            if (!visited[idx]) {
                ++count
                bfsHelper(vertex, visited, consume)
            }
        }
        return count
    }


    /**
     * Breadth-first traversal of the graph:
     *   - visit the source vertex
     *   - visit all adjacent vertices of the source vertex
     *   - visit all other vertices reachable from the source through its adjacent vertices
     *
     *   A vertex is visited only once.
     */
    private fun bfsHelper(source: V, visited: BooleanArray, consume: (V) -> Unit) {
        val sourceIdx = graph.getVertexIdx(source)
        visited[sourceIdx] = true
        val queue = LinkedList<V>()
        queue.add(source)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            consume(current)
            val adjList = graph.getAdjacentFor(current)
            for (v in adjList) {
                val idx = graph.getVertexIdx(v)
                if (!visited[idx]) {
                    visited[idx] = true
                    queue.add(v)
                }
            }
        }
    }

    fun bfsForSource(source: V, consume: (V) -> Unit) {
        val visited = BooleanArray(graph.getVertexCount()) { false }
        bfsHelper(source, visited, consume)
    }

    /**
     * Returns the array of integers where indices are indices of
     * vertices in the graph, and values are the smallest number of edges from source
     * to vertex at current index.
     *
     * -1 represents, that there's no path from source to that vertex.
     */
    fun findShortestPathsForSource(source: V): IntArray {
        val result = IntArray(graph.getVertexCount()) { -1 }
        val visited = BooleanArray(graph.getVertexCount())
        val queue = LinkedList<V>()

        val idx = graph.getVertexIdx(source)
        result[idx] = 0
        visited[idx] = true
        queue.add(source)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val currentIdx = graph.getVertexIdx(current)

            for (v in graph.getAdjacentFor(current)) {
                val adjIdx = graph.getVertexIdx(v)
                if (!visited[adjIdx]) {
                    // since we perform breadth-first traversal of the graph
                    // we are sure that this vertex is only one edge away from
                    // currently polled from the queue vertex, so we update
                    // its value with the polled vertex value + 1.
                    result[adjIdx] = result[currentIdx] + 1
                    visited[adjIdx] = true
                    queue.add(v)
                }
            }
        }
        return result
    }
}

















