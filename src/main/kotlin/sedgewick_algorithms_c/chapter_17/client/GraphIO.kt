package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph
import sedgewick_algorithms_c.chapter_17.impl.DenseGraph
import java.util.*

object GraphIO {
    /**
     * Visualizes the given Graph.
     * Shows all edges of a vertex on a single line in the form:
     *      fromVertex - toVertex fromVertex - toVertex ...
     */
    fun show(graph: Graph) {
        for (vertex in 0 until graph.vertexCount()) {
            val iterator = graph.adjIterator(vertex)
            var next = iterator.beg()
            while (!iterator.end()) {
                print("$vertex - $next ")
                next = iterator.nxt()
            }
            println()
        }
    }

    /**
     * Reads pairs of integer values from standard input
     * and inserts them as Edges into the given Graph.
     */
    fun scanEZ(graph: Graph) {
        val scanner = Scanner(System.`in`)
        while (scanner.hasNext()) {
            val v = scanner.nextInt()
            if (scanner.hasNext()) {
                val w = scanner.nextInt()
                graph.insert(Edge(v, w))
            }
        }
    }

    /**
     * Reads pairs of symbols from standard input, converts them to integers
     * and inserts them as Edges into the given Graph.
     * Returns the map of symbol to index, where each symbol
     * corresponds to a vertex index in the Graph.
     */
    fun scan(graph: Graph): Map<String, Int> {
        val symbolToIndex = hashMapOf<String, Int>()
        var currentIndex = 0
        val scanner = Scanner(System.`in`)
        while (scanner.hasNext()) {
            val v = scanner.next()
            if (scanner.hasNext()) {
                val w = scanner.next()
                val vIdx = symbolToIndex.computeIfAbsent(v) { currentIndex++ }
                val wIdx = symbolToIndex.computeIfAbsent(w) { currentIndex++ }
                graph.insert(Edge(vIdx, wIdx))
            }
        }
        return symbolToIndex
    }

    /**
     * Adds Edges from the given vector to the graph.
     */
    fun scanVector(graph: Graph, vector: List<Edge>) {
        for (e in vector) {
            graph.insert(e)
        }
    }
}

fun main() {
    val g = DenseGraph(10, false)
    val m = GraphIO.scan(g)
    for ((k, v) in m) {
        println("$k - $v")
    }
    GraphIO.show(g)
}