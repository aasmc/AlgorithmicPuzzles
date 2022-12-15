package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BridgesFinderTest {
    private val sut = BridgesFinder

    @Test
    fun testAlgorithmCorrect() {
        val g = GraphAdjList<Int>(directed = false).apply {
            addEdge(1, 0)
            addEdge(0, 2)
            addEdge(2, 1)
            addEdge(0, 3)
            addEdge(3, 4)
        }
        val res = sut.findBridges(g).sortedBy { it.from }
        val ex = listOf(
            Bridge(0, 3),
            Bridge(3, 4)
        )
        assertEquals(ex, res)
    }
}