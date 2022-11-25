package sedgewick_algorithms_c.chapter_17.util

import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.adt.Graph
import sedgewick_algorithms_c.chapter_17.impl.DenseGraph
import sedgewick_algorithms_c.chapter_17.impl.SparseGraphLinkedList
import sedgewick_algorithms_c.chapter_17.impl.SparseMultiGraph

object GraphCreator {
    fun createUndirectedDenseGraph(): Graph {
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

    fun createUndirectedSparseGraph(): Graph {
        return SparseMultiGraph(7, false).apply {
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

    fun createUndirectedSparseGraphLinkedList(): Graph {
        return SparseMultiGraph(7, false).apply {
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

    fun createDirectedSparseGraphLinkedList(): Graph {
        return SparseMultiGraph(7, true).apply {
            insert(Edge(0, 1))
            insert(Edge(2, 1))
            insert(Edge(2, 3))
            insert(Edge(3, 4))
            insert(Edge(3, 5))
            insert(Edge(3, 6))
            insert(Edge(6, 0))
        }
    }

    fun createUndirectedEmptySparseGraph(): Graph {
        return SparseMultiGraph(7, false)
    }

    fun createUndirectedEmptyGraphLinkedList(): Graph {
        return SparseGraphLinkedList(7, false)
    }
}