package sedgewick_algorithms_c.chapter_17.client

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sedgewick_algorithms_c.chapter_17.util.GraphCreator
import sedgewick_algorithms_c.chapter_17.util.GraphCreator.createDirectedSparseGraphLinkedList

internal class DegreeCounterTest {
    private val g = GraphCreator.createUndirectedSparseGraphLinkedList()
    private val sut = DegreeCounter(g)

    @Test
    fun testCorrectlyCountsOutDegrees() {
        assertEquals(1, sut.getOutDegree(5))
        assertEquals(1, sut.getOutDegree(6))
        assertEquals(3, sut.getOutDegree(0))
        assertEquals(3, sut.getOutDegree(1))
        assertEquals(2, sut.getOutDegree(2))
        assertEquals(3, sut.getOutDegree(3))
        assertEquals(3, sut.getOutDegree(4))
    }

    @Test
    fun testCorrectlyCountsInDegrees() {
        val directed = createDirectedSparseGraphLinkedList()
        val counter = DegreeCounter(directed)

        assertEquals(2, counter.getInDegree(1))
        assertEquals(1, counter.getInDegree(0))
        assertEquals(0, counter.getInDegree(2))
        assertEquals(1, counter.getInDegree(4))
        assertEquals(1, counter.getInDegree(3))
        assertEquals(1, counter.getInDegree(5))
        assertEquals(1, counter.getInDegree(6))
    }
}