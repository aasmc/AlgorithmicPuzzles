package sedgewick_algorithms_c.chapter_17.util

import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph
import sedgewick_algorithms_c.chapter_17.impl.DenseGraph

object GraphCreator {
    fun createDenseGraph(): Graph {
        return DenseGraph(7, false).apply {
            insert(Edge(0, 1))
            insert(Edge(0, 2))
            insert(Edge(0, 3))
            insert(Edge(1, 3))
            insert(Edge(1, 4))
            insert(Edge(2, 4))
            insert(Edge(3, 4))
            insert(Edge(5, 6))
        }
    }
}