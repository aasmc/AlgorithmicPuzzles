package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.searching.equalsDelta
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MSTFinderTest {
    val graph16 = GraphGenerators.createGraphForMSTFinderResult16()
    val graph13 = GraphGenerators.createGraphForMST13()
    val sut = UndirectedWeightedConnectedGraphMSTFinder

    @Test
    fun findMST16_correct() {
        val expected = 16.0
        val result = sut.findMST(graph16)
        assertTrue(expected.equalsDelta(result))
    }

    @Test
    fun findMST13_correct() {
        val expected = 13.0
        val result = sut.findMST(graph13)
        assertTrue(result.equalsDelta(expected))
    }

    @Test
    fun findMST_correct_arbitrarily() {
        val graph = GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 10.0)
            addEdge(1, 2, 10.0)
            addEdge(2, 3, 10.0)
            addEdge(3, 4, 10.0)
        }

        val result = sut.findMST(graph)
        assertTrue(result.equalsDelta(40.0))

        val g = GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 5.0)
            addEdge(0, 2, 8.0)
            addEdge(1, 2, 10.0)
            addEdge(1, 3, 15.0)
            addEdge(3, 2, 20.0)
        }
        val r = sut.findMST(g)
        assertTrue(r.equalsDelta(28.0))

        val gg = GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 4.0)
            addEdge(1, 3, 2.0)
            addEdge(0, 2, 3.0)
            addEdge(1, 2, 1.0)
            addEdge(2, 3, 4.0)
            addEdge(3, 4, 2.0)
            addEdge(4, 5, 6.0)
        }

        val rr = sut.findMST(gg)
        assertTrue(rr.equalsDelta(14.0))
    }
}













