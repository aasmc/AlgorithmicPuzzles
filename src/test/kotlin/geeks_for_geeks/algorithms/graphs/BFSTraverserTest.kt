package geeks_for_geeks.algorithms.graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BFSTraverserTest {

    private lateinit var intGraph: Graph<Int>
    private lateinit var stringGraph: Graph<String>
    private lateinit var intBfsTraverser: BFSTraverser<Int>
    private lateinit var stringBfsTraverser: BFSTraverser<String>

    @BeforeEach
    fun setup() {
        intGraph = createIntGraph()
        stringGraph = createStringGraph()
        intBfsTraverser = BFSTraverser(intGraph)
        stringBfsTraverser = BFSTraverser(stringGraph)
    }

    @Test
    fun bfsTestInt() {
        intBfsTraverser.bfsNoSource {
            print("$it ")
        }

        println()

        intBfsTraverser.bfsForSource(0) {
            print("$it ")
        }
    }

    @Test
    fun bfsTestString() {
        stringBfsTraverser.bfsNoSource {
            print("$it ")
        }

        println()
        stringBfsTraverser.bfsForSource("A") {
            print("$it ")
        }
    }

    @Test
    fun shortestPathsTest() {
        val expected = intArrayOf(0, 1, 1, 2, -1, -1, -1)
        val result = stringBfsTraverser.findShortestPathsForSource("A")
        assertTrue(result.contentEquals(expected))

        val ex = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3)
        val res = intBfsTraverser.findShortestPathsForSource(11)
        assertTrue(res.contentEquals(ex))
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