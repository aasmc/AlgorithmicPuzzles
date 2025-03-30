package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.kotlin.GraphAdjList
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
    fun addEdgeDirected() {
        val g = GraphGenerators.createDirectedGraphWithCycle6Vertices6Edges()
        assertEquals(6, g.getVertexCount())
        assertEquals(6, g.getEdgeCount())

        // same edge, no addition
        g.addEdge(5, 6)
        assertEquals(6, g.getVertexCount())
        assertEquals(6, g.getEdgeCount())

        // new edge
        g.addEdge(6, 7)
        assertEquals(7, g.getVertexCount())
        assertEquals(7, g.getEdgeCount())

        g.addEdge(7, 6)
        assertEquals(7, g.getVertexCount())
        assertEquals(8, g.getEdgeCount())
        assertTrue(g.checkEdgeExists(7, 6))
        assertTrue(g.checkEdgeExists(6, 7))
        assertFalse(g.checkEdgeExists(6, 1))
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
        val expected = setOf("A", "C", "E")
        assertEquals(expected, adj)

        assertThrows<IllegalArgumentException>(){
            g.getAdjacentFor("U")
        }
    }

    @Test
    fun getAdjacentDirectedGraph() {
        val g = GraphGenerators.createDirectedGraphNoCycle6Vertices5Edges()
        val ex = setOf(3, 4)
        val ac = g.getAdjacentFor(1)
        assertEquals(ex, ac)

        val ex1 = setOf<Int>()
        val ac1 = g.getAdjacentFor(2)
        assertEquals(ex1, ac1)

        val ex2 = setOf(6)
        val ac2 = g.getAdjacentFor(5)
        assertEquals(ex2, ac2)
    }

    @Test
    fun testDirectedIterator() {
        val g = GraphGenerators.createDirectedGraphWithCycle6Vertices6Edges()
        val iterator = g.adjIterator(1)
        val result = mutableSetOf<Int>()
        while (iterator.hasNext()) {
            result.add(iterator.next())
        }
        val ex = setOf(3, 4)
        assertEquals(result.toSet(), ex)
    }

    @Test
    fun removeEdgeTestUndirected() {
        val g = createStringGraph()
        assertEquals(7, g.getVertexCount())
        assertEquals(14, g.getEdgeCount())

        val removed = g.removeEdge("B", "A")
        assertTrue(removed)
        assertEquals(12, g.getEdgeCount())

        val aList = g.getAdjacentFor("A")
        assertEquals(1, aList.size)
        assertEquals(setOf("C"), aList)

        assertThrows<IllegalArgumentException>() {
            g.removeEdge("A", "U")
        }

        val bList = g.getAdjacentFor("B")
        assertEquals(2, bList.size)
        assertEquals(setOf("C", "E"), bList)

        assertEquals(7, g.getVertexCount())

        assertTrue(g.removeEdge("K", "M"))
        assertEquals(10, g.getEdgeCount())

        assertTrue(g.removeEdge("M", "L"))
        assertEquals(8, g.getEdgeCount())

        assertTrue(g.removeEdge("L", "K"))
        assertEquals(6, g.getEdgeCount())

        assertFalse(g.removeEdge("K", "M"))
        assertEquals(6, g.getEdgeCount())

        assertFalse(g.removeEdge("M", "L"))
        assertEquals(6, g.getEdgeCount())

        assertFalse(g.removeEdge("L", "K"))
        assertEquals(6, g.getEdgeCount())

        assertEquals(7, g.getVertexCount())
    }

    @Test
    fun removeEdgeTestDirected() {
        val g = GraphGenerators.createDirectedGraphNoCycle6Vertices5Edges()
        assertEquals(6, g.getVertexCount())
        assertEquals(5, g.getEdgeCount())

        assertTrue(g.removeEdge(5, 6))
        assertEquals(6, g.getVertexCount())
        assertEquals(4, g.getEdgeCount())
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