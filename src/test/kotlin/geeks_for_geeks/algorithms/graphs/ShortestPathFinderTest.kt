package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.GraphGenerators.createGraphForBellmanFord
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ShortestPathFinderTest {
    val dag = GraphGenerators.createWeightedDirectedAcyclicGraph()
    val finder = ShortestPathFinder

    @Test
    fun findShortestPathInDag_correct() {
        val result = finder.findShortestPathsInDAG(dag, 0)
        val zeroIdx = dag.getVertexIdx(0)
        val oneIdx = dag.getVertexIdx(1)
        val twoIdx = dag.getVertexIdx(2)
        val threeIdx = dag.getVertexIdx(3)
        val fourIdx = dag.getVertexIdx(4)
        val fiveIdx = dag.getVertexIdx(5)
        val zeroEx = 0.0
        val oneEx = 2.0
        val twoEx = 3.0
        val threeEx = 6.0
        val fourEx = 1.0
        val fiveEx = 5.0
        assertEquals(zeroEx, result[zeroIdx])
        assertEquals(oneEx, result[oneIdx])
        assertEquals(twoEx, result[twoIdx])
        assertEquals(threeEx, result[threeIdx])
        assertEquals(fourEx, result[fourIdx])
        assertEquals(fiveEx, result[fiveIdx])
    }

    @Test
    fun bellmanFordCorrect() {
        val g = createGraphForBellmanFord()
        val result = finder.bellmanFordSSSP(g, 0).sortedBy { it.vertex }
        assertTrue(result.size == 10)
        assertEquals(0.0, result[0].distance)
        assertEquals(5.0, result[1].distance)
        assertEquals(Double.NEGATIVE_INFINITY, result[2].distance)
        assertEquals(Double.NEGATIVE_INFINITY, result[3].distance)
        assertEquals(Double.NEGATIVE_INFINITY, result[4].distance)
        assertEquals(35.0, result[5].distance)
        assertEquals(40.0, result[6].distance)
        assertEquals(-10.0, result[7].distance)
        assertEquals(-20.0, result[8].distance)
        assertEquals(Double.NEGATIVE_INFINITY, result[9].distance)
    }


}