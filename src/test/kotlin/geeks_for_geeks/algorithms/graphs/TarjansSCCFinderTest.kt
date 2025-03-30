package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.kotlin.GraphAdjList
import geeks_for_geeks.algorithms.graphs.kotlin.TarjansSCCFinder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TarjansSCCFinderTest {
    val sut = TarjansSCCFinder

    @Test
    fun testCorrectAlgorithm() {
        val g = GraphAdjList<Int>(directed = true).apply {
            addEdge(1, 0)
            addEdge(0, 2)
            addEdge(2, 1)
            addEdge(0, 3)
            addEdge(3, 4)
        }
        val result = sut.findSCC(g).sortedBy { it.vertices.size }
        assertEquals(3, result.size)
        assertEquals(1, result[0].vertices.size)
        assertEquals(1, result[1].vertices.size)
        assertEquals(3, result[2].vertices.size)
        for (scc in result) {
            println(scc )
        }
    }
}