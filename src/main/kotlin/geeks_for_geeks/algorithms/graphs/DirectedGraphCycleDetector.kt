package geeks_for_geeks.algorithms.graphs

import java.lang.UnsupportedOperationException
import java.util.LinkedList

object DirectedGraphCycleDetector {
    fun <T : Comparable<T>> detectCycleViaDFS(graph: Graph<T>): Boolean {
        val visited = BooleanArray(graph.getVertexCount())
        val recursionStack = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                if (dfsCycleHelper(graph, v, visited, recursionStack)) {
                    return true
                }
            }
        }
        return false
    }

    private fun <T : Comparable<T>> dfsCycleHelper(
        graph: Graph<T>,
        source: T,
        visited: BooleanArray,
        // keeps track of vertices currently involved in the recursion stack
        recursionStack: BooleanArray
    ): Boolean {
        val idx = graph.getVertexIdx(source)
        visited[idx] = true
        // mark this vertex as the one in the recursion stack
        recursionStack[idx] = true
        for (v in graph.getAdjacentFor(source)) {
            val vIdx = graph.getVertexIdx(v)
            // if we have not visited this vertex, and there's a cycle among its adjacent
            // vertices then return true
            if (!visited[vIdx] && dfsCycleHelper(graph, v, visited, recursionStack)) {
                return true
                // if we have visited this vertex and it is in the recursion stack,
                // then we have a cycle
            } else if (recursionStack[vIdx]) {
                return true
            }
        }
        // when we reach here, we checked all reachable vertices, so mark this
        // as not in the recursion stack
        recursionStack[idx] = false
        return false
    }

    /**
     * Checks whether a given graph contains a cycle or not using Kahn's algorithm:
     *
     * 1. Store inDegree of every vertex
     * 2. Create a queue
     * 3. Add all 0-inDegree vertices to the queue
     * 4. Initialize count = 0
     * 5. While queue is not empty:
     *  - poll a vertex from the queue
     *  - increment the count
     *  - for every adjacent vertex U:
     *      a. decrement its inDegree count
     *      b. if its inDegree count becomes 0, add it to the queue
     * 6. When we processed all the vertices, the count must be equal to the number
     *    of processed vertices. If not, then we have a cycle in the graph, because
     *    at a certain point during traversal, there'll be no vertex with inDegree == 0,
     *    our queue will be empty, and we will go out of the loop, without processing
     *    all the vertices.
     */
    fun <T : Comparable<T>> detectCycleViaBFS(graph: Graph<T>): Boolean {
        if (!graph.isDirected()) {
            throw UnsupportedOperationException("This cycle detector works only with directed graphs")
        }
        val inDegrees = GraphDegreeCounter(graph).getInDegreeMap().toMutableMap()
        val queue = LinkedList<T>()
        for ((v, deg) in inDegrees) {
            if (deg == 0) {
                queue.add(v)
            }
        }
        var counter = 0
        while (queue.isNotEmpty()) {
            val v = queue.poll()
            for (adj in graph.getAdjacentFor(v)) {
                inDegrees.computeIfPresent(adj) { _, deg -> deg - 1 }
                if (inDegrees[adj] == 0) queue.add(adj)
            }
            ++counter
        }
        return counter != graph.getVertexCount()
    }
}