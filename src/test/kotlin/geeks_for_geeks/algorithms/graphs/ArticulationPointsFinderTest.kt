package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ArticulationPointsFinderTest {

    private val sut = ArticulationPointsFinder

    @Test
    fun algorithmCorrect() {
        val g = GraphAdjList<Int>(directed = false).apply {
            addEdge(1, 0)
            addEdge(0, 2)
            addEdge(2, 1)
            addEdge(0, 3)
            addEdge(3, 4)
        }
        val result = sut.findArticulationPoints(g).sorted()
        assertEquals(listOf(0, 3), result)
    }
}