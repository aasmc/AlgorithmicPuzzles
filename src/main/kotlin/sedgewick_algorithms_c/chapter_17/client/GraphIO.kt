package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph

object GraphIO {
    /**
     * Visualizes the given Graph.
     */
    fun show(graph: Graph) {
        TODO()
    }

    /**
     * Reads pairs of integer values from standard input
     * and inserts them as Edges into the given Graph.
     */
    fun scanEZ(graph: Graph) {
        TODO()
    }

    /**
     * Reads pairs of symbols from standard input, converts them to integers
     * and inserts them as Edges into the given Graph.
     * Returns the map of symbol to index, where each symbol
     * corresponds to a vertex index in the Graph.
     */
    fun scan(graph: Graph): Map<String, Int> {
        TODO()
    }

    /**
     * Adds Edges from the given vector to the graph.
     */
    fun scanVector(graph: Graph, vector: List<Edge>) {
        TODO()
    }
}