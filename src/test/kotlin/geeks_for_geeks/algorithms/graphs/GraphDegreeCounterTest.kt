package geeks_for_geeks.algorithms.graphs

import geeks_for_geeks.algorithms.graphs.kotlin.Graph
import geeks_for_geeks.algorithms.graphs.kotlin.GraphAdjList
import geeks_for_geeks.algorithms.graphs.kotlin.GraphDegreeCounter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GraphDegreeCounterTest {

    data class Vertex(
        val value: Int
    ): Comparable<Vertex> {
        override fun compareTo(other: Vertex): Int {
            return value.compareTo(other.value)
        }
    }

    private lateinit var graph: Graph<Vertex>
    private lateinit var sut: GraphDegreeCounter<Vertex>

    @BeforeEach
    fun setup() {
        graph = GraphAdjList()
        graph.addEdge(Vertex(0), Vertex(1))
        graph.addEdge(Vertex(0), Vertex(2))
        graph.addEdge(Vertex(0), Vertex(3))
        graph.addEdge(Vertex(0), Vertex(4))
        graph.addEdge(Vertex(1), Vertex(3))
        graph.addEdge(Vertex(2), Vertex(4))
        graph.addEdge(Vertex(3), Vertex(4))
        sut = GraphDegreeCounter(graph)
    }

    @Test
    fun testPositiveScenario() {
        assertEquals(4, sut.getOutDegree(Vertex(0)))
        assertEquals(3, sut.getOutDegree(Vertex(3)))
        assertEquals(3, sut.getOutDegree(Vertex(4)))
        assertEquals(2, sut.getOutDegree(Vertex(2)))
        assertEquals(2, sut.getOutDegree(Vertex(1)))
    }

    @Test
    fun testNoVertexThrows() {
        assertThrows(IllegalArgumentException::class.java) {
            sut.getOutDegree(Vertex(10))
        }
    }
}