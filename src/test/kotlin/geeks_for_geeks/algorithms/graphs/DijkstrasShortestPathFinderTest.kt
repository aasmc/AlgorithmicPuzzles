package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.GraphGenerators.createUndirectedWeightedGraphForDijkstras
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DijkstrasShortestPathFinderTest {
    val graph = createUndirectedWeightedGraphForDijkstras()
    val sut = DijkstrasShortestPathFinder

    @Test
    fun testAlgorithmCorrect() {
        val expected = listOf<ShortestPath<Int>>(
            ShortestPath(0, 0.0),
            ShortestPath(1, 5.0),
            ShortestPath(2, 8.0),
            ShortestPath(3, 10.0)
        )
        val result = sut.findShortestPathsForSource(0, graph)
        assertEquals(expected, result)
    }
}