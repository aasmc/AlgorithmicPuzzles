package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class UGraphAdjListTest {

    @Test
    fun addEdgeTestIntGraph() {
        val graph = UGraphAdjList<Int>(0)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.visualizeGraph()
    }

    @Test
    fun addEdgeTestStringGraph() {
        val g = UGraphAdjList<String>(10)
        g.addEdge("A", "B")
        g.addEdge("A", "C")
        g.addEdge("B", "C")
        g.addEdge("B", "E")
        g.visualizeGraph()
    }

    @Test
    fun checkEdgeExistsTest() {
        val g = createStringGraph()
        assertTrue(g.checkEdgeExists("A", "C"))
        assertFalse(g.checkEdgeExists("A", "E"))
        assertThrows<IllegalArgumentException> { g.checkEdgeExists("U", "E") }
    }

    @Test
    fun getAdjacentForTest() {
        val g = createStringGraph()
        val adj = g.getAdjacentFor("B")
        val expected = listOf("A", "C", "E")
        assertEquals(expected, adj)

        assertThrows<IllegalArgumentException>(){
            g.getAdjacentFor("U")
        }
    }

    @Test
    fun removeEdgeTest() {
        val g = createStringGraph()
        val removed = g.removeEdge("B", "A")
        assertTrue(removed)
        val aList = g.getAdjacentFor("A")
        assertEquals(1, aList.size)
        assertEquals(listOf("C"), aList)

        assertThrows<IllegalArgumentException>() {
            g.removeEdge("A", "U")
        }

        val bList = g.getAdjacentFor("B")
        assertEquals(2, bList.size)
        assertEquals(listOf("C", "E"), bList)
    }

    private fun createStringGraph(): UGraphAdjList<String> {
        val g = UGraphAdjList<String>(10).apply {
            addEdge("A", "B")
            addEdge("A", "C")
            addEdge("B", "C")
            addEdge("B", "E")
        }
        return g
    }
}