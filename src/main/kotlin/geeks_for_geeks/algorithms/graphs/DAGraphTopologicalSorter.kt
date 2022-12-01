package geeks_for_geeks.algorithms.graphs

import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import java.util.LinkedList
import java.util.Stack

object DAGraphTopologicalSorter {
    /**
     * Sorts vertices of the given directed acyclic graph in topological order:
     * dependent vertices go after the vertices they depend upon.
     *
     * Algorithm (Kahn's Algorithm):
     *   1. Store inDegree of every vertex
     *   2. Create a queue
     *   3. Add all 0-inDegree vertices to the queue in any order
     *   4. Traverse the queue:
     *      - pop a vertex U from the queue and add it to the result list
     *      - for every adjacent vertex of U:
     *          a. reduce its inDegree by 1
     *          b. if its inDegree == 0, add it to the queue
     */
    fun <V : Comparable<V>> sortTopologicallyBFS(graph: Graph<V>): List<V> {
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

    /**
     * Algorithm:
     * 1. Create an empty stack
     * 2. For every vertex U:
     *    - if U is not visited:
     *          DFSHelper(U, stack)
     * 3. While stack is not empty:
     *  - pop a vertex from the stack and add it to the result list.
     *
     * DFSHelper(U, stack):
     *  1. mark U as visited,
     *  2. for every adjacent V of U
     *      - if V is not visited: DFSHelper(V, stack)
     *  3. push U to the stack
     */
    fun <V : Comparable<V>> sortTopologicallyDFS(graph: Graph<V>): List<V> {
        if (!graph.isDirected()) {
            throw UnsupportedOperationException("Topological sort can be performed on directed acyclic graphs only!")
        }
        if (DirectedGraphCycleDetector.detectCycleViaDFS(graph)) {
            throw IllegalArgumentException("This graph contains cycles, so topological sort cannot be performed")
        }
        val stack: Stack<V> = Stack()
        val visited: BooleanArray = BooleanArray(graph.getVertexCount())
        for (v in graph.vertices()) {
            if (!visited[graph.getVertexIdx(v)]) {
                dfsHelper(stack, visited, v, graph)
            }
        }
        val result = mutableListOf<V>()
        while (stack.isNotEmpty()) {
            result.add(stack.pop())
        }
        return result.toList()
    }

    private fun <V: Comparable<V>> dfsHelper(stack: Stack<V>, visited: BooleanArray, vertex: V, graph: Graph<V>) {
        visited[graph.getVertexIdx(vertex)] = true
        for (adj in graph.getAdjacentFor(vertex)) {
            if (!visited[graph.getVertexIdx(adj)]) {
                dfsHelper(stack, visited, adj, graph)
            }
        }
        stack.push(vertex)
    }
}