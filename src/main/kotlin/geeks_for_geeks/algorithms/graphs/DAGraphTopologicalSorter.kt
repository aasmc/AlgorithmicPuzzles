package geeks_for_geeks.algorithms.graphs

import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import java.util.LinkedList

object DAGraphTopologicalSorter {
    /**
     * Sorts vertices of the given directed acyclic graph in topological order:
     * dependent vertices go after the vertices they depend upon.
     *
     * Algorithm:
     *   1. Store inDegree of every vertex
     *   2. Create a queue
     *   3. Add all 0-inDegree vertices to the queue in any order
     *   4. Traverse the queue:
     *      - pop a vertex U from the queue and add it to the result list
     *      - for every adjacent vertex of U:
     *          a. reduce its inDegree by 1
     *          b. if its inDegree == 0, add it to the queue
     */
    fun <V : Comparable<V>> sortTopologically(graph: Graph<V>): List<V> {
        if (!graph.isDirected()) {
            throw UnsupportedOperationException("Topological sort can be performed on directed acyclic graphs only!")
        }
        if (DirectedGraphCycleDetector.detectCycleViaDFS(graph)) {
            throw IllegalArgumentException("This graph contains cycles, so topological sort cannot be performed")
        }
        val degreeCounter = GraphDegreeCounter(graph)
        val inDegrees = degreeCounter.getInDegreeMap().toMutableMap()
        val queue = LinkedList<V>()
        for ((v, degree) in inDegrees) {
            if (degree == 0) {
                queue.add(v)
            }
        }
        val result = mutableListOf<V>()
        while (queue.isNotEmpty()) {
            val vertex = queue.poll()
            result.add(vertex)
            for (adj in graph.getAdjacentFor(vertex)) {
                inDegrees.computeIfPresent(adj) { _, v -> v - 1 }
                if (inDegrees[adj] == 0) {
                    queue.add(adj)
                }
            }
        }
        return result
    }

}