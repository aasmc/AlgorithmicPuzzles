package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph

fun edges(graph: Graph): List<Edge> {
    val result = ArrayList<Edge>(graph.edgeCount())
    for (v in 0 until graph.vertexCount()) {
        val iterator = graph.adjIterator(v)
        var w = iterator.beg()
        while (!iterator.end()) {
            if (graph.isDirected() || v < w) {
                result.add(Edge(v, w))
            }
            w = iterator.nxt()
        }
    }
    return result
}