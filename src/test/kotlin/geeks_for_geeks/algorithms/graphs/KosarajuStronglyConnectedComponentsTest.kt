package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class KosarajuStronglyConnectedComponentsTest {
    private val g1: Graph<Int> = GraphAdjList<Int>(directed = true).apply {
        addEdge(0, 1)
        addEdge(1, 2)
        addEdge(2, 3)
        addEdge(3, 0)
        addEdge(3, 4)
        addEdge(4, 5)
        addEdge(5, 4)
    }

    private val g2: Graph<Int> = GraphAdjList<Int>(directed = true).apply {
        addEdge(0, 1)
        addEdge(1, 2)
        addEdge(1, 3)
        addEdge(3, 0)
    }

    private val sut = KosarajuStronglyConnectedComponents

    @Test
    fun algorithmCorrect() {
        val ex1 = listOf<List<Int>>(
            listOf(0, 1, 2, 3),
            listOf(4, 5)
        )
        val ex2 = listOf(
            listOf(0, 1, 3),
            listOf(2)
        )
        val r1 = sut.findStronglyConnectedComponents(g1).map { it.sorted() }
        val r2 = sut.findStronglyConnectedComponents(g2).map { it.sorted() }
        assertEquals(ex1, r1)
        assertEquals(ex2, r2)
    }
}