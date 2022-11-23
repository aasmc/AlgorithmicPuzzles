package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class GraphAdjListTest {

    @Test
    fun addEdgeTestIntGraph() {
        val graph = GraphAdjList<Int>(false,0)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.visualizeGraph()

        assertEquals(4, graph.getVertexCount())
        assertEquals(8, graph.getEdgeCount())

        assertTrue(graph.checkEdgeExists(0, 1))
        assertTrue(graph.checkEdgeExists(1, 0))
        assertTrue(graph.checkEdgeExists(0, 2))
        assertTrue(graph.checkEdgeExists(2, 0))
        assertTrue(graph.checkEdgeExists(1, 2))
        assertTrue(graph.checkEdgeExists(2, 1))
        assertTrue(graph.checkEdgeExists(1, 3))
        assertTrue(graph.checkEdgeExists(3, 1))
    }

    @Test
    fun addEdgeTestStringGraph() {
        val g = GraphAdjList<String>(false,10)
        g.addEdge("A", "B")
        g.addEdge("A", "C")
        g.addEdge("B", "C")
        g.addEdge("B", "E")
        g.visualizeGraph()

        assertEquals(4, g.getVertexCount())
        assertEquals(8, g.getEdgeCount())

        assertTrue(g.checkEdgeExists("A", "B"))
        assertTrue(g.checkEdgeExists("A", "C"))
        assertTrue(g.checkEdgeExists("B", "A"))
        assertTrue(g.checkEdgeExists("B", "C"))
        assertTrue(g.checkEdgeExists("B", "E"))
        assertTrue(g.checkEdgeExists("C", "A"))
        assertTrue(g.checkEdgeExists("C", "B"))
        assertTrue(g.checkEdgeExists("E", "B"))
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
        assertEquals(1, aList.count())
        assertEquals(listOf("C"), aList)

        assertThrows<IllegalArgumentException>() {
            g.removeEdge("A", "U")
        }

        val bList = g.getAdjacentFor("B")
        assertEquals(2, bList.count())
        assertEquals(listOf("C", "E"), bList)
    }


    private fun createStringGraph(): GraphAdjList<String> {
        val g = GraphAdjList<String>(false,10).apply {
            addEdge("A", "B")
            addEdge("A", "C")
            addEdge("B", "C")
            addEdge("B", "E")

            addEdge("K", "M")
            addEdge("K", "L")
            addEdge("L", "M")
        }
        return g
    }
}