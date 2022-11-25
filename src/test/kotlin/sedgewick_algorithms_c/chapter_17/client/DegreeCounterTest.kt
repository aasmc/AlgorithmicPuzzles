package sedgewick_algorithms_c.chapter_17.client

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sedgewick_algorithms_c.chapter_17.util.GraphCreator

internal class DegreeCounterTest {
    private val g = GraphCreator.createUndirectedSparseGraphLinkedList()
    private val sut = DegreeCounter(g)

    @Test
    fun testCorrectlyCountsDegrees() {
        assertEquals(1, sut[5])
        assertEquals(1, sut[6])
        assertEquals(3, sut[0])
        assertEquals(3, sut[1])
        assertEquals(2, sut[2])
        assertEquals(3, sut[3])
        assertEquals(3, sut[4])
    }
}