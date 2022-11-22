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
    }

    @Test
    fun addEdgeTestStringGraph() {
        val g = GraphAdjList<String>(false,10)
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

    @Test
    fun bfsTest() {
        val g = createStringGraph()
        g.bfs("A") {
            println(it)
        }

        val ig = createIntGraph()
        ig.bfs(0) {
            print("$it ")
        }
    }

    @Test
    fun bfsNoSourceTest() {
        val g = createStringGraph()
        val c = g.bfsNoSource() {
            println(it)
        }
        assertEquals(2, c)

        val ig = createIntGraph()
        val ic = ig.bfsNoSource() {
            print("$it ")
        }
        assertEquals(3, ic)
    }

    private fun createIntGraph(): GraphAdjList<Int> {
        return GraphAdjList<Int>(false,10).apply {
            addEdge(0, 1)
            addEdge(0, 2)
            addEdge(1, 2)
            addEdge(1, 3)
            addEdge(2, 3)
            addEdge(2, 4)
            addEdge(3, 4)

            addEdge(5, 6)
            addEdge(6, 7)
            addEdge(5, 7)

            addEdge(11, 12)
            addEdge(12, 13)
            addEdge(13, 14)
        }
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