package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.kotlin.DAGraphTopologicalSorter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DAGraphTopologicalSorterTest {
    val graph = GraphGenerators.createDAGForTopologicalSort()
    val sorter = DAGraphTopologicalSorter

    @Test
    fun testTopoSortBFS() {
        val result = sorter.sortTopologicallyBFS(graph)
        assertTrue(result[0] == 1 || result[0] == 0)
        assertTrue(result[result.lastIndex] == 3)
    }


    @Test
    fun testTopoSortDFS() {
        val result = sorter.sortTopologicallyDFS(graph)
        assertTrue(result[0] == 1 || result[0] == 0)
        assertTrue(result[result.lastIndex] == 3)
    }
}