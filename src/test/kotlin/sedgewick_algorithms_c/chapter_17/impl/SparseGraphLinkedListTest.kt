package sedgewick_algorithms_c.chapter_17.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sedgewick_algorithms_c.chapter_17.adt.Edge
import sedgewick_algorithms_c.chapter_17.client.GraphIO
import sedgewick_algorithms_c.chapter_17.util.GraphCreator

internal class SparseGraphLinkedListTest {
    val g = GraphCreator.createUndirectedEmptyGraphLinkedList()

    @Test
    fun testInsert_remove() {
        assertEquals(0, g.edgeCount())

        g.insert(Edge(0, 1))
        assertEquals(1, g.edgeCount())

        g.insert(Edge(0, 1))
        assertEquals(1, g.edgeCount())

        g.insert(Edge(0, 1))
        assertEquals(1, g.edgeCount())

        g.remove(Edge(0, 1))
        assertEquals(0, g.edgeCount())

        g.remove(Edge(0, 1))
        assertEquals(0, g.edgeCount())

        g.remove(Edge(0, 1))
        assertEquals(0, g.edgeCount())

        g.insert(Edge(1, 2))
        g.insert(Edge(1, 3))
        g.insert(Edge(2, 3))
        g.insert(Edge(2, 4))
        g.insert(Edge(3, 4))
        assertEquals(5, g.edgeCount())
        GraphIO.show(g)
        g.remove(Edge(5, 4))
        assertEquals(5, g.edgeCount())
        g.remove(Edge(1, 2))
        assertEquals(4, g.edgeCount())
        GraphIO.show(g)
        g.remove(Edge(3, 4))
        assertEquals(3, g.edgeCount())
        GraphIO.show(g)
        g.remove(Edge(1, 3))
        g.remove(Edge(2, 3))
        g.remove(Edge(4, 2))
        GraphIO.show(g)
        assertEquals(0, g.edgeCount())
    }

    @Test
    fun testCheckEdgeExists() {
        val graph = GraphCreator.createUndirectedSparseGraphLinkedList()
        assertTrue(graph.checkEdgeExists(Edge(0, 1)))
        assertFalse(graph.checkEdgeExists(Edge(1, 1)))
        assertTrue(graph.checkEdgeExists(Edge(2, 4)))
        assertTrue(graph.checkEdgeExists(Edge(5, 6)))
        assertFalse(graph.checkEdgeExists(Edge(4, 6)))
    }
}