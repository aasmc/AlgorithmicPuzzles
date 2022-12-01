package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DAGraphTopologicalSorterTest {
    val graph = GraphGenerators.createDAGForTopologicalSort()
    val sorter = DAGraphTopologicalSorter

    @Test
    fun testTopoSort() {
        val result = sorter.sortTopologically(graph)
        assertTrue(result[0] == 0 || result[0] == 1)
        assertTrue(result[1] == 0 || result[1] == 1)
        assertTrue(result[2] == 2 || result[2] == 4)
        assertTrue(result[3] == 2 || result[3] == 4)
        assertTrue(result[4] == 3)
    }
}