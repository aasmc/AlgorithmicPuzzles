package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
}