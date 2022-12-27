package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.kotlin.DirectedGraphCycleDetector
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DirectedGraphCycleDetectorTest {
    private val noCycleGraph = GraphGenerators.createDirectedGraphNoCycle6Vertices5Edges()
    private val cycleGraph = GraphGenerators.createDirectedGraphWithCycle6Vertices6Edges()
    private val sut = DirectedGraphCycleDetector

    @Test
    fun correctlyDeterminesNoCycle_whenNoCycleInGraph() {
        assertFalse(sut.detectCycleViaDFS(noCycleGraph))
        assertFalse(sut.detectCycleViaBFS(noCycleGraph))
    }

    @Test
    fun correctlyIdentifiesCycle_whenCycleInGraph() {
        assertTrue(sut.detectCycleViaDFS(cycleGraph))
        assertTrue(sut.detectCycleViaBFS(cycleGraph))
    }
}