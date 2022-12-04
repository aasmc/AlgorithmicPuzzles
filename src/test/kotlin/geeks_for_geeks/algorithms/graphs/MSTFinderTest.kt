package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.searching.equalsDelta
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MSTFinderTest {
    val graph = GraphGenerators.createGraphForMSTFinderResult16()
    val sut = MSTFinder

    @Test
    fun findMST_correct() {
        val expected = 16.0
        val result = sut.findMSTForUndirectedWeightedConnectedGraph(graph)
        assertTrue(expected.equalsDelta(result))
    }
}