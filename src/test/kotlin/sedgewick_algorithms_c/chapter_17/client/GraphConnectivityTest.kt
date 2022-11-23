package sedgewick_algorithms_c.chapter_17.client

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sedgewick_algorithms_c.chapter_17.util.GraphCreator

internal class GraphConnectivityTest {
    private val g = GraphCreator.createDenseGraph()
    private val sut = GraphConnectivity(g)

    @Test
    fun testConnectedComponentCount() {
        assertEquals(2, sut.count())
    }

    @Test
    fun testConnected_vertices() {
        assertTrue(sut.checkConnected(0, 4))
        assertTrue(sut.checkConnected(5, 6))
        assertFalse(sut.checkConnected(5, 4))
    }
}