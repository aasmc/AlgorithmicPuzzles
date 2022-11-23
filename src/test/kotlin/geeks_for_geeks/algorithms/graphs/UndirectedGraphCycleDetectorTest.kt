package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class UndirectedGraphCycleDetectorTest {
    private lateinit var cycleGraph: Graph<String>
    private lateinit var noCycleGraph: Graph<String>
    private val sut: UndirectedGraphCycleDetector = UndirectedGraphCycleDetector

    @BeforeEach
    fun setup() {
        cycleGraph = GraphGenerators.createStringGraphAdjListWithCycle()
        noCycleGraph = GraphGenerators.createStringGraphAdjListNoCycle()
    }

    @Test
    fun correctlyDetectsCycle_whenThereIsCycle() {
        assertTrue(sut.detectCycle(cycleGraph))
    }

    @Test
    fun correctlyDetectsNoCycle_whenThereIsNoCycle() {
        assertFalse(sut.detectCycle(noCycleGraph))
    }
}